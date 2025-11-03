package com.ahmeterdogdu.controller.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ahmeterdogdu.controller.IOrderController;
import com.ahmeterdogdu.dto.DtoOrder;
import com.ahmeterdogdu.services.IOrderService;

@RestController
@RequestMapping("rest/api/order")
public class OrderControllerImpl implements IOrderController {
	
	@Autowired
	private IOrderService orderService;

	@GetMapping("/list")
	@Override
	public List<DtoOrder> findAllOrders() {
		return orderService.findAllOrders();
	}

	@GetMapping("/list/{id}")
	@Override
	public DtoOrder getOrderById(@PathVariable(name = "id") Integer id) {
		return orderService.getOrderById(id);
	}

}
