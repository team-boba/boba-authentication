package com.beaconfireboba.authserver.dao;


import com.beaconfireboba.authserver.entity.UserRole;

public interface UserRoleDAO {
    UserRole addUserRole(UserRole userRole);

    void updateUserRole(UserRole userRole);
}
