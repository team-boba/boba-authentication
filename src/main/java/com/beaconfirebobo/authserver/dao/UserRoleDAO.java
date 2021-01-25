package com.beaconfirebobo.authserver.dao;


import com.beaconfirebobo.authserver.entity.UserRole;

public interface UserRoleDAO {
    UserRole addUserRole(UserRole userRole);

    void updateUserRole(UserRole userRole);
}
