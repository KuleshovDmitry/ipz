package model.dao.impl;

import model.dao.AuthorDao;
import entity.Author;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class AuthorDaoImpl extends DefaultDao<Author> implements AuthorDao {
    private static final Logger log = LogManager.getLogger(AuthorDaoImpl.class.getName());

    public AuthorDaoImpl() {
        setTableName("Author");
    }

    @Override
    public long getId(Author author) {//WARN!! user & book must contain valid id
        log.traceEntry(author.toString());
        Author result = getUniqueObjectFromQuery("" +
                "from Author where " +
                "first_name = \'" + author.getFirstName() + "\' and " +
                "last_name = \'" + author.getLastName() + "\' and " +
                "bio = \'" + author.getBio() + "\'");
        if (result == null) {
            return log.traceExit(0);
        }
        return log.traceExit(result.getId());
    }

    @Transactional
    public List<Author> getByName(String firstName, String lastName) {
        Session session = sessionFactory.getCurrentSession();
        Criteria criteria = session.createCriteria(Author.class);
        if (firstName != "") criteria.add(Restrictions.eq("firstName", firstName));
        if (lastName != "") criteria.add(Restrictions.eq("lastName", lastName));
        return criteria.list();
    }
}
