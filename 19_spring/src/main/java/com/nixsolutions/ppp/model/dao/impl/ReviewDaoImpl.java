package com.nixsolutions.ppp.model.dao.impl;

import com.nixsolutions.ppp.model.dao.ReviewDao;
import com.nixsolutions.ppp.model.entity.Review;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Repository;

@Repository
public class ReviewDaoImpl extends DefaultDao<Review> implements ReviewDao {
    private static final Logger log = LogManager.getLogger(ReviewDaoImpl.class.getName());

    public ReviewDaoImpl() {
        setTableName("Review");
    }

    @Override
    public long getId(Review review) {
        log.traceEntry(review.toString());
        Review result = getUniqueObjectFromQuery("" +
                "from Review where date_taken = \'" + review.getText() + "\' and" +
                "user_id = " + review.getUser().getId() + " and" +
                "book_id = " + review.getBook().getId());
        if (result == null) {
            return log.traceExit(0);
        }
        return log.traceExit(result.getId());
    }
}
