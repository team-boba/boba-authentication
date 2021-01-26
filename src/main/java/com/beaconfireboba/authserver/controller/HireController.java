/**
 * Just class just for test*/

package com.beaconfireboba.authserver.controller;

import com.beaconfireboba.authserver.constant.Constant;
import com.beaconfireboba.authserver.domain.token.RegisterToken;
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

    @GetMapping(value="/hire")
    public String hirePage(Model model) {
        model.addAttribute("registerToken", new RegisterToken());
        return "hire";
    }


    /**
     * Notice:
     * Jwt token only wrap user email.
     * email can be null.
     * Valid duration 10 minutes.
     * */
    @PostMapping(value="/getToken")
    public String generateToken(Model model, HttpServletResponse httpServletResponse,String email,@ModelAttribute("registerToken") @Valid RegisterToken registerToken){

        String serializedRegisterToken = tokenService.serializeTokenToJson(registerToken);
        String token = JwtUtil.generateToken(serializedRegisterToken);
        model.addAttribute("notification", "successful generated token");
        System.out.println(email);
        registrationTokenService.addRegistrationTokenToDB(token, Constant.JWT_EXPIRE_MINUTES, email);

        return "successful";
    }

}
