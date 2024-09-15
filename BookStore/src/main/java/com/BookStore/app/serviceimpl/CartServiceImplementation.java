package com.BookStore.app.serviceimpl;

import org.springframework.stereotype.Service;

import com.BookStore.app.exception.BookException;
import com.BookStore.app.model.Cart;
import com.BookStore.app.model.CartItem;
import com.BookStore.app.model.Book;
import com.BookStore.app.model.User;
import com.BookStore.app.repository.CartRepository;
import com.BookStore.app.request.AddItemRequest;
import com.BookStore.app.service.BookService;
import com.BookStore.app.service.CartItemService;
import com.BookStore.app.service.CartService;

@Service
public class CartServiceImplementation implements CartService{
	
	private CartRepository cartRepository;
	private CartItemService cartItemService;
	private BookService bookService;
	
	
	public CartServiceImplementation(CartRepository cartRepository,CartItemService cartItemService,
			BookService bookService) {
		this.cartRepository=cartRepository;
		this.bookService=bookService;
		this.cartItemService=cartItemService;
	}

	@Override
	public Cart createCart(User user) {
		
		Cart cart = new Cart();
		cart.setUser(user);
		Cart createdCart=cartRepository.save(cart);
		return createdCart;
	}
	
	public Cart findUserCart(Long userId) {
		Cart cart =	cartRepository.findByUserId(userId);
		int totalPrice=0;
		int totalDiscountedPrice=0;
		int totalItem=0;
		for(CartItem cartsItem : cart.getCartItems()) {
			totalPrice+=cartsItem.getPrice();
			totalDiscountedPrice+=cartsItem.getDiscountedPrice();
			totalItem+=cartsItem.getQuantity();
		}
		
		cart.setTotalPrice(totalPrice);
		cart.setTotalItem(cart.getCartItems().size());
		cart.setTotalDiscountedPrice(totalDiscountedPrice);
		cart.setDiscounte(totalPrice-totalDiscountedPrice);
		cart.setTotalItem(totalItem);
		
		return cartRepository.save(cart);
		
	}

	@Override
	public CartItem addCartItem(Long userId, AddItemRequest req) throws BookException {
		Cart cart=cartRepository.findByUserId(userId);
		Book book=bookService.findBookById(req.getBookId());
		
		CartItem isPresent=cartItemService.isCartItemExist(cart, book,userId);
		
		if(isPresent == null) {
			CartItem cartItem = new CartItem();
			cartItem.setBook(book);
			cartItem.setCart(cart);
			cartItem.setQuantity(req.getQuantity());
			cartItem.setUserId(userId);
			
			
			int price=(int) (req.getQuantity()*book.getDiscountedPrice());
			cartItem.setPrice(price);
//			cartItem.setSize(req.getSize());
			
			CartItem createdCartItem=cartItemService.createCartItem(cartItem);
			cart.getCartItems().add(createdCartItem);
			return createdCartItem;
		}
		
		
		return isPresent;
	}

}
