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
import com.skybay444.dao.OfferDAO;
import com.skybay444.domain.Offer;
import com.skybay444.dto.OfferDTO;
import com.skybay444.dto.OfferSearchDTO;
import com.skybay444.dto.OfferPageDTO;
import com.skybay444.dto.OfferConvertCriteriaDTO;
import com.skybay444.dto.common.RequestDTO;
import com.skybay444.dto.common.ResultDTO;
import com.skybay444.service.OfferService;
import com.skybay444.util.ControllerUtils;





@Service
public class OfferServiceImpl extends GenericServiceImpl<Offer, Integer> implements OfferService {

    private final static Logger logger = LoggerFactory.getLogger(OfferServiceImpl.class);

	@Autowired
	OfferDAO offerDao;

	


	@Override
	public GenericDAO<Offer, Integer> getDAO() {
		return (GenericDAO<Offer, Integer>) offerDao;
	}
	
	public List<Offer> findAll () {
		List<Offer> offers = offerDao.findAll();
		
		return offers;	
		
	}

	public ResultDTO addOffer(OfferDTO offerDTO, RequestDTO requestDTO) {

		Offer offer = new Offer();

		offer.setOfferId(offerDTO.getOfferId());


		offer.setDescription(offerDTO.getDescription());


		offer.setDiscount(offerDTO.getDiscount());


		LocalDate localDate = LocalDate.now();
		ZoneId defaultZoneId = ZoneId.systemDefault();
		Date date = Date.from(localDate.atStartOfDay(defaultZoneId).toInstant());

		offer = offerDao.save(offer);
		
		ResultDTO result = new ResultDTO();
		return result;
	}
	
	public Page<Offer> getAllOffers(Pageable pageable) {
		return offerDao.findAll(pageable);
	}

	public Page<Offer> getAllOffers(Specification<Offer> spec, Pageable pageable) {
		return offerDao.findAll(spec, pageable);
	}

	public ResponseEntity<OfferPageDTO> getOffers(OfferSearchDTO offerSearchDTO) {
	
			Integer offerId = offerSearchDTO.getOfferId(); 
 			String description = offerSearchDTO.getDescription(); 
  			String sortBy = offerSearchDTO.getSortBy();
			String sortOrder = offerSearchDTO.getSortOrder();
			String searchQuery = offerSearchDTO.getSearchQuery();
			Integer page = offerSearchDTO.getPage();
			Integer size = offerSearchDTO.getSize();

	        Specification<Offer> spec = Specification.where(null);

			spec = ControllerUtils.andIfNecessary(spec, offerId, "offerId"); 
			
			spec = ControllerUtils.andIfNecessary(spec, description, "description"); 
			
			

		if (searchQuery != null && !searchQuery.isEmpty()) {
			spec = spec.and((root, query, cb) -> cb.or(

             cb.like(cb.lower(root.get("description")), "%" + searchQuery.toLowerCase() + "%") 
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

		Page<Offer> offers = this.getAllOffers(spec, pageable);
		
		//System.out.println(String.valueOf(offers.getTotalElements()) + " total ${classNamelPlural}, viewing page X of " + String.valueOf(offers.getTotalPages()));
		
		List<Offer> offersList = offers.getContent();
		
		OfferConvertCriteriaDTO convertCriteria = new OfferConvertCriteriaDTO();
		List<OfferDTO> offerDTOs = this.convertOffersToOfferDTOs(offersList,convertCriteria);
		
		OfferPageDTO offerPageDTO = new OfferPageDTO();
		offerPageDTO.setOffers(offerDTOs);
		offerPageDTO.setTotalElements(offers.getTotalElements());
		return ResponseEntity.ok(offerPageDTO);
	}

	public List<OfferDTO> convertOffersToOfferDTOs(List<Offer> offers, OfferConvertCriteriaDTO convertCriteria) {
		
		List<OfferDTO> offerDTOs = new ArrayList<OfferDTO>();
		
		for (Offer offer : offers) {
			offerDTOs.add(convertOfferToOfferDTO(offer,convertCriteria));
		}
		
		return offerDTOs;

	}
	
	public OfferDTO convertOfferToOfferDTO(Offer offer, OfferConvertCriteriaDTO convertCriteria) {
		
		OfferDTO offerDTO = new OfferDTO();
		
		offerDTO.setOfferId(offer.getOfferId());

	
		offerDTO.setDescription(offer.getDescription());

	
		offerDTO.setDiscount(offer.getDiscount());

	

		
		return offerDTO;
	}

	public ResultDTO updateOffer(OfferDTO offerDTO, RequestDTO requestDTO) {
		
		Offer offer = offerDao.getById(offerDTO.getOfferId());

		offer.setOfferId(ControllerUtils.setValue(offer.getOfferId(), offerDTO.getOfferId()));

		offer.setDescription(ControllerUtils.setValue(offer.getDescription(), offerDTO.getDescription()));

		offer.setDiscount(ControllerUtils.setValue(offer.getDiscount(), offerDTO.getDiscount()));



        offer = offerDao.save(offer);
		
		ResultDTO result = new ResultDTO();
		return result;
	}

	public OfferDTO getOfferDTOById(Integer offerId) {
	
		Offer offer = offerDao.getById(offerId);
			
		
		OfferConvertCriteriaDTO convertCriteria = new OfferConvertCriteriaDTO();
		return(this.convertOfferToOfferDTO(offer,convertCriteria));
	}







}
