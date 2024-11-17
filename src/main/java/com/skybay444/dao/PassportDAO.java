package com.skybay444.dao;

import java.util.List;

import com.skybay444.dao.GenericDAO;
import com.skybay444.domain.Passport;





public interface PassportDAO extends GenericDAO<Passport, Integer> {
  
	List<Passport> findAll();
	






}


