package com.beaconfireboba.authserver.service;

import com.beaconfireboba.authserver.dao.RegistrationTokenDAO;
import com.beaconfireboba.authserver.domain.token.RegisterToken;
import com.beaconfireboba.authserver.domain.token.SerializeRegisterToken;
import com.beaconfireboba.authserver.domain.user.SerializeUser;
import com.beaconfireboba.authserver.entity.RegistrationToken;
import com.beaconfireboba.authserver.entity.User;
import com.beaconfireboba.authserver.util.DateUtil;
import com.beaconfireboba.authserver.util.IdUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.beaconfireboba.authserver.util.SerializeUtil;

import javax.transaction.Transactional;

@Service
public class RegistrationTokenService {
    private RegistrationTokenDAO registrationTokenDAO;
    private DateUtil dateUtil;
    private SerializeUtil serializeUtil;

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

    public String serializeTokenToJson(RegisterToken token) {
        SerializeRegisterToken serializeRegisterToken = new SerializeRegisterToken();
        serializeRegisterToken.setHouseID(token.getHouseID());
        serializeRegisterToken.setEmail(token.getEmail());
        return serializeUtil.serialize(serializeRegisterToken);

    }

    @Autowired
    public void setSerializeUtil(SerializeUtil serializeUtil) {
        this.serializeUtil = serializeUtil;
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
