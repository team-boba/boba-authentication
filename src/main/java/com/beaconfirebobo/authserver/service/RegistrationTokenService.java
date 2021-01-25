package com.beaconfirebobo.authserver.service;

import com.beaconfirebobo.authserver.dao.RegistrationTokenDAO;
import com.beaconfirebobo.authserver.entity.RegistrationToken;
import com.beaconfirebobo.authserver.util.DateUtil;
import com.beaconfirebobo.authserver.util.IdUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class RegistrationTokenService {
    private RegistrationTokenDAO registrationTokenDAO;
    private DateUtil dateUtil;

    @Transactional
    public RegistrationToken getRegistrationTokenByToken(String token){
        return registrationTokenDAO.getRegistrationTokenWithToken(token);
    }

    @Transactional
    public RegistrationToken addRegistrationTokenToDB(String token, int duration, String email){
        RegistrationToken newToken = new RegistrationToken();
        newToken.setToken(token);
        newToken.setEmail(email);
        newToken.setValidDuration(duration);
        newToken.setCreateBy(dateUtil.getCurrentDate());
        return registrationTokenDAO.addRegistrationToken(newToken);
    }

    @Autowired
    public void setDateUtil(DateUtil dateUtil) {
        this.dateUtil = dateUtil;
    }

    @Autowired
    public void setRegistrationTokenDAO(RegistrationTokenDAO registrationTokenDAO){
        this.registrationTokenDAO = registrationTokenDAO;
    }
}
