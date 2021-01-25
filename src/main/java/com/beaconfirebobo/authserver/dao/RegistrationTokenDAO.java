package com.beaconfirebobo.authserver.dao;

import com.beaconfirebobo.authserver.entity.RegistrationToken;

public interface RegistrationTokenDAO {
    RegistrationToken getRegistrationTokenWithToken(String token);

    RegistrationToken addRegistrationToken(RegistrationToken token);
}
