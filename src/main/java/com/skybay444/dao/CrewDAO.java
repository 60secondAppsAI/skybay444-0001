package com.skybay444.dao;

import java.util.List;

import com.skybay444.dao.GenericDAO;
import com.skybay444.domain.Crew;





public interface CrewDAO extends GenericDAO<Crew, Integer> {
  
	List<Crew> findAll();
	






}


