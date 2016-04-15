package com.lazyloadingtest.plain.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class PlainChild implements Serializable {

    public static long serialVersionUID = 1L;

    @Id
    @GeneratedValue
    private long id;

    private String name;

    @ManyToOne
    private PlainParent parent;

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

    public PlainParent getParent() {
        return parent;
    }

    public void setParent(PlainParent parent) {
        this.parent = parent;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PlainChild child = (PlainChild) o;

        if (id != child.id) return false;
        return name != null ? name.equals(child.name) : child.name == null;

    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (name != null ? name.hashCode() : 0);
        return result;
    }
}
