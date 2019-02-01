package com.jojo.application.db.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.math.BigInteger;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "objects")
public class Object
{
    @Id
    @GenericGenerator(name = "objectIdGenerator", strategy = "com.jojo.application.db.components.ObjectIdGenerator")
    @GeneratedValue(generator = "objectIdGenerator")
    @Column(name = "object_id")
    private BigInteger objectId;

    @Column(name = "name")
    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="parent_id")
    private Object parent;

    @OneToMany(mappedBy="parent", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval=true)
    private Set<Object> children = new HashSet<>();

    public Object(String name, Object parent)
    {
        this.name = name;
        this.parent = parent;
    }

    public BigInteger getObjectId()
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

    public Object getParent()
    {
        return parent;
    }

    public void setParent(Object parent)
    {
        this.parent = parent;
    }

    public Set<Object> getChildren()
    {
        return children;
    }

    public void setChildren(Set<Object> children)
    {
        this.children = children;
    }

    @Override
    public String toString()
    {
        return "Object{" +
                "objectId=" + objectId +
                ", name='" + name + '\'' +
                ", parent=" + parent +
                ", children=" + children +
                '}';
    }
}
