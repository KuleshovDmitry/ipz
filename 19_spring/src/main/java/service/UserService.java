package service;

import model.dao.UserDao;
import entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserDao userDao;
    public boolean create(User user){
        return userDao.insert(user);
    }
    public boolean delete(User user){
        return userDao.delete(user);
    }
    public boolean delete(long i){
        return userDao.delete(i);
    }
    public boolean update(User user){
        return userDao.update(user);
    }
    public List<User> selectAll(){
        return userDao.selectAll();
    }
    public long getId(User user){
        return userDao.getId(user);
    }
    public User getUser(long id){
        return userDao.getById(id, User.class);
    }
    public long getId(String login){
        return userDao.getIdByLogin(login);
    }
    public User getUser(String login){
        return userDao.getByLogin(login);
    }
}
