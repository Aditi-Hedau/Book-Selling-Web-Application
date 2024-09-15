package com.BookStore.app.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

@Entity
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "author", nullable = false)
    private String author;

    @Column(name = "description")
    private String description;

    @Column(name = "isbn", unique = true, nullable = false)
    private String isbn;

    @Column(name = "price")
    private double price;

    @Column(name = "discounted_price")
    private double discountedPrice;

    @Column(name = "discount_percent")
    private int discountPercent;

    @Column(name = "quantity")
    private int quantity;

    @Column(name = "publisher")
    private String publisher;

    @Column(name = "publication_date")
    private LocalDateTime publicationDate;

    @Column(name = "cover_image_url")
    private String coverImageUrl;

    @OneToMany(mappedBy = "book", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Review> reviews = new ArrayList<>();

    @Column(name = "num_reviews")
    private int numReviews;

    @ManyToOne()
    @JoinColumn(name = "category_id")
    private Category category;

    private LocalDateTime createdAt;

    public Book() {
    }

    public Book(Long id, String title, String author, String description, String isbn, double price,
                double discountedPrice, int discountPercent, int quantity, String publisher,
                LocalDateTime publicationDate, String coverImageUrl, List<Review> reviews, int numReviews,
                Category category, LocalDateTime createdAt) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.description = description;
        this.isbn = isbn;
        this.price = price;
        this.discountedPrice = discountedPrice;
        this.discountPercent = discountPercent;
        this.quantity = quantity;
        this.publisher = publisher;
        this.publicationDate = publicationDate;
        this.coverImageUrl = coverImageUrl;
        this.reviews = reviews;
        this.numReviews = numReviews;
        this.category = category;
        this.createdAt = createdAt;
    }

    // Getters and Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getDiscountedPrice() {
        return discountedPrice;
    }

    public void setDiscountedPrice(double discountedPrice) {
        this.discountedPrice = discountedPrice;
    }

    public int getDiscountPercent() {
        return discountPercent;
    }

    public void setDiscountPercent(int discountPercent) {
        this.discountPercent = discountPercent;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public LocalDateTime getPublicationDate() {
        return publicationDate;
    }

    public void setPublicationDate(LocalDateTime publicationDate) {
        this.publicationDate = publicationDate;
    }

    public String getCoverImageUrl() {
        return coverImageUrl;
    }

    public void setCoverImageUrl(String coverImageUrl) {
        this.coverImageUrl = coverImageUrl;
    }

    public List<Review> getReviews() {
        return reviews;
    }

    public void setReviews(List<Review> reviews) {
        this.reviews = reviews;
    }

    public int getNumReviews() {
        return numReviews;
    }

    public void setNumReviews(int numReviews) {
        this.numReviews = numReviews;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    // hashCode, equals methods

    @Override
    public int hashCode() {
        return Objects.hash(author, category, description, discountPercent, discountedPrice, id, isbn, numReviews,
                price, publicationDate, publisher, quantity, reviews, title, coverImageUrl);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Book other = (Book) obj;
        return Objects.equals(author, other.author) && Objects.equals(category, other.category)
                && Objects.equals(description, other.description) && discountPercent == other.discountPercent
                && Double.doubleToLongBits(discountedPrice) == Double.doubleToLongBits(other.discountedPrice)
                && Objects.equals(id, other.id) && Objects.equals(isbn, other.isbn)
                && numReviews == other.numReviews && Double.doubleToLongBits(price) == Double.doubleToLongBits(other.price)
                && Objects.equals(publicationDate, other.publicationDate)
                && Objects.equals(publisher, other.publisher) && quantity == other.quantity
                && Objects.equals(reviews, other.reviews) && Objects.equals(title, other.title)
                && Objects.equals(coverImageUrl, other.coverImageUrl);
    }
}
