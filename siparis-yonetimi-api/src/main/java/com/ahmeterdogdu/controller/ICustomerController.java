package com.ahmeterdogdu.controller;

import com.ahmeterdogdu.dto.DtoCustomer;

public interface ICustomerController {
	
	public DtoCustomer findCustomerById(Integer id);

}
