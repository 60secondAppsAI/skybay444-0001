package com.skybay444.dao;

import java.util.List;

import com.skybay444.dao.GenericDAO;
import com.skybay444.domain.Meal;





public interface MealDAO extends GenericDAO<Meal, Integer> {
  
	List<Meal> findAll();
	






}


