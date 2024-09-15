package com.BookStore.app.service;

import java.util.List;

import com.BookStore.app.request.RatingRequest;
import com.BookStore.app.exception.BookException;
import com.BookStore.app.model.Rating;
import com.BookStore.app.model.User;

public interface RatingServices {
	
	public Rating createRating(RatingRequest req,User user) throws BookException;
	
	public List<Rating> getBooksRating(Long bookId);

}
