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

import com.skybay444.domain.Announcement;
import com.skybay444.dto.AnnouncementDTO;
import com.skybay444.dto.AnnouncementSearchDTO;
import com.skybay444.dto.AnnouncementPageDTO;
import com.skybay444.service.AnnouncementService;
import com.skybay444.dto.common.RequestDTO;
import com.skybay444.dto.common.ResultDTO;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.multipart.MultipartFile;




@CrossOrigin(origins = "*")
@RequestMapping("/announcement")
@RestController
public class AnnouncementController {

	private final static Logger logger = LoggerFactory.getLogger(AnnouncementController.class);

	@Autowired
	AnnouncementService announcementService;



	@RequestMapping(value="/", method = RequestMethod.GET)
	public List<Announcement> getAll() {

		List<Announcement> announcements = announcementService.findAll();
		
		return announcements;	
	}

	@GetMapping(value = "/{announcementId}")
	@ResponseBody
	public AnnouncementDTO getAnnouncement(@PathVariable Integer announcementId) {
		
		return (announcementService.getAnnouncementDTOById(announcementId));
	}

 	@RequestMapping(value = "/addAnnouncement", method = RequestMethod.POST)
	public ResponseEntity<?> addAnnouncement(@RequestBody AnnouncementDTO announcementDTO, HttpServletRequest request) {

		RequestDTO requestDTO = new RequestDTO(request);
		ResultDTO result = announcementService.addAnnouncement(announcementDTO, requestDTO);
		
		return result.asResponseEntity();
	}

	@GetMapping("/announcements")
	public ResponseEntity<AnnouncementPageDTO> getAnnouncements(AnnouncementSearchDTO announcementSearchDTO) {
 
		return announcementService.getAnnouncements(announcementSearchDTO);
	}	

	@RequestMapping(value = "/updateAnnouncement", method = RequestMethod.POST)
	public ResponseEntity<?> updateAnnouncement(@RequestBody AnnouncementDTO announcementDTO, HttpServletRequest request) {
		RequestDTO requestDTO = new RequestDTO(request);
		ResultDTO result = announcementService.updateAnnouncement(announcementDTO, requestDTO);
		
//		if (result.isSuccessful()) {
//		}

		return result.asResponseEntity();
	}



}
