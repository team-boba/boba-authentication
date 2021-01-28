package com.beaconfireboba.authserver.domain.token;

import lombok.*;
import org.hibernate.validator.constraints.Email;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ModelAttribute;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Component
public class RegisterToken {
    @NotNull
    @Email(message="Email invalid.")
    @Size(min=1, message="Email must not be empty.")
    private String email;

    @NotNull
    private Integer houseID;

    @NotNull
    private Integer duration;
}
