package com.nixsolutions.ppp.model.dao;

import com.nixsolutions.ppp.model.entity.Review;
import com.nixsolutions.ppp.model.entity.Role;
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
