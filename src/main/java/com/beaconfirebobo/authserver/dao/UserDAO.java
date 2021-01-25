package com.beaconfirebobo.authserver.dao;

import com.beaconfirebobo.authserver.entity.User;

import java.util.List;

public interface UserDAO {
    User getUserById(Integer id);

    List<User> getAllUser();

    User addUser(User user);

    void updateUser(User user);

    List<User> getUserByRoleName(String roleName);

    User getUserByName(String userName);
}
