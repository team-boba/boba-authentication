package com.beaconfirebobo.authserver.domain.user;

import lombok.*;
import org.hibernate.validator.constraints.Email;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Component
public class RegisterUser {
    @NotNull
    @Size(min=3, message="User name must be greater than 2 characters.")
    private String userName;

    @NotNull
    @Size(min=3, message="Password must be greater than 2 characters.")
    private String password;

    @NotNull
    @Email(message="Email invalid.")
    @Size(min=1, message="Email must not be empty.")
    private String email;

    @NotNull
    private List<String> roleNames = new ArrayList<>();
}
