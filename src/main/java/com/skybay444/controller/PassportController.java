package com.skybay444.controller;

import java.io.IOException;
import java.text.MessageFormat;
import java.util.List;
import java.util.ArrayList;


import com.skybay444.util.Util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.sql.Timestamp;
import java.util.Date;

import com.skybay444.domain.Passport;
import com.skybay444.dto.PassportDTO;
import com.skybay444.dto.PassportSearchDTO;
import com.skybay444.dto.PassportPageDTO;
import com.skybay444.service.PassportService;
import com.skybay444.dto.common.RequestDTO;
import com.skybay444.dto.common.ResultDTO;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.multipart.MultipartFile;




@CrossOrigin(origins = "*")
@RequestMapping("/passport")
@RestController
public class PassportController {

	private final static Logger logger = LoggerFactory.getLogger(PassportController.class);

	@Autowired
	PassportService passportService;



	@RequestMapping(value="/", method = RequestMethod.GET)
	public List<Passport> getAll() {

		List<Passport> passports = passportService.findAll();
		
		return passports;	
	}

	@GetMapping(value = "/{passportId}")
	@ResponseBody
	public PassportDTO getPassport(@PathVariable Integer passportId) {
		
		return (passportService.getPassportDTOById(passportId));
	}

 	@RequestMapping(value = "/addPassport", method = RequestMethod.POST)
	public ResponseEntity<?> addPassport(@RequestBody PassportDTO passportDTO, HttpServletRequest request) {

		RequestDTO requestDTO = new RequestDTO(request);
		ResultDTO result = passportService.addPassport(passportDTO, requestDTO);
		
		return result.asResponseEntity();
	}

	@GetMapping("/passports")
	public ResponseEntity<PassportPageDTO> getPassports(PassportSearchDTO passportSearchDTO) {
 
		return passportService.getPassports(passportSearchDTO);
	}	

	@RequestMapping(value = "/updatePassport", method = RequestMethod.POST)
	public ResponseEntity<?> updatePassport(@RequestBody PassportDTO passportDTO, HttpServletRequest request) {
		RequestDTO requestDTO = new RequestDTO(request);
		ResultDTO result = passportService.updatePassport(passportDTO, requestDTO);
		
//		if (result.isSuccessful()) {
//		}

		return result.asResponseEntity();
	}



}
