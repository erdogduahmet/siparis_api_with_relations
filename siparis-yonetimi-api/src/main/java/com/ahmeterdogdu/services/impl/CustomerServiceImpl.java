package com.ahmeterdogdu.services.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ahmeterdogdu.dto.DtoCustomer;
import com.ahmeterdogdu.dto.DtoCustomerIU;
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
	
	private DtoCustomer convertToDtoCustomer(Customer customer) {
		DtoCustomer dtoCustomer=new DtoCustomer();
		BeanUtils.copyProperties(customer, dtoCustomer);
		
		if (customer.getOrder()!=null) {
			List<DtoOrder> dtoOrderList=customer.getOrder().stream()
					.map(this::convertOrderToDtoOrderShallow).collect(Collectors.toList());
			dtoCustomer.setOrder(dtoOrderList);
		}else {
			dtoCustomer.setOrder(new ArrayList<>());
		}
		return dtoCustomer;
	}
	
	private DtoOrder convertOrderToDtoOrderShallow(Order order) {
		DtoOrder dtoOrder=new DtoOrder();
		BeanUtils.copyProperties(order, dtoOrder);
		
		dtoOrder.setCustomer(null);
		dtoOrder.setProduct(new ArrayList<>());
		return dtoOrder;
	}


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


	@Override
	public List<DtoCustomer> gelAllCustomers() {
		List<Customer> customerList =customerRepository.findAll();
		
		return customerList.stream().map(this::convertToDtoCustomer).collect(Collectors.toList());
	}


	@Override
	public DtoCustomer saveCustomer(DtoCustomerIU dtoCustomerIU) {
		Customer customer=new Customer();
		BeanUtils.copyProperties(dtoCustomerIU, customer);
		
		Customer savedCustomer=customerRepository.save(customer);
		
		return convertToDtoCustomer(savedCustomer);
	}


	@Override
	public boolean deleteCustomer(Integer id) {
		if (!customerRepository.existsById(id)) {
			return false;
		}
		
		customerRepository.deleteById(id);
		return true;
	}


	@Override
	public DtoCustomer updateCustomer(Integer id, DtoCustomerIU dtoCustomerIU) {
		Optional<Customer> optional =customerRepository.findById(id);
		if (optional.isEmpty()) {
			return null;
		}
		
		Customer customer=optional.get();
		
		BeanUtils.copyProperties(dtoCustomerIU, customer);
		Customer updatedCustomer=customerRepository.save(customer);
		
		return convertToDtoCustomer(updatedCustomer);
	}

}
