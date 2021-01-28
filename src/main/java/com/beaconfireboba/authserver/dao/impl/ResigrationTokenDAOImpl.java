package com.beaconfireboba.authserver.dao.impl;

import com.beaconfireboba.authserver.dao.AbstractHibernateDAO;
import com.beaconfireboba.authserver.dao.RegistrationTokenDAO;
import com.beaconfireboba.authserver.entity.RegistrationToken;
import com.beaconfireboba.authserver.entity.Role;
import com.beaconfireboba.authserver.entity.User;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

@Repository("RegistrationTokenHibernateDAO")
public class ResigrationTokenDAOImpl extends AbstractHibernateDAO<RegistrationToken> implements RegistrationTokenDAO {
    public ResigrationTokenDAOImpl() { setClazz(RegistrationToken.class); }

    @Override
    public RegistrationToken addRegistrationToken(RegistrationToken token) {
        return save(token);
    }
}
