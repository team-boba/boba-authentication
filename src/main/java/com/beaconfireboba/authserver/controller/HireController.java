package com.beaconfireboba.authserver.controller;

import com.beaconfireboba.authserver.domain.token.HrRegisterTokenRequest;
import com.beaconfireboba.authserver.service.RegistrationTokenService;
import com.beaconfireboba.authserver.security.util.JwtUtil;
import com.beaconfireboba.authserver.util.MailUtil;
import com.beaconfireboba.authserver.util.SerializeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping("/hr")
public class HireController {

    private SerializeUtil serializeUtil;
    private RegistrationTokenService registrationTokenService;
    private MailUtil mailUtil;

    @Async
    @PostMapping(value="/generateToken")
    @ResponseBody
    public void generateToken(@RequestBody HrRegisterTokenRequest hrRegisterTokenRequest){
        String serializedHrRegisterTokenRequest = serializeUtil.serialize(hrRegisterTokenRequest);
        String token = JwtUtil.generateToken(serializedHrRegisterTokenRequest, hrRegisterTokenRequest.getValidDuration());

        registrationTokenService.addRegistrationToken(token, hrRegisterTokenRequest.getValidDuration()
            , hrRegisterTokenRequest.getEmail(), "admin");

        mailUtil.sendGeneralMail(hrRegisterTokenRequest.getEmail(), "Notice from hr please register",
            "http://localhost:9999/auth/register?jwt=" + token);
    }

    @Autowired
    public void setSerializeUtil(SerializeUtil serializeUtil) {
        this.serializeUtil = serializeUtil;
    }

    @Autowired
    public void setRegistrationTokenService(RegistrationTokenService registrationTokenService) {
        this.registrationTokenService = registrationTokenService;
    }

    @Autowired
    public void setMailUtil(MailUtil mailUtil) {
        this.mailUtil = mailUtil;
    }
}

