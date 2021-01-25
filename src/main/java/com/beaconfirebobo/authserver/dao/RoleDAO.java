package com.beaconfirebobo.authserver.dao;


import com.beaconfirebobo.authserver.entity.Role;

import java.util.List;

public interface RoleDAO {
    List<Role> getAllRoles();

    Role getRoleByName(String roleName);
}
