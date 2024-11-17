package com.skybay444.service.impl;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;



import com.skybay444.dao.GenericDAO;
import com.skybay444.service.GenericService;
import com.skybay444.service.impl.GenericServiceImpl;
import com.skybay444.dao.PassportDAO;
import com.skybay444.domain.Passport;
import com.skybay444.dto.PassportDTO;
import com.skybay444.dto.PassportSearchDTO;
import com.skybay444.dto.PassportPageDTO;
import com.skybay444.dto.PassportConvertCriteriaDTO;
import com.skybay444.dto.common.RequestDTO;
import com.skybay444.dto.common.ResultDTO;
import com.skybay444.service.PassportService;
import com.skybay444.util.ControllerUtils;





@Service
public class PassportServiceImpl extends GenericServiceImpl<Passport, Integer> implements PassportService {

    private final static Logger logger = LoggerFactory.getLogger(PassportServiceImpl.class);

	@Autowired
	PassportDAO passportDao;

	


	@Override
	public GenericDAO<Passport, Integer> getDAO() {
		return (GenericDAO<Passport, Integer>) passportDao;
	}
	
	public List<Passport> findAll () {
		List<Passport> passports = passportDao.findAll();
		
		return passports;	
		
	}

	public ResultDTO addPassport(PassportDTO passportDTO, RequestDTO requestDTO) {

		Passport passport = new Passport();

		passport.setPassportId(passportDTO.getPassportId());


		passport.setNumber(passportDTO.getNumber());


		passport.setCountry(passportDTO.getCountry());


		passport.setExpirationDate(passportDTO.getExpirationDate());


		LocalDate localDate = LocalDate.now();
		ZoneId defaultZoneId = ZoneId.systemDefault();
		Date date = Date.from(localDate.atStartOfDay(defaultZoneId).toInstant());

		passport = passportDao.save(passport);
		
		ResultDTO result = new ResultDTO();
		return result;
	}
	
	public Page<Passport> getAllPassports(Pageable pageable) {
		return passportDao.findAll(pageable);
	}

	public Page<Passport> getAllPassports(Specification<Passport> spec, Pageable pageable) {
		return passportDao.findAll(spec, pageable);
	}

	public ResponseEntity<PassportPageDTO> getPassports(PassportSearchDTO passportSearchDTO) {
	
			Integer passportId = passportSearchDTO.getPassportId(); 
 			String number = passportSearchDTO.getNumber(); 
 			String country = passportSearchDTO.getCountry(); 
   			String sortBy = passportSearchDTO.getSortBy();
			String sortOrder = passportSearchDTO.getSortOrder();
			String searchQuery = passportSearchDTO.getSearchQuery();
			Integer page = passportSearchDTO.getPage();
			Integer size = passportSearchDTO.getSize();

	        Specification<Passport> spec = Specification.where(null);

			spec = ControllerUtils.andIfNecessary(spec, passportId, "passportId"); 
			
			spec = ControllerUtils.andIfNecessary(spec, number, "number"); 
			
			spec = ControllerUtils.andIfNecessary(spec, country, "country"); 
			
 			

		if (searchQuery != null && !searchQuery.isEmpty()) {
			spec = spec.and((root, query, cb) -> cb.or(

             cb.like(cb.lower(root.get("number")), "%" + searchQuery.toLowerCase() + "%") 
             , cb.like(cb.lower(root.get("country")), "%" + searchQuery.toLowerCase() + "%") 
		));}
		
		Sort sort = Sort.unsorted();
		if (sortBy != null && !sortBy.isEmpty() && sortOrder != null && !sortOrder.isEmpty()) {
			if (sortOrder.equalsIgnoreCase("asc")) {
				sort = Sort.by(sortBy).ascending();
			} else if (sortOrder.equalsIgnoreCase("desc")) {
				sort = Sort.by(sortBy).descending();
			}
		}
		Pageable pageable = PageRequest.of(page, size, sort);

		Page<Passport> passports = this.getAllPassports(spec, pageable);
		
		//System.out.println(String.valueOf(passports.getTotalElements()) + " total ${classNamelPlural}, viewing page X of " + String.valueOf(passports.getTotalPages()));
		
		List<Passport> passportsList = passports.getContent();
		
		PassportConvertCriteriaDTO convertCriteria = new PassportConvertCriteriaDTO();
		List<PassportDTO> passportDTOs = this.convertPassportsToPassportDTOs(passportsList,convertCriteria);
		
		PassportPageDTO passportPageDTO = new PassportPageDTO();
		passportPageDTO.setPassports(passportDTOs);
		passportPageDTO.setTotalElements(passports.getTotalElements());
		return ResponseEntity.ok(passportPageDTO);
	}

	public List<PassportDTO> convertPassportsToPassportDTOs(List<Passport> passports, PassportConvertCriteriaDTO convertCriteria) {
		
		List<PassportDTO> passportDTOs = new ArrayList<PassportDTO>();
		
		for (Passport passport : passports) {
			passportDTOs.add(convertPassportToPassportDTO(passport,convertCriteria));
		}
		
		return passportDTOs;

	}
	
	public PassportDTO convertPassportToPassportDTO(Passport passport, PassportConvertCriteriaDTO convertCriteria) {
		
		PassportDTO passportDTO = new PassportDTO();
		
		passportDTO.setPassportId(passport.getPassportId());

	
		passportDTO.setNumber(passport.getNumber());

	
		passportDTO.setCountry(passport.getCountry());

	
		passportDTO.setExpirationDate(passport.getExpirationDate());

	

		
		return passportDTO;
	}

	public ResultDTO updatePassport(PassportDTO passportDTO, RequestDTO requestDTO) {
		
		Passport passport = passportDao.getById(passportDTO.getPassportId());

		passport.setPassportId(ControllerUtils.setValue(passport.getPassportId(), passportDTO.getPassportId()));

		passport.setNumber(ControllerUtils.setValue(passport.getNumber(), passportDTO.getNumber()));

		passport.setCountry(ControllerUtils.setValue(passport.getCountry(), passportDTO.getCountry()));

		passport.setExpirationDate(ControllerUtils.setValue(passport.getExpirationDate(), passportDTO.getExpirationDate()));



        passport = passportDao.save(passport);
		
		ResultDTO result = new ResultDTO();
		return result;
	}

	public PassportDTO getPassportDTOById(Integer passportId) {
	
		Passport passport = passportDao.getById(passportId);
			
		
		PassportConvertCriteriaDTO convertCriteria = new PassportConvertCriteriaDTO();
		return(this.convertPassportToPassportDTO(passport,convertCriteria));
	}







}
