package com.beaconfireboba.authserver.entity;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Entity
@Table(name="userrole")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class UserRole implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Integer id;

    @NotNull
    @Column(name="activeflag")
    private Boolean activeFlag;

    @NotNull
    @Column(name="createdate")
    private String createDate;

    @NotNull
    @Column(name="modificationdate")
    private String modificationDate;

    @ManyToOne
    @JoinColumn(name="userid")
    private User user;

    @ManyToOne
    @JoinColumn(name="roleid")
    private Role role;
}
