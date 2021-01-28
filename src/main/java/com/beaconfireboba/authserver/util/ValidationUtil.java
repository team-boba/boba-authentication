package com.beaconfireboba.authserver.util;

import com.beaconfireboba.authserver.domain.user.LoginUser;
import com.beaconfireboba.authserver.domain.user.RegisterUser;
import com.beaconfireboba.authserver.entity.User;
import com.beaconfireboba.authserver.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class ValidationUtil {
    @Autowired
    private UserService userService;

    public Map<String, String> validateLoginUser(LoginUser loginUser) {
        Map<String, String> errors = new HashMap<>();
        User user = userService.getUserByName(loginUser.getUserName());
        if (user==null) {
            errors.put("userName", "User name not found.");
            return errors;
        } else {
            if (!loginUser.getPassword().equals(user.getPassword())) {
                errors.put("password", "Password incorrect.");
            }
        }

        return errors;
    }

    public Map<String, String> validateRegisterUser(RegisterUser registerUser) {
        Map<String, String> errors = new HashMap<>();

        User user = userService.getUserByName(registerUser.getUserName());
        if (user != null) {
            errors.put("userName", "User name already exists.");
        }

        return errors;
    }
}
