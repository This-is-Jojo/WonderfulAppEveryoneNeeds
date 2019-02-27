package com.jojo.application.db.entity;

import com.jojo.application.db.components.ObjectIdGenerator;

import javax.persistence.*;

@Entity
@Table(name = "attributes")
public class Attribute
{
    @Id
    long attrId;

    long objectTypeId;

    String name;

    public Attribute()
    {
        this.attrId = ObjectIdGenerator.getInstance().generate();
    }

    public Attribute(long objectTypeId, String name)
    {
        this.attrId = ObjectIdGenerator.getInstance().generate();
        this.objectTypeId = objectTypeId;
        this.name = name;
    }

    public long getAttrId()
    {
        return attrId;
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
}
