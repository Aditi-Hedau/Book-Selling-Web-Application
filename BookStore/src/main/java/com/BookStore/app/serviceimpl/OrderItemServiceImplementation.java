package com.BookStore.app.serviceimpl;

import org.springframework.stereotype.Service;

import com.BookStore.app.model.OrderItem;
import com.BookStore.app.repository.OrderItemRepository;
import com.BookStore.app.service.OrderItemService;

@Service
public class OrderItemServiceImplementation implements OrderItemService {

	private OrderItemRepository orderItemRepository;
	public OrderItemServiceImplementation(OrderItemRepository orderItemRepository) {
		this.orderItemRepository=orderItemRepository;
	}
	@Override
	public OrderItem createOrderItem(OrderItem orderItem) {
		
		return orderItemRepository.save(orderItem);
	}

}
