package com.jojo.application.db.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.jojo.application.db.components.ObjectIdGenerator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Entity
@Table(name = "objects")
public class Object
{
    @Id
    private Long objectId;

    private long objectTypeId;

    private String name;

    private Long parentId;

    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL,  orphanRemoval = true)
    @JoinColumn(name = "objectId")
    private Set<Parameter> parameters = new HashSet<>();

    private static final Logger logger = LogManager.getLogger(Object.class);

    public Object()
    {
        this.objectId = ObjectIdGenerator.getInstance().generate();
    }

    public Object(Long objectId, String name, long objectTypeId, Long parentId)
    {
        this.objectId = objectId;
        this.name = name;
        this.objectTypeId = objectTypeId;
        this.parentId = parentId;
    }

    public Object(String name, long objectTypeId, Long parentId)
    {
        this.objectId = ObjectIdGenerator.getInstance().generate();
        this.name = name;
        this.objectTypeId = objectTypeId;
        this.parentId = parentId;
    }

    public Long getObjectId()
    {
        return objectId;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public Long getParentId()
    {
        return parentId;
    }

    public void setParentId(Long parentId)
    {
        this.parentId = parentId;
    }

    public void setObjectTypeId(long objectTypeId)
    {
        this.objectTypeId = objectTypeId;
    }

    public long getObjectTypeId()
    {
        return objectTypeId;
    }

    public void setParameterValue(long attrId, String value)
    {
        Parameter parameter;
        parameter = this.parameters.stream().filter( par -> par.getAttrId() == attrId).findFirst().orElse(new Parameter(new Parameter.ParametersPk(attrId, this.objectId)));
        parameter.setValue(value);
        logger.debug("Update parameter: {}", parameter);
        parameters.add(parameter);
    }

    public String getParameterValue(long attrId)
    {
        Parameter parameter = this.parameters.stream().filter(par -> par.getAttrId() == attrId).findFirst().orElse(null);
        return parameter != null ? parameter.getValue() : null;
    }

    public Map<Long, String> getParametersMap()
    {
        return this.parameters.stream().collect(Collectors.toMap(Parameter::getAttrId, Parameter::getValue));
    }

    public void updateParametersMap(Map<Long, String> map)
    {
        map.forEach(this::setParameterValue);
    }

    @Override
    public String toString()
    {
        return "Object{" +
                "objectId=" + objectId +
                ", name='" + name + '\'' +
                ", parentId=" + parentId +
                '}';
    }
}
