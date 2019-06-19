package model.dao.impl;

import model.dao.RentedBookDao;
import entity.RentedBook;
import entity.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Repository
public class RentedBookDaoImpl extends DefaultDao<RentedBook> implements RentedBookDao {
    private static final Logger log = LogManager.getLogger(RentedBookDaoImpl.class.getName());

    public RentedBookDaoImpl() {
        setTableName("RentedBook");
    }

    @Override
    @Transactional
    public long getId(RentedBook rentedBook) {
        log.traceEntry(rentedBook.toString());
        Session session = sessionFactory.getCurrentSession();
        Criteria criteria = session.createCriteria(RentedBook.class);
        criteria.add(Restrictions.eq("user", rentedBook.getUser()));
        criteria.add(Restrictions.eq("book", rentedBook.getBook()));
        criteria.add(Restrictions.eq("dateTaken", rentedBook.getDateTaken()));
        criteria.add(Restrictions.eq("mustBeReturnedDate", rentedBook.getMustBeReturnedDate()));
        if ((RentedBook)criteria.uniqueResult() == null)
            return 0;
        else return ((RentedBook) criteria.uniqueResult()).getId();
    }

    @Override
    public List<RentedBook> selectByUser(User user) {
        log.traceEntry(user.toString());
        Session session = sessionFactory.getCurrentSession();
        Criteria criteria = session.createCriteria(RentedBook.class);
        criteria.add(Restrictions.eq("user", user));
        if (criteria.list() == null || criteria.list().size()==0){
            System.out.println(0);
            return new ArrayList<>();}
        else {
            System.out.println(1+""+criteria.list().size());return criteria.list();}
    }
}
