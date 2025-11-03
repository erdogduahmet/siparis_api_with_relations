package com.ahmeterdogdu.services.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ahmeterdogdu.dto.DtoCustomer;
import com.ahmeterdogdu.dto.DtoOrder;
import com.ahmeterdogdu.dto.DtoProduct;
import com.ahmeterdogdu.entities.Customer;
import com.ahmeterdogdu.entities.Order;
import com.ahmeterdogdu.entities.Product;
import com.ahmeterdogdu.repository.CustomerRepository;
import com.ahmeterdogdu.services.ICustomerServices;

@Service
public class CustomerServiceImpl implements ICustomerServices{
	
	@Autowired
	private CustomerRepository customerRepository;


	@Override
	public DtoCustomer findCustomerById(Integer id) {
		DtoCustomer dtoCustomer=new DtoCustomer();
		Optional<Customer> optional=customerRepository.findById(id);
		if (optional.isEmpty()) {
			return null;
		}
		
		Customer customer=optional.get();
		List<Order> dbOrders=optional.get().getOrder();
		BeanUtils.copyProperties(customer, dtoCustomer);
		if (dbOrders!=null && !dbOrders.isEmpty()) {
			for (Order order : dbOrders) {
				DtoOrder dtoOrder=new DtoOrder();
				BeanUtils.copyProperties(order, dtoOrder);
				dtoCustomer.getOrder().add(dtoOrder);
				
				if (order.getProduct()!=null && !order.getProduct().isEmpty()) {
					for (Product product: order.getProduct()) {
						DtoProduct dtoProduct=new DtoProduct();
						BeanUtils.copyProperties(product, dtoProduct);
						dtoOrder.getProduct().add(dtoProduct);
					}
				}
			}
		}
		
		return dtoCustomer;
	}

}
