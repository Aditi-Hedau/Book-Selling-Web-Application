package com.BookStore.app.serviceimpl;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.BookStore.app.exception.CartItemException;
import com.BookStore.app.exception.UserException;
import com.BookStore.app.model.Cart;
import com.BookStore.app.model.CartItem;
import com.BookStore.app.model.Book;
import com.BookStore.app.model.User;
import com.BookStore.app.repository.CartItemRepository;
import com.BookStore.app.repository.CartRepository;
import com.BookStore.app.service.CartItemService;
import com.BookStore.app.service.UserService;

@Service
public class CartItemServiceImplementation implements CartItemService {
	
	private CartItemRepository cartItemRepository;
	private UserService userService;
	private CartRepository cartRepository;
	
	public CartItemServiceImplementation(CartItemRepository cartItemRepository,UserService userService) {
		this.cartItemRepository=cartItemRepository;
		this.userService=userService;
	}

	@Override
	public CartItem createCartItem(CartItem cartItem) {
		
		cartItem.setQuantity(1);
		cartItem.setPrice((int) (cartItem.getBook().getPrice()*cartItem.getQuantity()));
		cartItem.setDiscountedPrice((int) (cartItem.getBook().getDiscountedPrice()*cartItem.getQuantity()));
		
		CartItem createdCartItem=cartItemRepository.save(cartItem);
		
		return createdCartItem;
	}

	@Override
	public CartItem updateCartItem(Long userId, Long id, CartItem cartItem) throws CartItemException, UserException {
		
		CartItem item=findCartItemById(id);
		User user=userService.findUserById(item.getUserId());
		
		
		if(user.getId().equals(userId)) {
			
			item.setQuantity(cartItem.getQuantity());
			item.setPrice((int) (item.getQuantity()*item.getBook().getPrice()));
			item.setDiscountedPrice((int) (item.getQuantity()*item.getBook().getDiscountedPrice()));
			
			return cartItemRepository.save(item);
				
			
		}
		else {
			throw new CartItemException("You can't update  another users cart_item");
		}
		
	}

	@Override
	public CartItem isCartItemExist(Cart cart,Book book, Long userId) {
		
		CartItem cartItem=cartItemRepository.isCartItemExist(cart, book, userId);
		
		return cartItem;
	}
	
	

	@Override
	public void removeCartItem(Long userId,Long cartItemId) throws CartItemException, UserException {
		
		System.out.println("userId- "+userId+" cartItemId "+cartItemId);
		
		CartItem cartItem=findCartItemById(cartItemId);
		
		User user=userService.findUserById(cartItem.getUserId());
		User reqUser=userService.findUserById(userId);
		
		if(user.getId().equals(reqUser.getId())) {
			cartItemRepository.deleteById(cartItem.getId());
		}
		else {
			throw new UserException("you can't remove anothor users item");
		}
		
	}

	@Override
	public CartItem findCartItemById(Long cartItemId) throws CartItemException {
		Optional<CartItem> opt=cartItemRepository.findById(cartItemId);
		
		if(opt.isPresent()) {
			return opt.get();
		}
		throw new CartItemException("cartItem not found with id : "+cartItemId);
	}

}
