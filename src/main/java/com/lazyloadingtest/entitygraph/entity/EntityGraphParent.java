package com.lazyloadingtest.entitygraph.entity;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedAttributeNode;
import javax.persistence.NamedEntityGraph;
import javax.persistence.OneToMany;

@Entity
@NamedEntityGraph(
        name = "graph.Parent.children",
        attributeNodes = @NamedAttributeNode(value = "children")
)
public class EntityGraphParent implements Serializable {

    public static long serialVersionUID = 1L;

    @Id
    @GeneratedValue
    private long id;

    private String name;

    @OneToMany(targetEntity = EntityGraphChild.class, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<EntityGraphChild> children;

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

    public Set<EntityGraphChild> getChildren() {
        return children;
    }

    public void setChildren(Set<EntityGraphChild> children) {
        this.children = children;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        EntityGraphParent parent = (EntityGraphParent) o;

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
