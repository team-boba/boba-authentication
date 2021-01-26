/**
 * Just class just for test*/

package com.beaconfireboba.authserver.controller;

import com.beaconfireboba.authserver.constant.Constant;
import com.beaconfireboba.authserver.domain.token.RegisterToken;
import com.beaconfireboba.authserver.domain.token.RegisterTokenResponse;
import com.beaconfireboba.authserver.domain.user.RegisterUser;
import com.beaconfireboba.authserver.entity.RegistrationToken;
import com.beaconfireboba.authserver.service.RegistrationTokenService;
import com.beaconfireboba.authserver.security.util.JwtUtil;
import com.beaconfireboba.authserver.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.imageio.spi.RegisterableService;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.Date;


@Controller
@RequestMapping("/hr")
public class HireController {

    private RegistrationTokenService registrationTokenService;

    @Autowired
    private RegistrationTokenService tokenService;

    @Autowired
    public HireController(RegistrationTokenService registrationTokenService){
        this.registrationTokenService = registrationTokenService;
    }




    @PostMapping(value="/getToken")
    @ResponseBody
    public RegisterTokenResponse generateToken(@RequestBody RegisterTokenResponse registerTokenResponse){
        RegisterToken registerToken = registerTokenResponse.getRegisterToken();
        String serializedRegisterToken = tokenService.serializeTokenToJson(registerToken);
        String token = JwtUtil.generateToken(serializedRegisterToken);
        System.out.println(registerToken.getDuration());
        System.out.println(registerToken.getEmail());
        registrationTokenService.addRegistrationTokenToDB(token, registerToken.getDuration(), registerToken.getEmail());

        return registerTokenResponse;
    }

}
