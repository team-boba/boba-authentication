package com.beaconfireboba.authserver.domain.user;

import lombok.*;
import org.hibernate.validator.constraints.Email;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

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
    private String email;

    private String createDate;

    private int houseId;
}
