package com.BookStore.app.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.BookStore.app.exception.BookException;
import com.BookStore.app.model.Book;
import com.BookStore.app.request.CreateBookRequest;
import com.BookStore.app.response.ApiResponse;
import com.BookStore.app.service.BookService;

@RestController
@RequestMapping("/api/admin/books")
public class AdminBookController {
	
	private BookService bookService;
	
	public AdminBookController(BookService bookService) {
		this.bookService = bookService;
	}
	
	@PostMapping("/")
	public ResponseEntity<Book> createBookHandler(@RequestBody CreateBookRequest req) throws BookException{
		
		Book createdBook = bookService.createBook(req);
		
		return new ResponseEntity<Book>(createdBook,HttpStatus.ACCEPTED);
		
	}
	
	@DeleteMapping("/{bookId}/delete")
	public ResponseEntity<ApiResponse> deleteBookHandler(@PathVariable Long bookId) throws BookException{
		
		System.out.println("dlete book controller .... ");
		String msg=bookService.deleteBook(bookId);
		System.out.println("delete book controller .... msg "+msg);
		ApiResponse res=new ApiResponse(msg,true);
		
		return new ResponseEntity<ApiResponse>(res,HttpStatus.ACCEPTED);
		
	}
	
	@GetMapping("/all")
	public ResponseEntity<List<Book>> findAllBook(){
		
		List<Book> books = bookService.getAllBooks();
		
		return new ResponseEntity<List<Book>>(books,HttpStatus.OK);
	}
	
	@GetMapping("/recent")
	public ResponseEntity<List<Book>> recentlyAddedBook(){
		
		List<Book> books = bookService.recentlyAddedBook();
		
		return new ResponseEntity<List<Book>>(books,HttpStatus.OK);
	}
	
	
	@PutMapping("/{bookId}/update")
	public ResponseEntity<Book> updateBookHandler(@RequestBody Book req,@PathVariable Long bookId) throws BookException{
		
		Book updatedBook=bookService.updateBook(bookId, req);
		
		return new ResponseEntity<Book>(updatedBook,HttpStatus.OK);
	}
	
	@PostMapping("/creates")
	public ResponseEntity<ApiResponse> createMultipleBook(@RequestBody CreateBookRequest[] reqs) throws BookException{
		
		for(CreateBookRequest book:reqs) {
			bookService.createBook(book);
		}
		
		ApiResponse res=new ApiResponse("products created successfully",true);
		return new ResponseEntity<ApiResponse>(res,HttpStatus.ACCEPTED);
	}

}
