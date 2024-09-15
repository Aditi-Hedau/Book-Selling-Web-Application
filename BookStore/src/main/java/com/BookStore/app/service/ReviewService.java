package com.BookStore.app.service;

import java.util.List;

import com.BookStore.app.request.ReviewRequest;
import com.BookStore.app.exception.BookException;
import com.BookStore.app.model.Review;
import com.BookStore.app.model.User;

public interface ReviewService {

	public Review createReview(ReviewRequest req,User user) throws BookException;
	
	public List<Review> getAllReview(Long bookId);
	
	
}
