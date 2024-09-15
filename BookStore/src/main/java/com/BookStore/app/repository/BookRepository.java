//package com.BookStore.app.repository;
//
//import java.util.List;
//
//import org.springframework.data.domain.Page;
//import org.springframework.data.domain.Pageable;
//import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.jpa.repository.Query;
//import org.springframework.data.repository.query.Param;
//
//import com.BookStore.app.model.Book;
//
//
//public interface BookRepository extends JpaRepository<Book, Long> {
//
//	@Query("SELECT b From Book b Where LOWER(b.category.name)=:category")
//	public List<Book> findByCategory(@Param("category") String category);
//	
//	@Query("SELECT b From Book b where LOWER(b.title) Like %:query% OR LOWER(b.description) Like %:query% OR LOWER(b.category.name) LIKE %:query%")
//	public List<Book> searchBook(@Param("query")String query);
//	
//
//
//	
//	@Query("SELECT b FROM Book b " +
//	        "WHERE (b.category.name = :category OR :category = '') " +
//	        "AND ((:minPrice IS NULL AND :maxPrice IS NULL) OR (b.discountedPrice BETWEEN :minPrice AND :maxPrice)) " +
//		    "AND (:minDiscount IS NULL OR b.discountPercent >= :minDiscount) " +
//		    "ORDER BY " +
//		    "CASE WHEN :sort = 'price_low' THEN b.discountedPrice END ASC, " +
//		    "CASE WHEN :sort = 'price_high' THEN b.discountedPrice END DESC, "+
//		    "b.createdAt DESC")
//	List<Book> filterBooks(
//	        @Param("category") String category,
//			@Param("minPrice") Integer minPrice,
//			@Param("maxPrice") Integer maxPrice,
//			@Param("minDiscount") Integer minDiscount,
//			@Param("sort") String sort
//			);
//	
//	public List<Book> findTop10ByOrderByCreatedAtDesc();
//}

package com.BookStore.app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.BookStore.app.model.Book;

public interface BookRepository extends JpaRepository<Book, Long> {

    @Query("SELECT b From Book b WHERE LOWER(b.category.name) = LOWER(:category)")
    List<Book> findByCategory(@Param("category") String category);

    @Query("SELECT b From Book b WHERE LOWER(b.title) LIKE %:query% " +
           "OR LOWER(b.description) LIKE %:query% " +
           "OR LOWER(b.category.name) LIKE %:query%")
    List<Book> searchBook(@Param("query") String query);

    @Query("SELECT b FROM Book b " +
           "WHERE (:category IS NULL OR LOWER(b.category.name) = LOWER(:category)) " +
           "AND (:minPrice IS NULL OR :maxPrice IS NULL OR b.discountedPrice BETWEEN :minPrice AND :maxPrice) " +
           "AND (:minDiscount IS NULL OR b.discountPercent >= :minDiscount) " +
           "ORDER BY " +
           "CASE WHEN :sort = 'price_low' THEN b.discountedPrice END ASC, " +
           "CASE WHEN :sort = 'price_high' THEN b.discountedPrice END DESC, " +
           "b.createdAt DESC")
    List<Book> filterBooks(
        @Param("category") String category,
        @Param("minPrice") Integer minPrice,
        @Param("maxPrice") Integer maxPrice,
        @Param("minDiscount") Integer minDiscount,
        @Param("sort") String sort
    );

    List<Book> findTop10ByOrderByCreatedAtDesc();
}

