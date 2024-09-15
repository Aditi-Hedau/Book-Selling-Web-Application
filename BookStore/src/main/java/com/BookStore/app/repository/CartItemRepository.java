package com.BookStore.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.BookStore.app.model.Cart;
import com.BookStore.app.model.CartItem;
import com.BookStore.app.model.Book;

public interface CartItemRepository extends JpaRepository<CartItem, Long>{

	@Query("SELECT ci From CartItem ci Where ci.cart=:cart And ci.book=:book And ci.userId=:userId")
	public CartItem isCartItemExist(@Param("cart")Cart cart,@Param("book")Book book, @Param("userId")Long userId);
	
}
