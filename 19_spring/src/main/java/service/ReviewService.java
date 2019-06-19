package service;

import model.dao.ReviewDao;
import entity.Review;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ReviewService {
    @Autowired
    private ReviewDao reviewDao;
    public boolean create(Review review){
        return reviewDao.insert(review);
    }
    public boolean delete(Review review){
        return reviewDao.delete(review);
    }
    public boolean delete(long i){
        return reviewDao.delete(i);
    }
    public boolean update(Review review){
        return reviewDao.update(review);
    }
    public List<Review> selectAll(){
        return reviewDao.selectAll();
    }
    public long getId(Review review){
        return reviewDao.getId(review);
    }
    public Review getReview(long id){
        return reviewDao.getById(id, Review.class);
    }
}
