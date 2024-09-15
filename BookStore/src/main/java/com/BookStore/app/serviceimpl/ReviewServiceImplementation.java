package com.BookStore.app.serviceimpl;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import com.BookStore.app.exception.BookException;
import com.BookStore.app.model.Book;
import com.BookStore.app.model.Review;
import com.BookStore.app.model.User;
import com.BookStore.app.repository.BookRepository;
import com.BookStore.app.repository.ReviewRepository;
import com.BookStore.app.request.ReviewRequest;
import com.BookStore.app.service.BookService;
import com.BookStore.app.service.ReviewService;

@Service
public class ReviewServiceImplementation implements ReviewService {
	
	private ReviewRepository reviewRepository;
	private BookService bookService;
	private BookRepository bookRepository;
	
	public ReviewServiceImplementation(ReviewRepository reviewRepository,BookService bookService,BookRepository bookRepository) {
		this.reviewRepository=reviewRepository;
		this.bookService=bookService;
		this.bookRepository=bookRepository;
	}

	@Override
	public Review createReview(ReviewRequest req,User user) throws BookException {
		// TODO Auto-generated method stub
		Book book=bookService.findBookById(req.getBookId());
		Review review=new Review();
		review.setUser(user);
		review.setBook(book);
		review.setReview(req.getReview());
		review.setCreatedAt(LocalDateTime.now());
		
//		book.getReviews().add(review);
		bookRepository.save(book);
		return reviewRepository.save(review);
	}

	@Override
	public List<Review> getAllReview(Long bookId) {
		
		return reviewRepository.getAllBooksReview(bookId);
	}

}
