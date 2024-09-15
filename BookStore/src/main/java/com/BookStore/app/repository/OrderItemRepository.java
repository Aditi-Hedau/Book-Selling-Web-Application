package com.BookStore.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.BookStore.app.model.OrderItem;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {

}
