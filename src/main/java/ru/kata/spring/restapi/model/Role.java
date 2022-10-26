package ru.kata.spring.restapi.model;

import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "roles")
public class Role implements GrantedAuthority {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int Id;

    @Column(name = "role", nullable = false)
    private String nameRole;

    public Role() {
    }

    public Role(String nameRole) {
        this.nameRole = nameRole;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getNameRole() {
        return nameRole;
    }

    public void setNameRole(String nameRole) {
        this.nameRole = nameRole;
    }

    @Override
    public String getAuthority() {
        return getNameRole();
    }

    @Override
    public String toString() {
        return this.nameRole.substring(5);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Role role)) return false;
        return Id == role.Id && nameRole.equals(role.nameRole);
    }

    @Override
    public int hashCode() {
        return Objects.hash(Id, nameRole);
    }
}
