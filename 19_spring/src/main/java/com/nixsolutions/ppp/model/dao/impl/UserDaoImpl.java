package com.nixsolutions.ppp.model.dao.impl;

import com.nixsolutions.ppp.config.HibernateUtil;
import com.nixsolutions.ppp.model.dao.UserDao;
import com.nixsolutions.ppp.model.entity.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class UserDaoImpl extends DefaultDao<User> implements UserDao {
    private static final Logger log = LogManager.getLogger(UserDaoImpl.class.getName());

    @Autowired
    private HibernateUtil hibernateUtil;

    public UserDaoImpl() {
        setTableName(User.class.getName());
    }


    @Override
    public long getId(User user) {
        log.traceEntry(user.toString());
        User result = getUniqueObjectFromQuery("" +
                "from User where login = \'" + user.getLogin() + "\' and " +
                "password = \'" + user.getPassword() + "\'");
        if (result == null) {
            return (0);
        }
        return result.getId();
    }

    @Transactional
    public long getIdByLogin(String login) {
        log.traceEntry(login);

        Session session = sessionFactory.getCurrentSession();
        User resultUser = (User) session.createQuery("" +
                "from User where login = \'" + login + "\'").uniqueResult();
        return log.traceExit(resultUser.getId());
    }

    @Transactional
    public boolean delete(String login) {
        Session session = sessionFactory.getCurrentSession();
        session.createQuery("delete from User where login = \'" + login + "\'").executeUpdate();
        return log.traceExit(true);
    }

    @Transactional
    public User getByLogin(String login) {
        log.traceEntry(login);
        User user = (User) hibernateUtil.getSessionFactory().
                openSession().createQuery(
                "From User where login = \'" + login + "\'").uniqueResult();

        return (log.traceExit(user));
    }

    @Override
    protected boolean isValid(User user) {
        return super.isValid(user) &&
                user.getLogin() != null &&
                user.getPassword() != null &&
                user.getRole() != null;
    }

}
