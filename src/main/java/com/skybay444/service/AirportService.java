package com.skybay444.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;

import com.skybay444.domain.Airport;
import com.skybay444.dto.AirportDTO;
import com.skybay444.dto.AirportSearchDTO;
import com.skybay444.dto.AirportPageDTO;
import com.skybay444.dto.AirportConvertCriteriaDTO;
import com.skybay444.service.GenericService;
import com.skybay444.dto.common.RequestDTO;
import com.skybay444.dto.common.ResultDTO;
import java.util.List;
import java.util.Optional;





public interface AirportService extends GenericService<Airport, Integer> {

	List<Airport> findAll();

	ResultDTO addAirport(AirportDTO airportDTO, RequestDTO requestDTO);

	ResultDTO updateAirport(AirportDTO airportDTO, RequestDTO requestDTO);

    Page<Airport> getAllAirports(Pageable pageable);

    Page<Airport> getAllAirports(Specification<Airport> spec, Pageable pageable);

	ResponseEntity<AirportPageDTO> getAirports(AirportSearchDTO airportSearchDTO);
	
	List<AirportDTO> convertAirportsToAirportDTOs(List<Airport> airports, AirportConvertCriteriaDTO convertCriteria);

	AirportDTO getAirportDTOById(Integer airportId);







}





