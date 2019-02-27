package com.jojo.application.db.entity;

import com.jojo.application.db.components.ObjectIdGenerator;

import javax.persistence.*;

@Entity
@Table(name = "objects")
public class Object
{
    @Id
    private Long objectId;

    private long objectTypeId;

    private String name;

    private Long parentId;

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
