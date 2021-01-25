package com.beaconfireboba.authserver.entity;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Entity
@Table(name="registration_token")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString


public class RegistrationToken implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @NotNull
    @Column(name = "token")
    private String token;

    @NotNull
    @Column(name = "valid_duration")
    private int validDuration;

    @NotNull
    @Column(name="email")
    private String email;

    @NotNull
    @Column(name="create_by")
    private String createBy;

}
