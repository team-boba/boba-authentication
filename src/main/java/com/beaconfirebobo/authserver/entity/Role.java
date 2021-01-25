package com.beaconfirebobo.authserver.entity;

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
    @Column(name="role_name")
    private String roleName;

    @NotNull
    @Size(min=1, message="Role description must not be empty.")
    @Column(name="description")
    private String description;

    @NotNull
    @Column(name="create_date")
    private String createDate;


    @OneToMany(fetch=FetchType.LAZY, mappedBy="role")
    private Set<UserRole> userRoles = new HashSet<>();
}
