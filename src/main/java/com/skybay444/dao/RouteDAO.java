package com.skybay444.dao;

import java.util.List;

import com.skybay444.dao.GenericDAO;
import com.skybay444.domain.Route;





public interface RouteDAO extends GenericDAO<Route, Integer> {
  
	List<Route> findAll();
	






}


