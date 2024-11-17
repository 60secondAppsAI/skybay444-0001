package com.skybay444.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;

import com.skybay444.domain.Passport;
import com.skybay444.dto.PassportDTO;
import com.skybay444.dto.PassportSearchDTO;
import com.skybay444.dto.PassportPageDTO;
import com.skybay444.dto.PassportConvertCriteriaDTO;
import com.skybay444.service.GenericService;
import com.skybay444.dto.common.RequestDTO;
import com.skybay444.dto.common.ResultDTO;
import java.util.List;
import java.util.Optional;





public interface PassportService extends GenericService<Passport, Integer> {

	List<Passport> findAll();

	ResultDTO addPassport(PassportDTO passportDTO, RequestDTO requestDTO);

	ResultDTO updatePassport(PassportDTO passportDTO, RequestDTO requestDTO);

    Page<Passport> getAllPassports(Pageable pageable);

    Page<Passport> getAllPassports(Specification<Passport> spec, Pageable pageable);

	ResponseEntity<PassportPageDTO> getPassports(PassportSearchDTO passportSearchDTO);
	
	List<PassportDTO> convertPassportsToPassportDTOs(List<Passport> passports, PassportConvertCriteriaDTO convertCriteria);

	PassportDTO getPassportDTOById(Integer passportId);







}





