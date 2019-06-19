package model.dao;

import entity.Review;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
public interface ReviewDao {
    public boolean insert(Review review);
    public boolean delete(Review review);
    public boolean delete(long i);
    public boolean update(Review review);
    public List<Review> executeWithResultList(String query);
    public List<Review> selectAll();
    public long getId(Review review);
    public Review getById(long id, Class objClass);
}
