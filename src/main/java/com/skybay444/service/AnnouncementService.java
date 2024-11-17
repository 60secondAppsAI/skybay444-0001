package com.skybay444.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;

import com.skybay444.domain.Announcement;
import com.skybay444.dto.AnnouncementDTO;
import com.skybay444.dto.AnnouncementSearchDTO;
import com.skybay444.dto.AnnouncementPageDTO;
import com.skybay444.dto.AnnouncementConvertCriteriaDTO;
import com.skybay444.service.GenericService;
import com.skybay444.dto.common.RequestDTO;
import com.skybay444.dto.common.ResultDTO;
import java.util.List;
import java.util.Optional;





public interface AnnouncementService extends GenericService<Announcement, Integer> {

	List<Announcement> findAll();

	ResultDTO addAnnouncement(AnnouncementDTO announcementDTO, RequestDTO requestDTO);

	ResultDTO updateAnnouncement(AnnouncementDTO announcementDTO, RequestDTO requestDTO);

    Page<Announcement> getAllAnnouncements(Pageable pageable);

    Page<Announcement> getAllAnnouncements(Specification<Announcement> spec, Pageable pageable);

	ResponseEntity<AnnouncementPageDTO> getAnnouncements(AnnouncementSearchDTO announcementSearchDTO);
	
	List<AnnouncementDTO> convertAnnouncementsToAnnouncementDTOs(List<Announcement> announcements, AnnouncementConvertCriteriaDTO convertCriteria);

	AnnouncementDTO getAnnouncementDTOById(Integer announcementId);







}





