package com.beaconfirebobo.authserver.controller;

import com.beaconfirebobo.authserver.constant.Constant;
import com.beaconfirebobo.authserver.entity.RegistrationToken;
import com.beaconfirebobo.authserver.entity.User;
import com.beaconfirebobo.authserver.service.RegistrationTokenService;
import com.beaconfirebobo.authserver.service.UserService;
import com.beaconfirebobo.authserver.domain.user.LoginUser;
import com.beaconfirebobo.authserver.domain.user.RegisterUser;
import com.beaconfirebobo.authserver.util.CookieUtil;
import com.beaconfirebobo.authserver.util.JwtUtil;
import com.beaconfirebobo.authserver.util.ValidationUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import javax.websocket.server.PathParam;
import java.util.Map;

@Controller
@RequestMapping("/auth")
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private ValidationUtil validationUtil;

    @Autowired
    private RegistrationTokenService registrationTokenService;


    @GetMapping(value="/register/{token}")
    public String registerPage(Model model, @PathVariable("token") String token) {
        String registrationToken = JwtUtil.getSubjectFromToken(token, Constant.SIGNING_KEY);
        if(registrationToken != null){
            model.addAttribute("registerUser", new RegisterUser());
            model.addAttribute("roleNames", userService.getAllRoleNames());
            return "register";
        }
        else{
            System.out.println("cannot find any register token");
            return null;
        }
    }

    @PostMapping(value="/register")
    public String registerUser(HttpServletResponse httpServletResponse, @RequestParam("redirect") String redirect, @ModelAttribute("registerUser") @Valid RegisterUser registerUser,
                               BindingResult result, Model model) {
        Map<String, String> errors = validationUtil.validateRegisterUser(registerUser);
        for (String errorName : errors.keySet()) {
            result.rejectValue(errorName, "error."+errorName, errors.get(errorName));
        }

        if (result.hasErrors()) {
            model.addAttribute("roleNames", userService.getAllRoleNames());
            return "register";
        }

        User newUser = userService.addUser(registerUser);
        String serializeUserJson = userService.serializeUserToJson(newUser);

        String token = JwtUtil.generateToken(serializeUserJson);
        CookieUtil.create(httpServletResponse, Constant.JWT_TOKEN_COOKIE_NAME, token, false, -1, "localhost");

        return "redirect:" + redirect;
    }

    @GetMapping(value="/login")
    public String loginPage(Model model) {
        model.addAttribute("loginUser", new LoginUser());
        return "login";
    }

    @PostMapping(value="/login")
    public String loginUser(HttpServletResponse httpServletResponse, @RequestParam("redirect") String redirect, @ModelAttribute("loginUser") @Valid LoginUser loginUser, BindingResult result) {
        Map<String, String> errors = validationUtil.validateLoginUser(loginUser);
        for (String errorName : errors.keySet()) {
            result.rejectValue(errorName, "error."+errorName, errors.get(errorName));
        }

        if (result.hasErrors()) {
            return "login";
        }

        User user = userService.getUserByName(loginUser.getUserName());
        String serializeUserJson = userService.serializeUserToJson(user);

        String token = JwtUtil.generateToken(serializeUserJson);
        CookieUtil.create(httpServletResponse, Constant.JWT_TOKEN_COOKIE_NAME, token, false, -1, "localhost");

        return "redirect:" + redirect;
    }

    @GetMapping(value="/logout")
    public String logoutUser(HttpServletResponse httpServletResponse, @RequestParam("redirect") String redirect) {
        CookieUtil.clear(httpServletResponse, Constant.JWT_TOKEN_COOKIE_NAME);
        return "redirect:" + redirect;
    }
}
