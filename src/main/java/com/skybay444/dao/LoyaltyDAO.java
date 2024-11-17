package com.skybay444.dao;

import java.util.List;

import com.skybay444.dao.GenericDAO;
import com.skybay444.domain.Loyalty;





public interface LoyaltyDAO extends GenericDAO<Loyalty, Integer> {
  
	List<Loyalty> findAll();
	






}


