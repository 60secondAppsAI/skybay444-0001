package com.skybay444.dao;

import java.util.List;

import com.skybay444.dao.GenericDAO;
import com.skybay444.domain.CheckIn;





public interface CheckInDAO extends GenericDAO<CheckIn, Integer> {
  
	List<CheckIn> findAll();
	






}


