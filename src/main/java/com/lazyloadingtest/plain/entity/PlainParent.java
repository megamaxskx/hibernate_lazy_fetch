package com.lazyloadingtest.plain.entity;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class PlainParent implements Serializable {

    public static long serialVersionUID = 1L;

    @Id
    @GeneratedValue
    private long id;

    private String name;

    @OneToMany(targetEntity = PlainChild.class, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<PlainChild> children;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<PlainChild> getChildren() {
        return children;
    }

    public void setChildren(Set<PlainChild> children) {
        this.children = children;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PlainParent parent = (PlainParent) o;

        if (id != parent.id) return false;
        if (name != null ? !name.equals(parent.name) : parent.name != null) return false;
        return children != null ? children.equals(parent.children) : parent.children == null;

    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (children != null ? children.hashCode() : 0);
        return result;
    }
}
