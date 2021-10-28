package com.SimpleRest.SimpleRestAPI.store.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import java.util.Set;

@Entity
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Role implements GrantedAuthority {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @JsonIgnore
    long id;

    String role;

    @ManyToMany(mappedBy = "roles")
    Set<User> users;

    public Role() {
    }

    public Role(ERole role, Set<User> users) {
        this.role = role.name();
        this.users = users;
    }

    public void setRole(ERole role) {
        this.role = role.name();
    }

    @Override
    public String getAuthority() {
        return role;
    }
}
