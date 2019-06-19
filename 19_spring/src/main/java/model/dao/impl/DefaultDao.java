package model.dao.impl;

import configration.HibernateUtil;
import model.dao.Dao;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public abstract class DefaultDao<T> implements Dao<T> {
    private static final Logger log = LogManager.getLogger(DefaultDao.class.getName());
    private String tableName;
    @Autowired
    private HibernateUtil hibernateUtil;
    @Autowired
    protected SessionFactory sessionFactory;

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    @Override
    @Transactional
    public boolean insert(T t) {
        if (!isValid(t) || getId(t) > 0) {
            return false;
        } else {
            Session session = sessionFactory.getCurrentSession();
            session.save(t);
            return log.traceExit(true);
        }
    }

    @Override
    @Transactional
    public boolean delete(T t) {
        log.traceEntry(t.toString());

        if (getId(t) == 0) {
            return log.traceExit(false);
        }
        delete(getId(t));
        return log.traceExit(true);
    }

    @Transactional
    @Override
    public boolean delete(long id) {
        log.traceEntry("" + id);

        Session session = sessionFactory.getCurrentSession();
        session.createQuery("delete from " + tableName + " where id = " + id).executeUpdate();

        return log.traceExit(true);
    }

    @Override
    @Transactional
    public boolean update(T t) {
        log.traceEntry("" + t);
        if (!isValid(t)) {
            return log.traceExit(false);
        }

        Session session = sessionFactory.getCurrentSession();
        session.update(t);
        return log.traceExit(true);
    }

    @Override
    @Transactional
    public List<T> executeWithResultList(String query) {
        log.traceEntry(query);

        Session session = sessionFactory.getCurrentSession();
        List<T> result = session.createQuery(query).list();
        return log.traceExit(result);

    }

    @Override
    @Transactional
    public List<T> selectAll() {
        log.traceEntry();
        return log.traceExit(executeWithResultList("from " + tableName));
    }

    @Override
    @Transactional
    public T getById(long id, Class objClass) {
           log.trace("" + id);
        T result;

        Session session = sessionFactory.getCurrentSession();
        result = (T) session.get(objClass, id);
        return log.traceExit(result);
    }

    protected boolean isValid(T t) {
        return (t != null);
    }

    @Transactional
    protected T getUniqueObjectFromQuery(String query) {
        log.traceEntry(query.toString());

        Session session = sessionFactory.getCurrentSession();
        T result = (T) session.createQuery(query).uniqueResult();
        return log.traceExit(result);
    }
}