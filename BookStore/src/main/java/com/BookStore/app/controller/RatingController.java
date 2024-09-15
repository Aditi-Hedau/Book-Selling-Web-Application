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
import com.BookStore.app.model.Rating;
import com.BookStore.app.model.Review;
import com.BookStore.app.model.User;
import com.BookStore.app.request.RatingRequest;
import com.BookStore.app.service.RatingServices;
import com.BookStore.app.service.UserService;

@RestController
@RequestMapping("/api/ratings")
public class RatingController {
	
	private UserService userService;
	private RatingServices ratingServices;
	
	public RatingController(UserService userService,RatingServices ratingServices) {
		this.ratingServices=ratingServices;
		this.userService=userService;
		// TODO Auto-generated constructor stub
	}

	@PostMapping("/create")
	public ResponseEntity<Rating> createRatingHandler(@RequestBody RatingRequest req,@RequestHeader("Authorization") String jwt) throws UserException, BookException{
		User user=userService.findUserProfileByJwt(jwt);
		Rating rating=ratingServices.createRating(req, user);
		return new ResponseEntity<>(rating,HttpStatus.ACCEPTED);
	}
	
	@GetMapping("/book/{bookId}")
	public ResponseEntity<List<Rating>> getBooksReviewHandler(@PathVariable Long bookId){
	
		List<Rating> ratings=ratingServices.getBooksRating(bookId);
		return new ResponseEntity<>(ratings,HttpStatus.OK);
	}
}
