package com.ahmeterdogdu.dto;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(value = Include.NON_EMPTY)//order içindeki customer içindeki order ın gitmesi için
public class DtoCustomer {
	
	private int id;
	
	private String firstName;
	
	private String lastName;
	
	private String eMail;
	
	private List<DtoOrder> order=new ArrayList<>();

}
