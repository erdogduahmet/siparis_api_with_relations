package com.ahmeterdogdu.services;

import java.util.List;

import com.ahmeterdogdu.dto.DtoOrder;
import com.ahmeterdogdu.dto.DtoOrderIU;

public interface IOrderService {
	
	public DtoOrder createOrder(DtoOrderIU dtoOrderIU);
	public List<DtoOrder> findAllOrders();
	public DtoOrder getOrderById(Integer id);
	public boolean deleteOrder(Integer id);
	public DtoOrder updateOrderStatus(Integer id,String status);

}
