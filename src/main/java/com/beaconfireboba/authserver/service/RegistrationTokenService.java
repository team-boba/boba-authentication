package com.beaconfireboba.authserver.service;

import com.beaconfireboba.authserver.dao.RegistrationTokenDAO;
import com.beaconfireboba.authserver.entity.RegistrationToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class RegistrationTokenService {
    private RegistrationTokenDAO registrationTokenDAO;

    @Transactional
    public RegistrationToken addRegistrationToken(String token, int validDuration, String email, String createdBy){
        RegistrationToken newToken = new RegistrationToken();
        newToken.setToken(token);
        newToken.setEmail(email);
        newToken.setValidDuration(validDuration);
        newToken.setCreateBy(createdBy);
        return registrationTokenDAO.addRegistrationToken(newToken);
    }

    @Autowired
    public void setRegistrationTokenDAO(RegistrationTokenDAO registrationTokenDAO){
        this.registrationTokenDAO = registrationTokenDAO;
    }
}
