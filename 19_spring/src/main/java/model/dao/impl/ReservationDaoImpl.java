package model.dao.impl;

import model.dao.ReservationDao;
import entity.Reservation;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Repository;

@Repository
public class ReservationDaoImpl extends DefaultDao<Reservation> implements ReservationDao {
    private static final Logger log = LogManager.getLogger(ReservationDaoImpl.class.getName());
    public ReservationDaoImpl() {
        setTableName("Reservation");
    }


    @Override
    public long getId(Reservation reservation) {
        log.traceEntry(reservation.toString());
        Reservation result = getUniqueObjectFromQuery("" +
                "from Reservation where " +//book_id = \'" + reservation.getBook().getId() + "\' and
                "user_id = \'" + reservation.getUser().getId() + "\'");
        if (result == null) {//TODO somethitg wrong
            return (0);
        }
        return result.getId();
    }
}
