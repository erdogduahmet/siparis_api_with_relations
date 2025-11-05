package com.ahmeterdogdu.services.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ahmeterdogdu.dto.DtoCustomer;
import com.ahmeterdogdu.dto.DtoOrder;
import com.ahmeterdogdu.dto.DtoOrderIU;
import com.ahmeterdogdu.dto.DtoProduct;
import com.ahmeterdogdu.entities.Customer;
import com.ahmeterdogdu.entities.Order;
import com.ahmeterdogdu.entities.Product;
import com.ahmeterdogdu.repository.CustomerRepository;
import com.ahmeterdogdu.repository.OrderRepository;
import com.ahmeterdogdu.repository.ProductRepository;
import com.ahmeterdogdu.services.IOrderService;

@Service
public class OrderServiceImpl implements IOrderService {

	@Autowired
	private OrderRepository orderRepository;

	@Autowired
	private CustomerRepository customerRepository;

	@Autowired
	private ProductRepository productRepository;

	@Override
	public DtoOrder createOrder(DtoOrderIU dtoOrderIU) {

		// önce ilişkili entityleri Id leri ile db den buluyoruz
		Customer customer = customerRepository.findById(dtoOrderIU.getCustomerId())
				.orElseThrow(() -> new RuntimeException("Müşteri bulunamadı: " + dtoOrderIU.getCustomerId()));

		List<Product> dbProducts = productRepository.findAllById(dtoOrderIU.getProductIds());

		if (dbProducts.isEmpty() && !dtoOrderIU.getProductIds().isEmpty()) {
			throw new RuntimeException("Belirtilen ürünlerden bazıları bulunamadı.");
		}

		// sonra yeni order entity oluşturuyoruz ve dolduruyoruz
		Order newOrder = new Order();
		BeanUtils.copyProperties(dtoOrderIU, newOrder);
		newOrder.setCustomer(customer);// manyToOne
		newOrder.setProduct(dbProducts);// ManyToMany
		newOrder.setOrderDate(new Date());// tarihi o anki tarih olarak ayarla
		newOrder.setStatus(dtoOrderIU.getStatus() != null ? dtoOrderIU.getStatus() : "Pending");

		Order savedOrder = orderRepository.save(newOrder);

		DtoOrder dtoOrder = new DtoOrder();
		BeanUtils.copyProperties(savedOrder, dtoOrder);

		DtoCustomer dtoCustomer = new DtoCustomer();
		BeanUtils.copyProperties(customer, dtoCustomer);
		dtoOrder.setCustomer(dtoCustomer);

		if (savedOrder.getProduct() != null && !savedOrder.getProduct().isEmpty()) {
			for (Product product : savedOrder.getProduct()) {
				DtoProduct dtoProduct = new DtoProduct();
				BeanUtils.copyProperties(product, dtoProduct);
				dtoOrder.getProduct().add(dtoProduct);
			}

		}

		return dtoOrder;
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

				if (order.getProduct() != null && !order.getProduct().isEmpty()) {
					for (Product product : order.getProduct()) {
						DtoProduct dtoProduct = new DtoProduct();
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
		if (!orderRepository.existsById(id)) {
			return false;
		}
		orderRepository.deleteById(id);
		return true;
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
		Optional<Order> optional = orderRepository.findById(id);
		if (optional.isEmpty()) {
			return null;
		}

		Order dbOrder = optional.get();
		dbOrder.setStatus(status);

		Order updatedOrder = orderRepository.save(dbOrder);

		DtoOrder dtoOrder = new DtoOrder();
		BeanUtils.copyProperties(updatedOrder, dtoOrder);

		return getOrderById(updatedOrder.getId());
	}

}
