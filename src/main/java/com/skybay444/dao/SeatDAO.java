package com.skybay444.dao;

import java.util.List;

import com.skybay444.dao.GenericDAO;
import com.skybay444.domain.Seat;





public interface SeatDAO extends GenericDAO<Seat, Integer> {
  
	List<Seat> findAll();
	






}


