package com.ahmeterdogdu.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DtoOrderIU {

	private int customerId;
	
	private String status;
	
	private List<Integer> productIds;
	
	
}
