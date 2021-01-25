package com.beaconfireboba.authserver.entity;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="user")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class User implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Integer id;

    @NotNull
    @Column(name="username")
    private String userName;

    @NotNull
    @Column(name="password")
    private String password;

    @NotNull
    @Column(name="email")
    private String email;

    @NotNull
    @Column(name="personid")
    private long personId;

    @NotNull
    @Column(name="createdate")
    private String createDate;

    @NotNull
    @Column(name="modificationdate")
    private String modificationDate;

    @OneToMany(fetch=FetchType.LAZY, mappedBy="user")
    private Set<UserRole> userRoles = new HashSet<>();
}
