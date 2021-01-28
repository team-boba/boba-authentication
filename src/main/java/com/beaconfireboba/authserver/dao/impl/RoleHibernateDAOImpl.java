package com.beaconfireboba.authserver.dao.impl;

import com.beaconfireboba.authserver.dao.AbstractHibernateDAO;
import com.beaconfireboba.authserver.dao.RoleDAO;
import com.beaconfireboba.authserver.entity.Role;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

@Repository("roleHibernateDao")
public class RoleHibernateDAOImpl extends AbstractHibernateDAO<Role> implements RoleDAO {
    public RoleHibernateDAOImpl() { setClazz(Role.class); }

    @Override
    public List<Role> getAllRoles() {
        return findAll();
    }

    @Override
    public Role getRoleByName(String roleName) {
        Session session = getCurrentSession();
        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery<Role> criteriaQuery = criteriaBuilder.createQuery(Role.class);
        Root<Role> root = criteriaQuery.from(Role.class);
        criteriaQuery.select(root).where(criteriaBuilder.equal(root.get("roleName"), roleName));
        Query<Role> query = session.createQuery(criteriaQuery);
        Role role = query.uniqueResult();
        return role;
    }

    @Override
    public Role getRoleById(int id) { return findById(id); }
}
