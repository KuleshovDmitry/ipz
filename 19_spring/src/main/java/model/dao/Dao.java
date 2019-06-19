package model.dao;


import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository
public interface Dao<T> {
    public boolean insert(T t);
    public boolean delete(T t);
    public boolean delete(long i);
    public boolean update(T t);
    public List<T> executeWithResultList(String query);
    public List<T> selectAll();
    public long getId(T t);
    public T getById(long id, Class objClass);
}
