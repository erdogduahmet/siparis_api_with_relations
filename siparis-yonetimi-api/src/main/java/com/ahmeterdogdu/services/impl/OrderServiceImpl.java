package com.ahmeterdogdu.services.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ahmeterdogdu.dto.DtoCustomer;
import com.ahmeterdogdu.dto.DtoOrder;
import com.ahmeterdogdu.dto.DtoOrderIU;
import com.ahmeterdogdu.dto.DtoProduct;
import com.ahmeterdogdu.entities.Order;
import com.ahmeterdogdu.entities.Product;
import com.ahmeterdogdu.repository.OrderRepository;
import com.ahmeterdogdu.services.IOrderService;

@Service
public class OrderServiceImpl implements IOrderService {

	@Autowired
	private OrderRepository orderRepository;

	@Override
	public DtoOrder createOrder(DtoOrderIU dtoOrderIU) {
		return null;
	}

	@Override
	public List<DtoOrder> findAllOrders() {
		List<DtoOrder> dtoOrderList = new ArrayList<>();

		List<Order> orderList = orderRepository.findAll();
		if (orderList != null && !orderList.isEmpty()) {
			for (Order order : orderList) {
				DtoOrder dtoOrder = new DtoOrder();
				BeanUtils.copyProperties(order, dtoOrder);
				DtoCustomer dtoCustomer = new DtoCustomer();
				dtoCustomer.setId(order.getCustomer().getId());
				dtoCustomer.setFirstName(order.getCustomer().getFirstName());
				dtoCustomer.setLastName(order.getCustomer().getLastName());
				dtoCustomer.setEMail(order.getCustomer().getEMail());

				dtoOrder.setCustomer(dtoCustomer);
				dtoOrderList.add(dtoOrder);

				if (order.getProduct()!=null && !order.getProduct().isEmpty()) {
					for (Product product : order.getProduct()) {
						DtoProduct dtoProduct=new DtoProduct();
						BeanUtils.copyProperties(product, dtoProduct);
						dtoOrder.getProduct().add(dtoProduct);
					}
				}
			}
		}
		return dtoOrderList;
	}

	@Override
	public boolean deleteOrder(Integer id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public DtoOrder getOrderById(Integer id) {
		DtoOrder dtoOrder = new DtoOrder();
		Optional<Order> optional = orderRepository.findById(id);

		if (optional.isEmpty()) {
			return null;
		}
		Order order = optional.get();
		BeanUtils.copyProperties(order, dtoOrder);

		if (order.getProduct() != null && !order.getProduct().isEmpty()) {
			for (Product product : order.getProduct()) {
				DtoProduct dtoProduct = new DtoProduct();
				BeanUtils.copyProperties(product, dtoProduct);

				dtoOrder.getProduct().add(dtoProduct);
			}
		}

		DtoCustomer dtoCustomer = new DtoCustomer();
		dtoCustomer.setId(order.getCustomer().getId());
		dtoCustomer.setFirstName(order.getCustomer().getFirstName());
		dtoCustomer.setLastName(order.getCustomer().getLastName());
		dtoCustomer.setEMail(order.getCustomer().getEMail());
		dtoOrder.setCustomer(dtoCustomer);

		return dtoOrder;
	}

	@Override
	public DtoOrder updateOrderStatus(Integer id, String status) {
		// TODO Auto-generated method stub
		return null;
	}

}
