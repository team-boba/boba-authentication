package com.beaconfireboba.authserver.controller;

import com.beaconfireboba.authserver.constant.Constant;
import com.beaconfireboba.authserver.domain.common.ServiceStatus;
import com.beaconfireboba.authserver.domain.token.HrRegisterTokenRequest;
import com.beaconfireboba.authserver.domain.token.JwtRequest;
import com.beaconfireboba.authserver.domain.user.SerializeUser;
import com.beaconfireboba.authserver.domain.user.SerializeUserResponse;
import com.beaconfireboba.authserver.entity.User;
import com.beaconfireboba.authserver.service.UserService;
import com.beaconfireboba.authserver.domain.user.LoginUser;
import com.beaconfireboba.authserver.domain.user.RegisterUser;
import com.beaconfireboba.authserver.security.util.CookieUtil;
import com.beaconfireboba.authserver.security.util.JwtUtil;
import com.beaconfireboba.authserver.util.ValidationUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.Map;

@Controller
@RequestMapping("/auth")
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private ValidationUtil validationUtil;

    @GetMapping(value="/register")
    public String registerPage(Model model, @RequestParam("jwt") String token) {
        if (token==null) {
            model.addAttribute("tokenErrorMessage", "Your registration token is invalid, please contact HR");
            return "register";
        }

        String serializedHrRegisterTokenRequest = JwtUtil.getSubjectFromToken(token, Constant.SIGNING_KEY);

        if(serializedHrRegisterTokenRequest != null){
            try {
                HrRegisterTokenRequest hrRegisterTokenRequest = new ObjectMapper().readValue(serializedHrRegisterTokenRequest, HrRegisterTokenRequest.class);
                RegisterUser registerUser = new RegisterUser();
                registerUser.setEmail(hrRegisterTokenRequest.getEmail());
                registerUser.setHouseId(hrRegisterTokenRequest.getHouseId());
                model.addAttribute("registerUser", registerUser);
                return "register";
            } catch (JsonProcessingException e) {
                model.addAttribute("registerUser", new RegisterUser());
                model.addAttribute("tokenErrorMessage", "Your registration token is invalid, please contact HR");
                return "register";
            }
        } else{
            model.addAttribute("registerUser", new RegisterUser());
            model.addAttribute("tokenErrorMessage", "Your registration token is invalid, please contact HR");
            return "register";
        }
    }

    @PostMapping(value="/register")
    public String registerUser(HttpServletResponse httpServletResponse,
       @ModelAttribute("registerUser") @Valid RegisterUser registerUser,
       BindingResult result) {
        if (result.hasErrors()) {
            return "register";
        }

        Map<String, String> errors = validationUtil.validateRegisterUser(registerUser);
        for (String errorName : errors.keySet()) {
            result.rejectValue(errorName, "error."+errorName, errors.get(errorName));
        }

        if (result.hasErrors()) {
            return "register";
        }

        User newUser = userService.addUser(registerUser);
        String serializeUserJson = userService.serializeUserToJson(newUser);

        String token = JwtUtil.generateToken(serializeUserJson, Constant.JWT_LOGIN_EXPIRE_MINUTES);
        CookieUtil.create(httpServletResponse, Constant.JWT_TOKEN_COOKIE_NAME, token, false, -1, "localhost");

        return "redirect:" + "http://localhost:4200/employee/" + newUser.getId() + "/" + registerUser.getHouseId();
    }

    @GetMapping(value="/login")
    public String loginPage(Model model) {
        model.addAttribute("loginUser", new LoginUser());
        return "login";
    }

    @PostMapping(value="/login")
    public String loginUser(HttpServletResponse httpServletResponse, @ModelAttribute("loginUser") @Valid LoginUser loginUser, BindingResult result) {
        if (result.hasErrors()) {
            return "login";
        }

        Map<String, String> errors = validationUtil.validateLoginUser(loginUser);
        for (String errorName : errors.keySet()) {
            result.rejectValue(errorName, "error."+errorName, errors.get(errorName));
        }

        if (result.hasErrors()) {
            return "login";
        }

        User user = userService.getUserByName(loginUser.getUserName());
        String serializeUserJson = userService.serializeUserToJson(user);

        String token = JwtUtil.generateToken(serializeUserJson, Constant.JWT_LOGIN_EXPIRE_MINUTES);
        CookieUtil.create(httpServletResponse, Constant.JWT_TOKEN_COOKIE_NAME, token, false, -1, "localhost");

        if (user.getUserRoles().stream().anyMatch(r->r.getRole().getRoleName().equals("hr"))) {
            return "redirect:" + "http://localhost:4200/hr" ;
        } else {
            return "redirect:" + "http://localhost:4200/employee/" + user.getId();
        }
    }

    @GetMapping(value="/logout")
    public String logoutUser(HttpServletResponse httpServletResponse) {
        CookieUtil.clear(httpServletResponse, Constant.JWT_TOKEN_COOKIE_NAME);
        return "redirect:" + "http://localhost:9999/auth/login" ;
    }

    @PostMapping(value="/deserialize-jwt")
    @ResponseBody
    public SerializeUserResponse deserializeJwt(@RequestBody JwtRequest jwtRequest) {
        SerializeUserResponse serializeUserResponse = new SerializeUserResponse();
        String serializeUserJson = JwtUtil.getSubjectFromToken(jwtRequest.getJwt(), Constant.SIGNING_KEY);

        if (serializeUserJson == null) {
            prepareSerializeUserResponse(serializeUserResponse, false, "JWT invalid.");
            return serializeUserResponse;
        }

        try {
            SerializeUser serializeUser = new ObjectMapper().readValue(serializeUserJson, SerializeUser.class);
            serializeUserResponse.setSerializeUser(serializeUser);
            prepareSerializeUserResponse(serializeUserResponse, true, "");
            return serializeUserResponse;
        } catch (Exception e) {
            e.printStackTrace();
        }
        prepareSerializeUserResponse(serializeUserResponse, false, "JWT invalid.");
        return serializeUserResponse;
    }

    @PostMapping(value="/is-authenticated-user")
    @ResponseBody
    public boolean isAuthenticatedUser(HttpServletResponse httpServletResponse, @RequestBody JwtRequest jwtRequest) {
        String serializeUserJson = JwtUtil.getSubjectFromToken(jwtRequest.getJwt(), Constant.SIGNING_KEY);

        if (serializeUserJson == null) {
            return false;
        }

        try {
            SerializeUser serializeUser = new ObjectMapper().readValue(serializeUserJson, SerializeUser.class);
            if (!serializeUser.isHr() && serializeUser.getUserId()!=jwtRequest.getUserId()) {
                CookieUtil.clear(httpServletResponse, Constant.JWT_TOKEN_COOKIE_NAME);
                return false;
            } else {
                return true;
            }
        } catch (Exception e) {
            return false;
        }
    }

    @PostMapping(value="/is-authorized-user")
    @ResponseBody
    public boolean isAuthorizedUser(HttpServletResponse httpServletResponse, @RequestBody JwtRequest jwtRequest) {
        String serializeUserJson = JwtUtil.getSubjectFromToken(jwtRequest.getJwt(), Constant.SIGNING_KEY);

        if (serializeUserJson == null) {
            return false;
        }

        try {
            SerializeUser serializeUser = new ObjectMapper().readValue(serializeUserJson, SerializeUser.class);
            if (!serializeUser.isHr()) {
                CookieUtil.clear(httpServletResponse, Constant.JWT_TOKEN_COOKIE_NAME);
                return false;
            } else {
                return true;
            }
        } catch (Exception e) {
            return false;
        }
    }

    private void prepareSerializeUserResponse(SerializeUserResponse response, boolean success, String errorMessage) {
        response.setServiceStatus(new ServiceStatus(success ? "SUCCESS" : "FAILED", success, errorMessage));
    }
}
