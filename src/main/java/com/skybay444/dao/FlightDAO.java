package com.skybay444.dao;

import java.util.List;

import com.skybay444.dao.GenericDAO;
import com.skybay444.domain.Flight;





public interface FlightDAO extends GenericDAO<Flight, Integer> {
  
	List<Flight> findAll();
	






}


