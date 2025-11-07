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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ahmeterdogdu.controller.IOrderController;
import com.ahmeterdogdu.dto.DtoOrder;
import com.ahmeterdogdu.dto.DtoOrderIU;
import com.ahmeterdogdu.services.IOrderService;

import jakarta.validation.Valid;

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

	@PostMapping("/add")
	@Override
	public DtoOrder createOrder(@RequestBody @Valid DtoOrderIU dtoOrderIU) {
		return orderService.createOrder(dtoOrderIU);
	}

	@DeleteMapping("/delete/{id}")
	@Override
	public boolean deleteOrder(@PathVariable(name = "id") Integer id) {
		return orderService.deleteOrder(id);
	}

	@PutMapping("/update/{id}/status")
	@Override
	public DtoOrder updateOrderStatus(@PathVariable(name = "id")Integer id,@RequestParam String status) {
		return orderService.updateOrderStatus(id, status);
	}

}
