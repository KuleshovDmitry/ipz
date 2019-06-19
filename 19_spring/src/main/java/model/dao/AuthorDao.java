package model.dao;

import entity.Author;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
public interface AuthorDao extends Dao<Author> {
    public List<Author> getByName(String firstName, String lastName);
}
