package com.beaconfireboba.authserver.domain.user;

import com.beaconfireboba.authserver.domain.common.ServiceStatus;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SerializeUserResponse {
    ServiceStatus serviceStatus;
    SerializeUser serializeUser;
}
