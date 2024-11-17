package com.skybay444.dao;

import java.util.List;

import com.skybay444.dao.GenericDAO;
import com.skybay444.domain.Announcement;





public interface AnnouncementDAO extends GenericDAO<Announcement, Integer> {
  
	List<Announcement> findAll();
	






}


