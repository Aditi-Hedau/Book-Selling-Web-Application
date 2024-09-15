package com.BookStore.app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.BookStore.app.model.Rating;

public interface RatingRepository extends JpaRepository<Rating, Long> {
	
	@Query("Select r From Rating r where r.book.id=:bookId")
	public List<Rating> getAllBooksRating(@Param("bookId") Long bookId);

}
