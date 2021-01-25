package com.beaconfirebobo.authserver.dao.impl;

import com.beaconfirebobo.authserver.dao.AbstractHibernateDAO;
import com.beaconfirebobo.authserver.dao.UserDAO;
import com.beaconfirebobo.authserver.entity.User;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

@Repository("userHibernateDao")
public class UserHibernateDAOImpl extends AbstractHibernateDAO<User> implements UserDAO {
    public UserHibernateDAOImpl() { setClazz(User.class); }

    @Override
    public User getUserById(Integer id) {
        User user = findById(id);
        user.getUserRoles().size();
        return user;
    }

    @Override
    public List<User> getAllUser() {
        List<User> users = findAll();
        users.forEach(u->u.getUserRoles().size());
        return users;
    }

    @Override
    public User addUser(User user) {
        return save(user);
    }

    @Override
    public void updateUser(User user) {
        save(user);
    }

    @Override
    public List<User> getUserByRoleName(String roleName) {
        Session session = getCurrentSession();
        String hql = "select ur.user from UserRole ur where ur.role.roleName=:roleName";
        Query query = session.createQuery(hql);
        query.setParameter("roleName", roleName);
        List<User> users = query.list();
        return users;
    }

    @Override
    public User getUserByName(String userName) {
        Session session = getCurrentSession();
        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery<User> criteriaQuery = criteriaBuilder.createQuery(User.class);
        Root<User> root = criteriaQuery.from(User.class);
        criteriaQuery.select(root).where(criteriaBuilder.equal(root.get("userName"), userName));
        Query<User> query = session.createQuery(criteriaQuery);
        User user = query.uniqueResult();
        return user;
    }
}
