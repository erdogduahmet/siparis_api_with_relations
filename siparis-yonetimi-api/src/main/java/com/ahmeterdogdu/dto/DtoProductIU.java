package com.ahmeterdogdu.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DtoProductIU {

	@NotEmpty
	@Size(min = 3,max = 15,message = "name alanı 3-15 karakter olmalıdır")
	private String name;
	
	private String description;
	
	@Min(value = 1,message = "price değeri 1 den küçük olamaz")
	private double price;
	
	@Min(value = 1,message = "stockAmount değeri 1 den küçük olamaz")
	@Max(value = 100,message = "stockAmount değeri 100 den büyük olamaz")
	private int stockAmount;
}
