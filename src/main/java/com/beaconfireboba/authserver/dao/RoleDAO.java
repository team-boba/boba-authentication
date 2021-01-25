package com.beaconfireboba.authserver.dao;


import com.beaconfireboba.authserver.entity.Role;

import java.util.List;

public interface RoleDAO {
    List<Role> getAllRoles();

    Role getRoleByName(String roleName);
}
