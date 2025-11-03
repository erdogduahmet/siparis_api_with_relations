package com.ahmeterdogdu.controller;

import java.util.List;

import com.ahmeterdogdu.dto.DtoOrder;

public interface IOrderController {
	
	public List<DtoOrder> findAllOrders();
	
	public DtoOrder getOrderById(Integer id);

}
