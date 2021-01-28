package com.beaconfireboba.authserver.domain.token;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

@Getter
@Setter
@ToString
public class HrRegisterTokenRequest implements Serializable {
    private String email;
    private int validDuration;
    private int houseId;
}
