package com.skybay444.dto;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class SchedulePageDTO {

	private Integer page = 0;
	private Long totalElements = 0L;

	private List<ScheduleDTO> schedules;
}





