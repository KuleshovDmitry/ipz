package model.dao;

import entity.RentedBook;
import entity.User;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
public interface RentedBookDao {
    public boolean insert(RentedBook rentedBook);
    public boolean delete(RentedBook rentedBook);
    public boolean delete(long i);
    public boolean update(RentedBook rentedBook);
    public List<RentedBook> executeWithResultList(String query);
    public List<RentedBook> selectAll();
    public long getId(RentedBook rentedBook);
    public RentedBook getById(long id, Class objClass);
    public List<RentedBook> selectByUser(User user);
}
