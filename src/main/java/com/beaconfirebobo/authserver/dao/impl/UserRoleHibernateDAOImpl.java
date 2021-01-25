package com.beaconfirebobo.authserver.dao.impl;

import com.beaconfirebobo.authserver.dao.AbstractHibernateDAO;
import com.beaconfirebobo.authserver.dao.UserRoleDAO;
import com.beaconfirebobo.authserver.entity.UserRole;
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
