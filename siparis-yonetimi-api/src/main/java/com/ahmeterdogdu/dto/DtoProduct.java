package com.ahmeterdogdu.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DtoProduct {
	
	private int id;
	
	private String name;
	
	private String description;
	
	private double price;

	private int stockAmount;
}
