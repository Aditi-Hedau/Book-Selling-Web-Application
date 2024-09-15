package com.BookStore.app.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.BookStore.app.model.Review;

public interface ReviewRepository extends JpaRepository<Review, Long> {

	@Query("Select r from Rating r where r.book.id=:bookId")
	public List<Review> getAllBooksReview(@Param("bookId") Long bookId);
}
