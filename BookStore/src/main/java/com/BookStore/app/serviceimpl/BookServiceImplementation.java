package com.BookStore.app.serviceimpl;


import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.BookStore.app.exception.BookException;
import com.BookStore.app.model.Category;
import com.BookStore.app.model.Book;
import com.BookStore.app.repository.CategoryRepository;
import com.BookStore.app.repository.BookRepository;
import com.BookStore.app.request.CreateBookRequest;
import com.BookStore.app.service.BookService;
import com.BookStore.app.service.UserService;
//import com.BookStore.app.user.domain.BookSubCategory;

@Service
public class BookServiceImplementation implements BookService {
	
	private BookRepository bookRepository;
	private UserService userService;
	private CategoryRepository categoryRepository;
	
	public BookServiceImplementation(BookRepository bookRepository,UserService userService,CategoryRepository categoryRepository) {
		this.bookRepository=bookRepository;
		this.userService=userService;
		this.categoryRepository=categoryRepository;
	}
	

	@Override
	public Book createBook(CreateBookRequest req) {
		
		Category topLevel=categoryRepository.findByName(req.getTopLavelCategory());
		
		if(topLevel==null) {
			
			Category topLavelCategory=new Category();
			topLavelCategory.setName(req.getTopLavelCategory());
			topLavelCategory.setLevel(1);
			
			topLevel= categoryRepository.save(topLavelCategory);
		}
		
		Category secondLevel=categoryRepository.
				findByNameAndParant(req.getSecondLavelCategory(),topLevel.getName());
		if(secondLevel==null) {
			
			Category secondLavelCategory=new Category();
			secondLavelCategory.setName(req.getSecondLavelCategory());
			secondLavelCategory.setParentCategory(topLevel);
			secondLavelCategory.setLevel(2);
			
			secondLevel= categoryRepository.save(secondLavelCategory);
		}

		Category thirdLevel=categoryRepository.findByNameAndParant(req.getThirdLavelCategory(),secondLevel.getName());
		if(thirdLevel==null) {
			
			Category thirdLavelCategory=new Category();
			thirdLavelCategory.setName(req.getThirdLavelCategory());
			thirdLavelCategory.setParentCategory(secondLevel);
			thirdLavelCategory.setLevel(3);
			
			thirdLevel=categoryRepository.save(thirdLavelCategory);
		}
		
		
		Book book=new Book();
		book.setTitle(req.getTitle());
//		book.setColor(req.getColor());
		book.setDescription(req.getDescription());
//		book.setDiscountedPrice(req.getDiscountedPrice());
//		book.setDiscountPersent(req.getDiscountPersent());
//		book.setImageUrl(req.getImageUrl());
//		book.setBrand(req.getBrand());
		book.setPrice(req.getPrice());
//		book.setSizes(req.getSize());
//		book.setQuantity(req.getQuantity());
//		book.setCategory(thirdLevel);
//		book.setCreatedAt(LocalDateTime.now());
		
		Book savedBook= bookRepository.save(book);
		
		System.out.println("books - "+book);
		
		return savedBook;
	}

	@Override
	public String deleteBook(Long bookId) throws BookException {
		
		Book book=findBookById(bookId);
		
		System.out.println("delete book "+book.getId()+" - "+bookId);
//		book.getSizes().clear();
//		bookRepository.save(book);
//		book.getCategory().
		bookRepository.delete(book);
		
		return "Book deleted Successfully";
	}

	@Override
	public Book updateBook(Long bookId,Book req) throws BookException {
		Book book=findBookById(bookId);
		
//		if(req.getQuantity()!=0) {
//			book.setQuantity(req.getQuantity());
//		}
		if(req.getDescription()!=null) {
			book.setDescription(req.getDescription());
		}
		
		
			
		
		return bookRepository.save(book);
	}

	@Override
	public List<Book> getAllBooks() {
		return bookRepository.findAll();
	}

	@Override
	public Book findBookById(Long id) throws BookException {
		Optional<Book> opt=bookRepository.findById(id);
		
		if(opt.isPresent()) {
			return opt.get();
		}
		throw new BookException("book not found with id "+id);
	}

	@Override
	public List<Book> findBookByCategory(String category) {
		
		System.out.println("category --- "+category);
		
		List<Book> books = bookRepository.findByCategory(category);
		
		return books;
	}

	@Override
	public List<Book> searchBook(String query) {
		List<Book> books=bookRepository.searchBook(query);
		return books;
	}



	
	
	@Override
	public Page<Book> getAllBook(String category, 
			Integer minPrice, Integer maxPrice, 
			Integer minDiscount,String sort, String stock, Integer pageNumber, Integer pageSize ) {

		Pageable pageable = PageRequest.of(pageNumber, pageSize);
		
		List<Book> books = bookRepository.filterBooks(category, minPrice, maxPrice, minDiscount, sort);
		
		
//		if (!colors.isEmpty()) {
//			books = books.stream()
//			        .filter(p -> colors.stream().anyMatch(c -> c.equalsIgnoreCase(p.getColor())))
//			        .collect(Collectors.toList());
//		
//		
//		} 

		if(stock!=null) {

			if(stock.equals("in_stock")) {
				books=books.stream().filter(p->p.getQuantity()>0).collect(Collectors.toList());
			}
			else if (stock.equals("out_of_stock")) {
				books=books.stream().filter(p->p.getQuantity()<1).collect(Collectors.toList());				
			}
				
					
		}
		int startIndex = (int) pageable.getOffset();
		int endIndex = Math.min(startIndex + pageable.getPageSize(), books.size());

		List<Book> pageContent = books.subList(startIndex, endIndex);
		Page<Book> filteredBooks = new PageImpl<>(pageContent, pageable, books.size());
	    return filteredBooks; // If color list is empty, do nothing and return all books
		
		
	}


	@Override
	public List<Book> recentlyAddedBook() {
		
		return bookRepository.findTop10ByOrderByCreatedAtDesc();
	}

}
