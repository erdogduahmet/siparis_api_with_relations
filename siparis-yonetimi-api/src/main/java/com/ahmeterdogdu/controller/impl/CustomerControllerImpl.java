package com.ahmeterdogdu.controller.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ahmeterdogdu.controller.ICustomerController;
import com.ahmeterdogdu.dto.DtoCustomer;
import com.ahmeterdogdu.services.ICustomerServices;

@RestController
@RequestMapping("/rest/api/customer")
public class CustomerControllerImpl implements ICustomerController {
	
	@Autowired
	private ICustomerServices customerServices;

	@GetMapping("/list/{id}")
	@Override
	public DtoCustomer findCustomerById(@PathVariable(name = "id") Integer id) {
		return customerServices.findCustomerById(id);
	}

}
