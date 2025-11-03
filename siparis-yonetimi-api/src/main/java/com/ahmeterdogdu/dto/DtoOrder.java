package com.ahmeterdogdu.dto;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
//@JsonInclude(value = Include.NON_EMPTY)//customer içindeki orderdaki customer in gitmesi için
public class DtoOrder {

	private int id;

	private Date orderDate;

	private String status;
	
	private DtoCustomer customer;
	
	private List<DtoProduct> product=new ArrayList<>();
	
}
