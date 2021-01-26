package com.beaconfireboba.authserver.domain.token;

import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class SerializeRegisterToken implements Serializable {
    private String email;
    private Integer houseID;
}
