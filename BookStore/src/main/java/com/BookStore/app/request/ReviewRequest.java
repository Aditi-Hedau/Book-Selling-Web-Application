package com.BookStore.app.request;

public class ReviewRequest {
	
	private Long bookId;
	private String review;
	public Long getBookId() {
		return bookId;
	}
	public void setBookId(Long bookId) {
		this.bookId = bookId;
	}
	public String getReview() {
		return review;
	}
	public void setReview(String review) {
		this.review = review;
	}

	
}
