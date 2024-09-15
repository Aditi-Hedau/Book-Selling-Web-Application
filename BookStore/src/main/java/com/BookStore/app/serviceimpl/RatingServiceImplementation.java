package com.BookStore.app.serviceimpl;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import com.BookStore.app.exception.BookException;
import com.BookStore.app.model.Book;
import com.BookStore.app.model.Rating;
import com.BookStore.app.model.User;
import com.BookStore.app.repository.RatingRepository;
import com.BookStore.app.request.RatingRequest;
import com.BookStore.app.service.BookService;
import com.BookStore.app.service.RatingServices;

@Service
public class RatingServiceImplementation implements RatingServices{
	
	private RatingRepository ratingRepository;
	private BookService bookService;
	
	public RatingServiceImplementation(RatingRepository ratingRepository,BookService bookService) {
		this.ratingRepository=ratingRepository;
		this.bookService=bookService;
	}

	@Override
	public Rating createRating(RatingRequest req,User user) throws BookException {
		
		Book book=bookService.findBookById(req.getBookId());
		
		Rating rating=new Rating();
		rating.setBook(book);
		rating.setUser(user);
		rating.setRating(req.getRating());
		rating.setCreatedAt(LocalDateTime.now());
		
		return ratingRepository.save(rating);
	}

	@Override
	public List<Rating> getBooksRating(Long bookId) {
		// TODO Auto-generated method stub
		return ratingRepository.getAllBooksRating(bookId);
	}
	
	

}
