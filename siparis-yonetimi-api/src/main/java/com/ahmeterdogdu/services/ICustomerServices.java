package com.ahmeterdogdu.services;

import com.ahmeterdogdu.dto.DtoCustomer;

public interface ICustomerServices {
	
	public DtoCustomer findCustomerById(Integer id);

}
