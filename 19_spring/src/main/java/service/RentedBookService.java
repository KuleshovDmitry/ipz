package service;

import model.dao.RentedBookDao;
import entity.RentedBook;
import entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class RentedBookService {
    @Autowired
    private RentedBookDao rentedBookDao;
    public boolean create(RentedBook rentedBook){
        return rentedBookDao.insert(rentedBook);
    }
    public boolean delete(RentedBook rentedBook){
        return rentedBookDao.delete(rentedBook);
    }
    public boolean delete(long i){
        return rentedBookDao.delete(i);
    }
    public boolean update(RentedBook rentedBook){
        return rentedBookDao.update(rentedBook);
    }
    public List<RentedBook> selectAll(){
        return rentedBookDao.selectAll();
    }
    public long getId(RentedBook rentedBook){
        return rentedBookDao.getId(rentedBook);
    }
    public RentedBook getRentedBook(long id){
        return rentedBookDao.getById(id, RentedBook.class);
    }
    public List<RentedBook> selectByUser(User user){
        return rentedBookDao.selectByUser(user);
    }
}
