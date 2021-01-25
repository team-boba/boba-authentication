package com.beaconfireboba.authserver.service;

import com.beaconfireboba.authserver.dao.RoleDAO;
import com.beaconfireboba.authserver.dao.UserDAO;
import com.beaconfireboba.authserver.dao.UserRoleDAO;
import com.beaconfireboba.authserver.domain.user.RegisterUser;
import com.beaconfireboba.authserver.domain.user.SerializeUser;
import com.beaconfireboba.authserver.entity.Role;
import com.beaconfireboba.authserver.entity.User;
import com.beaconfireboba.authserver.entity.UserRole;
import com.beaconfireboba.authserver.util.DateUtil;
import com.beaconfireboba.authserver.util.IdUtil;
import com.beaconfireboba.authserver.util.SerializeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {
    private UserDAO userDAO;
    private RoleDAO roleDAO;
    private UserRoleDAO userRoleDAO;
    private DateUtil dateUtil;
    private IdUtil idUtil;
    private SerializeUtil serializeUtil;

    @Transactional
    public User getUserByName(String userName) {
        return userDAO.getUserByName(userName);
    }

    @Transactional
    public User addUser(RegisterUser registerUser) {
        User user = new User();
        user.setUserName(registerUser.getUserName());
        user.setPassword(registerUser.getPassword());
        user.setEmail(registerUser.getEmail());
        user.setCreateDate(dateUtil.getCurrentDate());
        user.setModificationDate(dateUtil.getCurrentDate());
        user.setPersonId(idUtil.generateUUID());
        User newUser = userDAO.addUser(user);

        for (String roleName : registerUser.getRoleNames()) {
            UserRole userRole = new UserRole();
            userRole.setActiveFlag(true);
            userRole.setCreateDate(dateUtil.getCurrentDate());
            userRole.setModificationDate(dateUtil.getCurrentDate());
            Role role = roleDAO.getRoleByName(roleName);
            userRole.setUser(newUser);
            userRole.setRole(role);
            userRoleDAO.addUserRole(userRole);
        }

        return newUser;
    }

    @Transactional
    public List<String> getAllRoleNames() {
        return this.roleDAO.getAllRoles().stream().map(r->r.getRoleName()).collect(Collectors.toList());
    }

    public String serializeUserToJson(User user) {
        SerializeUser serializeUser = new SerializeUser();
        serializeUser.setUserName(user.getUserName());
        serializeUser.setEmail(user.getEmail());
        serializeUser.setPersonId(user.getPersonId());

        return serializeUtil.serialize(serializeUser);
    }

    @Autowired
    @Qualifier("userHibernateDao")
    public void setUserDAO(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    @Autowired
    @Qualifier("roleHibernateDao")
    public void setRoleDAO(RoleDAO roleDAO) {
        this.roleDAO = roleDAO;
    }

    @Autowired
    public void setUserRoleDAO(UserRoleDAO userRoleDAO) {
        this.userRoleDAO = userRoleDAO;
    }

    @Autowired
    public void setDateUtil(DateUtil dateUtil) {
        this.dateUtil = dateUtil;
    }

    @Autowired
    public void setIdUtil(IdUtil idUtil) {
        this.idUtil = idUtil;
    }

    @Autowired
    public void setSerializeUtil(SerializeUtil serializeUtil) {
        this.serializeUtil = serializeUtil;
    }
}
