package com.skybay444.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;

import com.skybay444.domain.Gate;
import com.skybay444.dto.GateDTO;
import com.skybay444.dto.GateSearchDTO;
import com.skybay444.dto.GatePageDTO;
import com.skybay444.dto.GateConvertCriteriaDTO;
import com.skybay444.service.GenericService;
import com.skybay444.dto.common.RequestDTO;
import com.skybay444.dto.common.ResultDTO;
import java.util.List;
import java.util.Optional;





public interface GateService extends GenericService<Gate, Integer> {

	List<Gate> findAll();

	ResultDTO addGate(GateDTO gateDTO, RequestDTO requestDTO);

	ResultDTO updateGate(GateDTO gateDTO, RequestDTO requestDTO);

    Page<Gate> getAllGates(Pageable pageable);

    Page<Gate> getAllGates(Specification<Gate> spec, Pageable pageable);

	ResponseEntity<GatePageDTO> getGates(GateSearchDTO gateSearchDTO);
	
	List<GateDTO> convertGatesToGateDTOs(List<Gate> gates, GateConvertCriteriaDTO convertCriteria);

	GateDTO getGateDTOById(Integer gateId);







}





