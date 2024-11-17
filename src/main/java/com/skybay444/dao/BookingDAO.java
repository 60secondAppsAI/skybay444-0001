package com.skybay444.dao;

import java.util.List;

import com.skybay444.dao.GenericDAO;
import com.skybay444.domain.Booking;





public interface BookingDAO extends GenericDAO<Booking, Integer> {
  
	List<Booking> findAll();
	






}


