package com.jojo.application.db.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.jojo.application.db.components.ObjectIdGenerator;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Entity
@Table(name = "object_types")
public class ObjectType
{
    @Id
    private long objectTypeId;

    private String name;

    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "objectTypeId")
    private Set<Attribute> attributes = new HashSet<>();

    public ObjectType()
    {
        this.objectTypeId = ObjectIdGenerator.getInstance().generate();
    }

    public ObjectType(String name)
    {
        this.objectTypeId = ObjectIdGenerator.getInstance().generate();
        this.name = name;
    }

    public long getObjectTypeId()
    {
        return objectTypeId;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public void addAtribute(Attribute attribute)
    {
        attributes.add(attribute);
    }

    public void deleteAttribute(Attribute attribute)
    {
        attributes.remove(attribute);
    }

    public List<Attribute> getAttributesList()
    {
        return new ArrayList<>(attributes);
    }
}
