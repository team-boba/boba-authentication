package com.beaconfireboba.authserver.domain.token;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class JwtRequest {
    private String jwt;
    private int userId;
}
