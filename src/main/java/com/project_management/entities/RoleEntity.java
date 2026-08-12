package com.project_management.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "roles")
@Getter
@Setter
public class RoleEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @Column
    private String role;

    @OneToMany(mappedBy = "role")
    private List<UserEntity> users;

    @ManyToMany
    @JoinTable(
            name = "role_permissions",
            joinColumns = @JoinColumn(name = "role_id"),
            inverseJoinColumns = @JoinColumn(name = "permission_id")
    )
    private Set<PermissionsEntity> permissions = new HashSet<>();

}
