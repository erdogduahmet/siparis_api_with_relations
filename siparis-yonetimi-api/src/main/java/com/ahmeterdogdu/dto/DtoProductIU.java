package com.ahmeterdogdu.dto;

import lombok.Data;

@Data
public class DtoProductIU {

	private String name;
	
	private String description;
	
	private double price;
	
	private int stockAmount;
}
