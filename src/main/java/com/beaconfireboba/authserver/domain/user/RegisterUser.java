package com.beaconfireboba.authserver.domain.user;

import lombok.*;
import org.hibernate.validator.constraints.Email;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
@ToString
public class RegisterUser {
    @NotNull
    @Size(min=3, message="User name must be greater than 2 characters.")
    private String userName;

    @NotNull
    @Size(min=3, message="Password must be greater than 2 characters.")
    private String password;

    @NotNull
    @Email
    @Size(min=1, message="Email is required.")
    private String email;

    private String createDate;

    @NotNull
    private int houseId;
}
