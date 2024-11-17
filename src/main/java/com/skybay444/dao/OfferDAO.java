package com.skybay444.dao;

import java.util.List;

import com.skybay444.dao.GenericDAO;
import com.skybay444.domain.Offer;





public interface OfferDAO extends GenericDAO<Offer, Integer> {
  
	List<Offer> findAll();
	






}


