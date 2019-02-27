package com.jojo.application.db.entity;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "parameters")
public class Parameter
{
    public Parameter()
    {
    }

    @EmbeddedId
    private ParametersPk parametersPk;

    private String value;

    public long getAttrId()
    {
        return parametersPk.attrId;
    }

    public long getObjectId()
    {
        return parametersPk.objectId;
    }

    public ParametersPk getParametersPk()
    {
        return parametersPk;
    }

    public Parameter(ParametersPk parametersPk)
    {
        this.parametersPk = parametersPk;
    }

    public String getValue()
    {
        return value;
    }

    public void setValue(String value)
    {
        this.value = value;
    }

    public static class ParametersPk implements Serializable
    {
        long attrId;
        long objectId;

        public ParametersPk()
        {
        }

        public ParametersPk(long attrId, long objectId)
        {
            this.attrId = attrId;
            this.objectId = objectId;
        }
    }
}
