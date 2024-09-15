package com.BookStore.app.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.BookStore.app.exception.BookException;
import com.BookStore.app.model.Book;
import com.BookStore.app.request.CreateBookRequest;

public interface BookService {
	
	// only for admin
	public Book createBook(CreateBookRequest req) throws BookException;
	
	public String deleteBook(Long bookId) throws BookException;
	
	public Book updateBook(Long bookId,Book book)throws BookException;
	
	public List<Book> getAllBooks();
	
	// for user and admin both
	public Book findBookById(Long id) throws BookException;
	
	public List<Book> findBookByCategory(String category);
	
	public List<Book> searchBook(String query);
	
//	public List<Product> getAllProduct(int minPrice, int maxPrice,int minDiscount, String category, String sort,int pageNumber, int pageSize);
	public Page<Book> getAllBook(String category, Integer minPrice, Integer maxPrice, Integer minDiscount,String sort, String stock, Integer pageNumber, Integer pageSize);
	
	public List<Book> recentlyAddedBook();
	
	

}
