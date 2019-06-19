
package model.dao.impl;

import model.dao.AuthorDao;
import model.dao.BookDao;
import entity.Book;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class BookDaoImpl extends DefaultDao<Book> implements BookDao {

    private static final Logger log = LogManager.getLogger(BookDaoImpl.class.getName());

    @Autowired
    private AuthorDao authorDao;

    public BookDaoImpl() {
        setTableName("Book");
    }

    @Override
    public long getId(Book book) {//WARN!! user & book must contain valid id
        log.traceEntry(book.toString());
        Book result = getUniqueObjectFromQuery("" +
                "from Book where " +
                "name = \'" + book.getName() + "\' and " +
                "year = " + book.getYear() +
                " and isbn = " + book.getIsbn() +
                " and  author_id = " + book.getAuthor().getId() +
                " and  genre_id = " + book.getGenre().getId());
        if (result == null) {
            return log.traceExit(0);
        }
        return log.traceExit(result.getId());

    }

    public List<Book> findByName(String name) {
        log.traceEntry(name);
        String query = "SELECT * FROM book WHERE name ='" + name + "';";

        return log.traceExit(executeWithResultList(query));
    }

    @Override
    @Transactional
    public List<Book> find(String bookName, String firstName, String lastName) {
        log.traceEntry(bookName + firstName + lastName);

        Session session = sessionFactory.getCurrentSession();
        Criteria criteria = session.createCriteria(Book.class);
        if (bookName != "") criteria.add(Restrictions.eq("name", bookName));
        criteria.createAlias("author", "author");
        if (firstName != "") criteria.add(Restrictions.eq("author.firstName", firstName));
        if (lastName != "") criteria.add(Restrictions.eq("author.lastName", lastName));
        List result = criteria.list();
        return log.traceExit(result);
    }
    @Override
    @Transactional
    public List<Book> findAvailable(String bookName, String firstName, String lastName) {
        log.traceEntry(bookName + firstName + lastName);

        Session session = sessionFactory.getCurrentSession();
        Criteria criteria = session.createCriteria(Book.class);
        if (bookName != "") criteria.add(Restrictions.eq("name", bookName));
        criteria.add(Restrictions.eq("available", true));
        criteria.createAlias("author", "author");
        if (firstName != "") criteria.add(Restrictions.eq("author.firstName", firstName));
        if (lastName != "") criteria.add(Restrictions.eq("author.lastName", lastName));
        List result = criteria.list();
        return log.traceExit(result);
    }
}
