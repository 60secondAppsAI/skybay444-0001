package com.skybay444.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;

import com.skybay444.domain.Loyalty;
import com.skybay444.dto.LoyaltyDTO;
import com.skybay444.dto.LoyaltySearchDTO;
import com.skybay444.dto.LoyaltyPageDTO;
import com.skybay444.dto.LoyaltyConvertCriteriaDTO;
import com.skybay444.service.GenericService;
import com.skybay444.dto.common.RequestDTO;
import com.skybay444.dto.common.ResultDTO;
import java.util.List;
import java.util.Optional;





public interface LoyaltyService extends GenericService<Loyalty, Integer> {

	List<Loyalty> findAll();

	ResultDTO addLoyalty(LoyaltyDTO loyaltyDTO, RequestDTO requestDTO);

	ResultDTO updateLoyalty(LoyaltyDTO loyaltyDTO, RequestDTO requestDTO);

    Page<Loyalty> getAllLoyaltys(Pageable pageable);

    Page<Loyalty> getAllLoyaltys(Specification<Loyalty> spec, Pageable pageable);

	ResponseEntity<LoyaltyPageDTO> getLoyaltys(LoyaltySearchDTO loyaltySearchDTO);
	
	List<LoyaltyDTO> convertLoyaltysToLoyaltyDTOs(List<Loyalty> loyaltys, LoyaltyConvertCriteriaDTO convertCriteria);

	LoyaltyDTO getLoyaltyDTOById(Integer loyaltyId);







}





