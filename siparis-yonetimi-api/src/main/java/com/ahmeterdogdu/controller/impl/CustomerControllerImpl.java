package com.ahmeterdogdu.controller.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ahmeterdogdu.controller.ICustomerController;
import com.ahmeterdogdu.dto.DtoCustomer;
import com.ahmeterdogdu.dto.DtoCustomerIU;
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

	@GetMapping("/list")
	@Override
	public List<DtoCustomer> gelAllCustomers() {
		return customerServices.gelAllCustomers();
	}

	@PostMapping("/save")
	@Override
	public DtoCustomer saveCustomer(@RequestBody DtoCustomerIU dtoCustomerIU) {
		return customerServices.saveCustomer(dtoCustomerIU);
	}

	@DeleteMapping("/delete/{id}")
	@Override
	public boolean deleteCustomer(@PathVariable(name = "id") Integer id) {
		return customerServices.deleteCustomer(id);
	}

	@PutMapping("/update/{id}")
	@Override
	public DtoCustomer updateCustomer(@PathVariable(name = "id") Integer id, @RequestBody DtoCustomerIU dtoCustomerIU) {
		return customerServices.updateCustomer(id, dtoCustomerIU);
	}

}
