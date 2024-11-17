package com.skybay444.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;

import com.skybay444.domain.Ticket;
import com.skybay444.dto.TicketDTO;
import com.skybay444.dto.TicketSearchDTO;
import com.skybay444.dto.TicketPageDTO;
import com.skybay444.dto.TicketConvertCriteriaDTO;
import com.skybay444.service.GenericService;
import com.skybay444.dto.common.RequestDTO;
import com.skybay444.dto.common.ResultDTO;
import java.util.List;
import java.util.Optional;





public interface TicketService extends GenericService<Ticket, Integer> {

	List<Ticket> findAll();

	ResultDTO addTicket(TicketDTO ticketDTO, RequestDTO requestDTO);

	ResultDTO updateTicket(TicketDTO ticketDTO, RequestDTO requestDTO);

    Page<Ticket> getAllTickets(Pageable pageable);

    Page<Ticket> getAllTickets(Specification<Ticket> spec, Pageable pageable);

	ResponseEntity<TicketPageDTO> getTickets(TicketSearchDTO ticketSearchDTO);
	
	List<TicketDTO> convertTicketsToTicketDTOs(List<Ticket> tickets, TicketConvertCriteriaDTO convertCriteria);

	TicketDTO getTicketDTOById(Integer ticketId);







}





