package com.beaconfireboba.authserver.entity;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="role")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Role implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Integer id;

    @NotNull
    @Size(min=1, message="Role name must not be empty.")
    @Column(name="rolename")
    private String roleName;

    @NotNull
    @Size(min=1, message="Role description must not be empty.")
    @Column(name="description")
    private String description;

    @NotNull
    @Column(name="createdate")
    private String createDate;

    @NotNull
    @Column(name="modificationdate")
    private String modificationDate;

    @Column(name="lastmodificationuserid")
    private Integer lastModificationUserId;

    @OneToMany(fetch=FetchType.LAZY, mappedBy="role")
    private Set<UserRole> userRoles = new HashSet<>();
}
