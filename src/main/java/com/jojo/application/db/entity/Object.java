package com.jojo.application.db.entity;

import javax.persistence.*;
import java.math.BigInteger;

@Entity
@Table(name = "objects")
public class Object
{
    @Id
    private BigInteger objectId;

    private BigInteger parentId;

    private String name;

    public Object()
    {
        this.objectId = BigInteger.valueOf(System.nanoTime());
    }

    public Object(String name, BigInteger parentId)
    {
        this.objectId = BigInteger.valueOf(System.nanoTime());
        this.name = name;
        this.parentId = parentId;
    }

    public Object(BigInteger objectId, BigInteger parentId, String name)
    {
        this.objectId = objectId;
        this.parentId = parentId;
        this.name = name;
    }

    public BigInteger getObjectId()
    {
        return objectId;
    }

    public BigInteger getParentId()
    {
        return parentId;
    }

    public void setParentId(BigInteger parentId)
    {
        this.parentId = parentId;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    @Override
    public String toString()
    {
        return "Object{" +
                "objectId=" + objectId +
                ", parentId=" + parentId +
                ", name='" + name + '\'' +
                '}';
    }

    public void setObjectId(BigInteger objectId)
    {
        this.objectId = objectId;
    }
}
