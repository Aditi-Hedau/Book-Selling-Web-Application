package com.BookStore.app.service;

import com.BookStore.app.exception.BookException;
import com.BookStore.app.model.Cart;
import com.BookStore.app.model.CartItem;
import com.BookStore.app.model.User;
import com.BookStore.app.request.AddItemRequest;

public interface CartService {
	
	public Cart createCart(User user);
	
	public CartItem addCartItem(Long userId,AddItemRequest req) throws BookException;
	
	public Cart findUserCart(Long userId);

}
