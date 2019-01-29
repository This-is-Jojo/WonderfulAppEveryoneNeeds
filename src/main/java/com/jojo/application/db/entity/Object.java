package com.jojo.application.db.entity;


import javax.persistence.*;
import java.math.BigInteger;

@Entity
@Table(name = "objects")
public class Object
{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public BigInteger objectId;

    private BigInteger parentId;

    private String name;

    public Object(BigInteger parentId, String name)
    {
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
}
