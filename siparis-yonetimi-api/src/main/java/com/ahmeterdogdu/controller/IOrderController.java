package com.ahmeterdogdu.controller;

import java.util.List;

import com.ahmeterdogdu.dto.DtoOrder;
import com.ahmeterdogdu.dto.DtoOrderIU;

public interface IOrderController {
	
	public DtoOrder createOrder(DtoOrderIU dtoOrderIU);
	
	public List<DtoOrder> findAllOrders();
	
	public DtoOrder getOrderById(Integer id);
	
	public boolean deleteOrder(Integer id);
	
	public DtoOrder updateOrderStatus(Integer id,String status);

}
