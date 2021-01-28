package com.beaconfireboba.authserver.dao;

import com.beaconfireboba.authserver.entity.RegistrationToken;

public interface RegistrationTokenDAO {
    RegistrationToken addRegistrationToken(RegistrationToken token);
}
