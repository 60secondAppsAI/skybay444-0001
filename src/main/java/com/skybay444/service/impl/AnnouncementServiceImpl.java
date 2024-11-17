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
import com.skybay444.dao.AnnouncementDAO;
import com.skybay444.domain.Announcement;
import com.skybay444.dto.AnnouncementDTO;
import com.skybay444.dto.AnnouncementSearchDTO;
import com.skybay444.dto.AnnouncementPageDTO;
import com.skybay444.dto.AnnouncementConvertCriteriaDTO;
import com.skybay444.dto.common.RequestDTO;
import com.skybay444.dto.common.ResultDTO;
import com.skybay444.service.AnnouncementService;
import com.skybay444.util.ControllerUtils;





@Service
public class AnnouncementServiceImpl extends GenericServiceImpl<Announcement, Integer> implements AnnouncementService {

    private final static Logger logger = LoggerFactory.getLogger(AnnouncementServiceImpl.class);

	@Autowired
	AnnouncementDAO announcementDao;

	


	@Override
	public GenericDAO<Announcement, Integer> getDAO() {
		return (GenericDAO<Announcement, Integer>) announcementDao;
	}
	
	public List<Announcement> findAll () {
		List<Announcement> announcements = announcementDao.findAll();
		
		return announcements;	
		
	}

	public ResultDTO addAnnouncement(AnnouncementDTO announcementDTO, RequestDTO requestDTO) {

		Announcement announcement = new Announcement();

		announcement.setAnnouncementId(announcementDTO.getAnnouncementId());


		announcement.setMessage(announcementDTO.getMessage());


		announcement.setDate(announcementDTO.getDate());


		LocalDate localDate = LocalDate.now();
		ZoneId defaultZoneId = ZoneId.systemDefault();
		Date date = Date.from(localDate.atStartOfDay(defaultZoneId).toInstant());

		announcement = announcementDao.save(announcement);
		
		ResultDTO result = new ResultDTO();
		return result;
	}
	
	public Page<Announcement> getAllAnnouncements(Pageable pageable) {
		return announcementDao.findAll(pageable);
	}

	public Page<Announcement> getAllAnnouncements(Specification<Announcement> spec, Pageable pageable) {
		return announcementDao.findAll(spec, pageable);
	}

	public ResponseEntity<AnnouncementPageDTO> getAnnouncements(AnnouncementSearchDTO announcementSearchDTO) {
	
			Integer announcementId = announcementSearchDTO.getAnnouncementId(); 
 			String message = announcementSearchDTO.getMessage(); 
   			String sortBy = announcementSearchDTO.getSortBy();
			String sortOrder = announcementSearchDTO.getSortOrder();
			String searchQuery = announcementSearchDTO.getSearchQuery();
			Integer page = announcementSearchDTO.getPage();
			Integer size = announcementSearchDTO.getSize();

	        Specification<Announcement> spec = Specification.where(null);

			spec = ControllerUtils.andIfNecessary(spec, announcementId, "announcementId"); 
			
			spec = ControllerUtils.andIfNecessary(spec, message, "message"); 
			
 			

		if (searchQuery != null && !searchQuery.isEmpty()) {
			spec = spec.and((root, query, cb) -> cb.or(

             cb.like(cb.lower(root.get("message")), "%" + searchQuery.toLowerCase() + "%") 
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

		Page<Announcement> announcements = this.getAllAnnouncements(spec, pageable);
		
		//System.out.println(String.valueOf(announcements.getTotalElements()) + " total ${classNamelPlural}, viewing page X of " + String.valueOf(announcements.getTotalPages()));
		
		List<Announcement> announcementsList = announcements.getContent();
		
		AnnouncementConvertCriteriaDTO convertCriteria = new AnnouncementConvertCriteriaDTO();
		List<AnnouncementDTO> announcementDTOs = this.convertAnnouncementsToAnnouncementDTOs(announcementsList,convertCriteria);
		
		AnnouncementPageDTO announcementPageDTO = new AnnouncementPageDTO();
		announcementPageDTO.setAnnouncements(announcementDTOs);
		announcementPageDTO.setTotalElements(announcements.getTotalElements());
		return ResponseEntity.ok(announcementPageDTO);
	}

	public List<AnnouncementDTO> convertAnnouncementsToAnnouncementDTOs(List<Announcement> announcements, AnnouncementConvertCriteriaDTO convertCriteria) {
		
		List<AnnouncementDTO> announcementDTOs = new ArrayList<AnnouncementDTO>();
		
		for (Announcement announcement : announcements) {
			announcementDTOs.add(convertAnnouncementToAnnouncementDTO(announcement,convertCriteria));
		}
		
		return announcementDTOs;

	}
	
	public AnnouncementDTO convertAnnouncementToAnnouncementDTO(Announcement announcement, AnnouncementConvertCriteriaDTO convertCriteria) {
		
		AnnouncementDTO announcementDTO = new AnnouncementDTO();
		
		announcementDTO.setAnnouncementId(announcement.getAnnouncementId());

	
		announcementDTO.setMessage(announcement.getMessage());

	
		announcementDTO.setDate(announcement.getDate());

	

		
		return announcementDTO;
	}

	public ResultDTO updateAnnouncement(AnnouncementDTO announcementDTO, RequestDTO requestDTO) {
		
		Announcement announcement = announcementDao.getById(announcementDTO.getAnnouncementId());

		announcement.setAnnouncementId(ControllerUtils.setValue(announcement.getAnnouncementId(), announcementDTO.getAnnouncementId()));

		announcement.setMessage(ControllerUtils.setValue(announcement.getMessage(), announcementDTO.getMessage()));

		announcement.setDate(ControllerUtils.setValue(announcement.getDate(), announcementDTO.getDate()));



        announcement = announcementDao.save(announcement);
		
		ResultDTO result = new ResultDTO();
		return result;
	}

	public AnnouncementDTO getAnnouncementDTOById(Integer announcementId) {
	
		Announcement announcement = announcementDao.getById(announcementId);
			
		
		AnnouncementConvertCriteriaDTO convertCriteria = new AnnouncementConvertCriteriaDTO();
		return(this.convertAnnouncementToAnnouncementDTO(announcement,convertCriteria));
	}







}
