package com.example.demo.service;

import org.springframework.stereotype.Service;

import com.example.demo.entity.Order;
import com.example.demo.entity.OrderStatus;
import com.example.demo.repository.OrderRepository;

@Service
public class OrderService {
	
	/*
	 * getOrderStatus(long orderId) returns the order’s status and total.
	 * 
	 * getFailedPayments(long customerId, int lastNDays) returns failed payments
	 * for that customer.
	 * 
	 * cancelOrder(long orderId) sets the status to CANCELLED,
	 * but only if the order is not already SHIPPED.
	 * 
	 */
	
	private final OrderRepository orderRepository;

	public OrderService(OrderRepository orderRepository) {
		this.orderRepository = orderRepository;
	}
	
	public OrderStatus getOrderStatus(Long orderId) {
		Order order = orderRepository.findById(orderId).orElseThrow(() -> new RuntimeException("No Order Found"));
		return order.getStatus();
	}
	
	public String cancelOrder(Long orderId) {
		Order order = orderRepository.findById(orderId).orElseThrow(() -> new RuntimeException("No Order Found"));
		OrderStatus status = order.getStatus();
		if(status==OrderStatus.SHIPPED) {
			throw new RuntimeException("Cannot cancel the order at this time");
		}
		else if(status==OrderStatus.CANCELLED) {
			throw new RuntimeException("Order is already cancelled");
		}
		order.setStatus(OrderStatus.CANCELLED);
		orderRepository.save(order);
		return "Order has been cancelled";
	}
	
	

}
