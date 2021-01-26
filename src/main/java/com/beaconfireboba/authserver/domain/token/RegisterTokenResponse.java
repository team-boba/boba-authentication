package com.beaconfireboba.authserver.domain.token;

import com.beaconfireboba.authserver.domain.common.ServiceStatus;
import com.beaconfireboba.authserver.domain.token.RegisterToken;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@JsonInclude(JsonInclude.Include.NON_NULL)
public class RegisterTokenResponse {
    private ServiceStatus serviceStatus;

    private RegisterToken registerToken;
}