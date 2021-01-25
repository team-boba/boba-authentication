package com.beaconfireboba.authserver.dao.impl;

import com.beaconfireboba.authserver.dao.AbstractHibernateDAO;
import com.beaconfireboba.authserver.dao.UserRoleDAO;
import com.beaconfireboba.authserver.entity.UserRole;
import org.springframework.stereotype.Repository;

@Repository("userRoleHibernateDAOImpl")
public class UserRoleHibernateDAOImpl extends AbstractHibernateDAO<UserRole> implements UserRoleDAO {

    @Override
    public UserRole addUserRole(UserRole userRole) {
        return save(userRole);
    }

    @Override
    public void updateUserRole(UserRole userRole) {
        save(userRole);
    }
}
