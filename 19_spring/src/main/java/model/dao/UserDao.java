package model.dao;

import entity.User;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
public interface UserDao {
    public boolean insert(User user);
    public boolean delete(User user);
    public boolean delete(long i);
    public boolean update(User user);
    public List<User> executeWithResultList(String query);
    public List<User> selectAll();
    public long getId(User user);
    public User getById(long id, Class objClass);
    public long getIdByLogin(String login);
    public User getByLogin(String login);
}
