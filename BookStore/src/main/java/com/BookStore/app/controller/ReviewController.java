package com.BookStore.app.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.BookStore.app.exception.BookException;
import com.BookStore.app.exception.UserException;
import com.BookStore.app.model.Review;
import com.BookStore.app.model.User;
import com.BookStore.app.request.ReviewRequest;
import com.BookStore.app.service.ReviewService;
import com.BookStore.app.service.UserService;

@RestController
@RequestMapping("/api/reviews")
public class ReviewController {
	
	private ReviewService reviewService;
	private UserService userService;
	
	public ReviewController(ReviewService reviewService,UserService userService) {
		this.reviewService=reviewService;
		this.userService=userService;
		// TODO Auto-generated constructor stub
	}
	@PostMapping("/create")
	public ResponseEntity<Review> createReviewHandler(@RequestBody ReviewRequest req,@RequestHeader("Authorization") String jwt) throws UserException, BookException{
		User user=userService.findUserProfileByJwt(jwt);
		System.out.println("book id "+req.getBookId()+" - "+req.getReview());
		Review review=reviewService.createReview(req, user);
		System.out.println("book review "+req.getReview());
		return new ResponseEntity<Review>(review,HttpStatus.ACCEPTED);
	}
	
	@GetMapping("/book/{bookId}")
	public ResponseEntity<List<Review>> getBooksReviewHandler(@PathVariable Long bookId){
		List<Review>reviews=reviewService.getAllReview(bookId);
		return new ResponseEntity<List<Review>>(reviews,HttpStatus.OK);
	}

}
