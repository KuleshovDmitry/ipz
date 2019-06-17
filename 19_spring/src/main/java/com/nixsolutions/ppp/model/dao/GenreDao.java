package com.nixsolutions.ppp.model.dao;

import com.nixsolutions.ppp.model.entity.Genre;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Transactional
public interface GenreDao {
    public boolean insert(Genre genre);
    public boolean delete(Genre genre);
    public boolean delete(long i);
    public boolean update(Genre genre);
    public List<Genre> executeWithResultList(String query);
    public List<Genre> selectAll();
    public long getId(Genre genre);
    public Genre getById(long id, Class objClass);
}
