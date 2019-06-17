package com.nixsolutions.ppp.model.dao;

import com.nixsolutions.ppp.model.entity.Author;
import com.nixsolutions.ppp.model.entity.Book;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
public interface BookDao extends Dao<Book> {
    public List<Book> find(String bookName, String authorFirstName, String authorLastName);
    public List<Book> findAvailable(String bookName, String authorFirstName, String authorLastName);
}
