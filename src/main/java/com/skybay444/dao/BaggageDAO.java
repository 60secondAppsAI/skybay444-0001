package com.skybay444.dao;

import java.util.List;

import com.skybay444.dao.GenericDAO;
import com.skybay444.domain.Baggage;





public interface BaggageDAO extends GenericDAO<Baggage, Integer> {
  
	List<Baggage> findAll();
	






}


