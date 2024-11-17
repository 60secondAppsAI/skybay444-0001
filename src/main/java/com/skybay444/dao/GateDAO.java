package com.skybay444.dao;

import java.util.List;

import com.skybay444.dao.GenericDAO;
import com.skybay444.domain.Gate;





public interface GateDAO extends GenericDAO<Gate, Integer> {
  
	List<Gate> findAll();
	






}


