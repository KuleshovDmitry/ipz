package com.nixsolutions.ppp.service;

import com.nixsolutions.ppp.model.dao.AuthorDao;
import com.nixsolutions.ppp.model.dao.BookDao;
import com.nixsolutions.ppp.model.dao.impl.AuthorDaoImpl;
import com.nixsolutions.ppp.model.dao.impl.BookDaoImpl;
import com.nixsolutions.ppp.model.entity.Author;
import com.nixsolutions.ppp.model.entity.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BookService {
    @Autowired
    private BookDao bookDao;
    @Autowired
    private AuthorDao authorDao;

    public boolean create(Book book) {
        return bookDao.insert(book);
    }

    public boolean delete(Book book) {
        return bookDao.delete(book);
    }

    public boolean delete(long i) {
        return bookDao.delete(i);
    }

    public boolean update(Book book) {
        return bookDao.update(book);
    }

    public List<Book> selectAll() {
        return bookDao.selectAll();
    }

    public long getId(Book book) {
        return bookDao.getId(book);
    }

    public Book getBook(long id) {
        return bookDao.getById(id, Book.class);
    }

    public List<Book> findAvailable(String authorFirstName, String authorLastName, String bookName) {
        List<Author> authors = authorDao.getByName(authorFirstName, authorLastName);
        List<Book> resultList = new ArrayList<>();
        resultList.addAll(bookDao.findAvailable(bookName, authorFirstName, authorLastName));
        return resultList;
    }
}
