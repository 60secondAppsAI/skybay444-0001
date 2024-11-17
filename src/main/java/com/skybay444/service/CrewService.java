package com.skybay444.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;

import com.skybay444.domain.Crew;
import com.skybay444.dto.CrewDTO;
import com.skybay444.dto.CrewSearchDTO;
import com.skybay444.dto.CrewPageDTO;
import com.skybay444.dto.CrewConvertCriteriaDTO;
import com.skybay444.service.GenericService;
import com.skybay444.dto.common.RequestDTO;
import com.skybay444.dto.common.ResultDTO;
import java.util.List;
import java.util.Optional;





public interface CrewService extends GenericService<Crew, Integer> {

	List<Crew> findAll();

	ResultDTO addCrew(CrewDTO crewDTO, RequestDTO requestDTO);

	ResultDTO updateCrew(CrewDTO crewDTO, RequestDTO requestDTO);

    Page<Crew> getAllCrews(Pageable pageable);

    Page<Crew> getAllCrews(Specification<Crew> spec, Pageable pageable);

	ResponseEntity<CrewPageDTO> getCrews(CrewSearchDTO crewSearchDTO);
	
	List<CrewDTO> convertCrewsToCrewDTOs(List<Crew> crews, CrewConvertCriteriaDTO convertCriteria);

	CrewDTO getCrewDTOById(Integer crewId);







}





