package com.nixsolutions.ppp.model.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
public class Role implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "name", nullable = false, unique = true)
    private String name;

    @Deprecated
    public Role() {
    }

    @Deprecated
    public Role(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Role role = (Role) o;
        return Objects.equals(name, role.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    private static Role adminRole = new Role("ADMIN");
    private static Role userRole = new Role("USER");
    private static Role anonRole = new Role("ANONYMOUS");

    static public Role getAdminRole() {
        return adminRole;
    }

    static public Role getAnonRole() {
        return anonRole;
    }

    static public Role getUserRole() {
        return userRole;
    }

    static public Role getByName(String name) {
        if (name.equals("ADMIN")) {
            return adminRole;
        } else if (name.equals("USER")) {
            return userRole;
        } else if (name.equals("ANONYMOUS")){
            return  anonRole;
        } else {
            throw new RuntimeException("invalid role name");
        }
    }
}
