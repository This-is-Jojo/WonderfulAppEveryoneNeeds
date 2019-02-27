package com.jojo.application.db.entity;

import com.jojo.application.db.components.ObjectIdGenerator;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "object_types")
public class ObjectType
{
    @Id
    long objectTypeId;

    public ObjectType()
    {
        this.objectTypeId = ObjectIdGenerator.getInstance().generate();
    }

    public ObjectType(String name)
    {
        this.objectTypeId = ObjectIdGenerator.getInstance().generate();
        this.name = name;
    }

    String name;

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
}
