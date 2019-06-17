package com.nixsolutions.ppp.model.dao;

import com.nixsolutions.ppp.model.entity.Author;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
public interface AuthorDao extends Dao<Author> {
    public List<Author> getByName(String firstName, String lastName);
}
