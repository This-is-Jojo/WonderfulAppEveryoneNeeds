package com.jojo.application.db.entity;

import javax.persistence.*;
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

    @Override
    public String toString()
    {
        return "Parameter{" +
                "parametersPk=" + parametersPk +
                ", value='" + value + '\'' +
                '}';
    }

    @Embeddable
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

        @Override
        public String toString()
        {
            return "ParametersPk{" +
                    "attrId=" + attrId +
                    ", objectId=" + objectId +
                    '}';
        }
    }
}
