package com.ahmeterdogdu.dto;

import java.util.List;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DtoCustomerIU {
	
	@NotEmpty
	@Size(min = 3,max = 15,message = "firstname alanı 3-15 karakter olmalıdır")
	private String firstName;
	
	@NotEmpty
	@Size(min = 3,max = 15,message = "lastname alanı 3-15 karakter arasında olmalıdır")
	private String lastName;
	
	@Email(message = "e mail formatında bir adres giriniz")
	private String eMail;
	
	private List<Integer> orderIds;
	
}
