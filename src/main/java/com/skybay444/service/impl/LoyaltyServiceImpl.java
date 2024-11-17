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
import com.skybay444.dao.LoyaltyDAO;
import com.skybay444.domain.Loyalty;
import com.skybay444.dto.LoyaltyDTO;
import com.skybay444.dto.LoyaltySearchDTO;
import com.skybay444.dto.LoyaltyPageDTO;
import com.skybay444.dto.LoyaltyConvertCriteriaDTO;
import com.skybay444.dto.common.RequestDTO;
import com.skybay444.dto.common.ResultDTO;
import com.skybay444.service.LoyaltyService;
import com.skybay444.util.ControllerUtils;





@Service
public class LoyaltyServiceImpl extends GenericServiceImpl<Loyalty, Integer> implements LoyaltyService {

    private final static Logger logger = LoggerFactory.getLogger(LoyaltyServiceImpl.class);

	@Autowired
	LoyaltyDAO loyaltyDao;

	


	@Override
	public GenericDAO<Loyalty, Integer> getDAO() {
		return (GenericDAO<Loyalty, Integer>) loyaltyDao;
	}
	
	public List<Loyalty> findAll () {
		List<Loyalty> loyaltys = loyaltyDao.findAll();
		
		return loyaltys;	
		
	}

	public ResultDTO addLoyalty(LoyaltyDTO loyaltyDTO, RequestDTO requestDTO) {

		Loyalty loyalty = new Loyalty();

		loyalty.setLoyaltyId(loyaltyDTO.getLoyaltyId());


		loyalty.setPoints(loyaltyDTO.getPoints());


		loyalty.setTier(loyaltyDTO.getTier());


		LocalDate localDate = LocalDate.now();
		ZoneId defaultZoneId = ZoneId.systemDefault();
		Date date = Date.from(localDate.atStartOfDay(defaultZoneId).toInstant());

		loyalty = loyaltyDao.save(loyalty);
		
		ResultDTO result = new ResultDTO();
		return result;
	}
	
	public Page<Loyalty> getAllLoyaltys(Pageable pageable) {
		return loyaltyDao.findAll(pageable);
	}

	public Page<Loyalty> getAllLoyaltys(Specification<Loyalty> spec, Pageable pageable) {
		return loyaltyDao.findAll(spec, pageable);
	}

	public ResponseEntity<LoyaltyPageDTO> getLoyaltys(LoyaltySearchDTO loyaltySearchDTO) {
	
			Integer loyaltyId = loyaltySearchDTO.getLoyaltyId(); 
  			String tier = loyaltySearchDTO.getTier(); 
 			String sortBy = loyaltySearchDTO.getSortBy();
			String sortOrder = loyaltySearchDTO.getSortOrder();
			String searchQuery = loyaltySearchDTO.getSearchQuery();
			Integer page = loyaltySearchDTO.getPage();
			Integer size = loyaltySearchDTO.getSize();

	        Specification<Loyalty> spec = Specification.where(null);

			spec = ControllerUtils.andIfNecessary(spec, loyaltyId, "loyaltyId"); 
			
			
			spec = ControllerUtils.andIfNecessary(spec, tier, "tier"); 
			

		if (searchQuery != null && !searchQuery.isEmpty()) {
			spec = spec.and((root, query, cb) -> cb.or(

             cb.like(cb.lower(root.get("tier")), "%" + searchQuery.toLowerCase() + "%") 
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

		Page<Loyalty> loyaltys = this.getAllLoyaltys(spec, pageable);
		
		//System.out.println(String.valueOf(loyaltys.getTotalElements()) + " total ${classNamelPlural}, viewing page X of " + String.valueOf(loyaltys.getTotalPages()));
		
		List<Loyalty> loyaltysList = loyaltys.getContent();
		
		LoyaltyConvertCriteriaDTO convertCriteria = new LoyaltyConvertCriteriaDTO();
		List<LoyaltyDTO> loyaltyDTOs = this.convertLoyaltysToLoyaltyDTOs(loyaltysList,convertCriteria);
		
		LoyaltyPageDTO loyaltyPageDTO = new LoyaltyPageDTO();
		loyaltyPageDTO.setLoyaltys(loyaltyDTOs);
		loyaltyPageDTO.setTotalElements(loyaltys.getTotalElements());
		return ResponseEntity.ok(loyaltyPageDTO);
	}

	public List<LoyaltyDTO> convertLoyaltysToLoyaltyDTOs(List<Loyalty> loyaltys, LoyaltyConvertCriteriaDTO convertCriteria) {
		
		List<LoyaltyDTO> loyaltyDTOs = new ArrayList<LoyaltyDTO>();
		
		for (Loyalty loyalty : loyaltys) {
			loyaltyDTOs.add(convertLoyaltyToLoyaltyDTO(loyalty,convertCriteria));
		}
		
		return loyaltyDTOs;

	}
	
	public LoyaltyDTO convertLoyaltyToLoyaltyDTO(Loyalty loyalty, LoyaltyConvertCriteriaDTO convertCriteria) {
		
		LoyaltyDTO loyaltyDTO = new LoyaltyDTO();
		
		loyaltyDTO.setLoyaltyId(loyalty.getLoyaltyId());

	
		loyaltyDTO.setPoints(loyalty.getPoints());

	
		loyaltyDTO.setTier(loyalty.getTier());

	

		
		return loyaltyDTO;
	}

	public ResultDTO updateLoyalty(LoyaltyDTO loyaltyDTO, RequestDTO requestDTO) {
		
		Loyalty loyalty = loyaltyDao.getById(loyaltyDTO.getLoyaltyId());

		loyalty.setLoyaltyId(ControllerUtils.setValue(loyalty.getLoyaltyId(), loyaltyDTO.getLoyaltyId()));

		loyalty.setPoints(ControllerUtils.setValue(loyalty.getPoints(), loyaltyDTO.getPoints()));

		loyalty.setTier(ControllerUtils.setValue(loyalty.getTier(), loyaltyDTO.getTier()));



        loyalty = loyaltyDao.save(loyalty);
		
		ResultDTO result = new ResultDTO();
		return result;
	}

	public LoyaltyDTO getLoyaltyDTOById(Integer loyaltyId) {
	
		Loyalty loyalty = loyaltyDao.getById(loyaltyId);
			
		
		LoyaltyConvertCriteriaDTO convertCriteria = new LoyaltyConvertCriteriaDTO();
		return(this.convertLoyaltyToLoyaltyDTO(loyalty,convertCriteria));
	}







}
