package com.nixsolutions.ppp.model.dao;

import com.nixsolutions.ppp.model.entity.Reservation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
public interface ReservationDao {
    public boolean insert(Reservation reservation);
    public boolean delete(Reservation reservation);
    public boolean delete(long i);
    public boolean update(Reservation reservation);
    public List<Reservation> executeWithResultList(String query);
    public List<Reservation> selectAll();
    public long getId(Reservation reservation);
    public Reservation getById(long id, Class objClass);
}
