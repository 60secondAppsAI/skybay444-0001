package com.skybay444.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;

import com.skybay444.domain.Offer;
import com.skybay444.dto.OfferDTO;
import com.skybay444.dto.OfferSearchDTO;
import com.skybay444.dto.OfferPageDTO;
import com.skybay444.dto.OfferConvertCriteriaDTO;
import com.skybay444.service.GenericService;
import com.skybay444.dto.common.RequestDTO;
import com.skybay444.dto.common.ResultDTO;
import java.util.List;
import java.util.Optional;





public interface OfferService extends GenericService<Offer, Integer> {

	List<Offer> findAll();

	ResultDTO addOffer(OfferDTO offerDTO, RequestDTO requestDTO);

	ResultDTO updateOffer(OfferDTO offerDTO, RequestDTO requestDTO);

    Page<Offer> getAllOffers(Pageable pageable);

    Page<Offer> getAllOffers(Specification<Offer> spec, Pageable pageable);

	ResponseEntity<OfferPageDTO> getOffers(OfferSearchDTO offerSearchDTO);
	
	List<OfferDTO> convertOffersToOfferDTOs(List<Offer> offers, OfferConvertCriteriaDTO convertCriteria);

	OfferDTO getOfferDTOById(Integer offerId);







}





