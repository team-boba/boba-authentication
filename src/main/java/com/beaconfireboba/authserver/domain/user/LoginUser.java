package com.beaconfireboba.authserver.domain.user;

import lombok.*;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Component
public class LoginUser {
    @NotNull
    @Size(min=3, message="User name should be greater than 2 characters.")
    private String userName;

    @NotNull
    @Size(min=3, message="Password should be greater than 2 characters.")
    private String password;
}
