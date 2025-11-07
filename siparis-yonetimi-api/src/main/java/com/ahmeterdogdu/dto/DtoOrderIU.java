package com.ahmeterdogdu.dto;

import java.util.List;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
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
	
	@NotEmpty
	@Size(min = 3,max = 15,message = "status alanı 3-15 karakter olmalıdır")
	private String status;
	
	private List<Integer> productIds;
	
	
}
