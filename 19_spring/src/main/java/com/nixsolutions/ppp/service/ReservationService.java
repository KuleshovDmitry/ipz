package com.nixsolutions.ppp.service;

import com.nixsolutions.ppp.model.dao.ReservationDao;
import com.nixsolutions.ppp.model.entity.Reservation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ReservationService {

    @Autowired
    private ReservationDao reservationDao;
    public boolean create(Reservation reservation){
        return reservationDao.insert(reservation);
    }
    public boolean delete(Reservation reservation){
        return reservationDao.delete(reservation);
    }
    public boolean delete(long i){
        return reservationDao.delete(i);
    }
    public boolean update(Reservation reservation){
        return reservationDao.update(reservation);
    }
    public List<Reservation> selectAll(){
        return reservationDao.selectAll();
    }
    public long getId(Reservation reservation){
        return reservationDao.getId(reservation);
    }
    public Reservation getReservation(long id){
        return reservationDao.getById(id, Reservation.class);
    }
}