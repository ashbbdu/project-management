package com.project_management.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@Table(name = "permissions")
public class PermissionsEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String permission;

    @ManyToMany(mappedBy = "permissions")
    private Set<RoleEntity> roles = new HashSet<>();
}
