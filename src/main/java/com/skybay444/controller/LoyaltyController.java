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

import com.skybay444.domain.Loyalty;
import com.skybay444.dto.LoyaltyDTO;
import com.skybay444.dto.LoyaltySearchDTO;
import com.skybay444.dto.LoyaltyPageDTO;
import com.skybay444.service.LoyaltyService;
import com.skybay444.dto.common.RequestDTO;
import com.skybay444.dto.common.ResultDTO;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.multipart.MultipartFile;




@CrossOrigin(origins = "*")
@RequestMapping("/loyalty")
@RestController
public class LoyaltyController {

	private final static Logger logger = LoggerFactory.getLogger(LoyaltyController.class);

	@Autowired
	LoyaltyService loyaltyService;



	@RequestMapping(value="/", method = RequestMethod.GET)
	public List<Loyalty> getAll() {

		List<Loyalty> loyaltys = loyaltyService.findAll();
		
		return loyaltys;	
	}

	@GetMapping(value = "/{loyaltyId}")
	@ResponseBody
	public LoyaltyDTO getLoyalty(@PathVariable Integer loyaltyId) {
		
		return (loyaltyService.getLoyaltyDTOById(loyaltyId));
	}

 	@RequestMapping(value = "/addLoyalty", method = RequestMethod.POST)
	public ResponseEntity<?> addLoyalty(@RequestBody LoyaltyDTO loyaltyDTO, HttpServletRequest request) {

		RequestDTO requestDTO = new RequestDTO(request);
		ResultDTO result = loyaltyService.addLoyalty(loyaltyDTO, requestDTO);
		
		return result.asResponseEntity();
	}

	@GetMapping("/loyaltys")
	public ResponseEntity<LoyaltyPageDTO> getLoyaltys(LoyaltySearchDTO loyaltySearchDTO) {
 
		return loyaltyService.getLoyaltys(loyaltySearchDTO);
	}	

	@RequestMapping(value = "/updateLoyalty", method = RequestMethod.POST)
	public ResponseEntity<?> updateLoyalty(@RequestBody LoyaltyDTO loyaltyDTO, HttpServletRequest request) {
		RequestDTO requestDTO = new RequestDTO(request);
		ResultDTO result = loyaltyService.updateLoyalty(loyaltyDTO, requestDTO);
		
//		if (result.isSuccessful()) {
//		}

		return result.asResponseEntity();
	}



}
