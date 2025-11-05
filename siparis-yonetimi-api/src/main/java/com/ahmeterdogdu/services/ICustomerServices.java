package com.ahmeterdogdu.services;

import java.util.List;

import com.ahmeterdogdu.dto.DtoCustomer;
import com.ahmeterdogdu.dto.DtoCustomerIU;

public interface ICustomerServices {
	
	public DtoCustomer findCustomerById(Integer id);
	
	public List<DtoCustomer> gelAllCustomers();
	
	public DtoCustomer saveCustomer(DtoCustomerIU dtoCustomerIU);
	
	public boolean deleteCustomer(Integer id);
	
	public DtoCustomer updateCustomer(Integer id,DtoCustomerIU dtoCustomerIU);

}
