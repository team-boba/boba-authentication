/**
 * Just class just for test*/

package com.beaconfireboba.authserver.controller;

import com.beaconfireboba.authserver.domain.user.RegisterUser;
import com.beaconfireboba.authserver.entity.RegistrationToken;
import com.beaconfireboba.authserver.service.RegistrationTokenService;
import com.beaconfireboba.authserver.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.Date;


@Controller
@RequestMapping("/hr")
public class HireController {
    private static final String signingKey = "signingKey";
    private RegistrationTokenService registrationTokenService;

    @Autowired
    public HireController(RegistrationTokenService registrationTokenService){
        this.registrationTokenService = registrationTokenService;
    }

    @GetMapping(value="/hire")
    public String hirePage() {
        return "hire";
    }


    /**
     * Notice:
     * Jwt token only wrap user email.
     * email can be null.
     * Valid duration 10 minutes.
     * */
    @PostMapping(value="/getToken")
    public String generateToken(Model model, HttpServletResponse httpServletResponse,String email){

        String token = JwtUtil.generateToken(email);
        model.addAttribute("notification", "successful generated token");
        System.out.println(email);
        registrationTokenService.addRegistrationTokenToDB(token, 10, email);

        return "successful";
    }

}
