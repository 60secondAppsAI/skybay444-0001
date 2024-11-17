package com.skybay444.dao;

import java.util.List;

import com.skybay444.dao.GenericDAO;
import com.skybay444.domain.Schedule;





public interface ScheduleDAO extends GenericDAO<Schedule, Integer> {
  
	List<Schedule> findAll();
	






}


