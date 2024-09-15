package com.BookStore.app.controller;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.BookStore.app.exception.BookException;
import com.BookStore.app.model.Book;
import com.BookStore.app.service.BookService;
//import com.BookStore.app.user.domain.BookSubCategory;

@RestController
@RequestMapping("/api")
public class UserBookController {
	
	private BookService bookService;
	
	public UserBookController(BookService bookService) {
		this.bookService=bookService;
	}
	
	
	@GetMapping("/books")
	public ResponseEntity<Page<Book>> findBookByCategoryHandler(@RequestParam String category,
			@RequestParam Integer minPrice,@RequestParam Integer maxPrice, @RequestParam Integer minDiscount, 
			@RequestParam String sort, @RequestParam String stock, @RequestParam Integer pageNumber,
			@RequestParam Integer pageSize){

		
		Page<Book> res= bookService.getAllBook(category, minPrice, maxPrice, minDiscount, sort,stock,pageNumber,pageSize);
		
		System.out.println("complete books");
		return new ResponseEntity<>(res,HttpStatus.ACCEPTED);
		
	}
	

	
	@GetMapping("/books/id/{bookId}")
	public ResponseEntity<Book> findBookByIdHandler(@PathVariable Long bookId) throws BookException{
		
		Book book=bookService.findBookById(bookId);
		
		return new ResponseEntity<Book>(book,HttpStatus.ACCEPTED);
	}

	@GetMapping("/books/search")
	public ResponseEntity<List<Book>> searchBookHandler(@RequestParam String q){
		
		List<Book> books=bookService.searchBook(q);
		
		return new ResponseEntity<List<Book>>(books,HttpStatus.OK);
		
	}
}
