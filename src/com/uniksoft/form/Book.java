package com.uniksoft.form;

import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "BOOKS")
public class Book {
	
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "ID", unique = true, nullable = false)
	private Integer id;
	
	@Column(name="BOOK_NAME", unique = true, nullable = false)
	private String bookName;
	
	@Column(name="PRICE")
    private int price;
	
	@Column(name="QTY")
    private int quantity;
	
	/*
	 * The column author_id will be created in the table books
	 * The class Author maps to the table authors
	 */
	@ManyToOne(cascade=CascadeType.ALL)
	@JoinColumn(name = "author_id", nullable = true)
	private Author author;
	
	public Book() {}
	
	public Book(String bookName, int price, int quantity) {
		this.bookName = bookName;
		this.price = price;
		this.quantity = quantity;
	}

	public Author getAuthor() {
		return author;
	}

	public void setAuthor(Author author) {
		this.author = author;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getBookName() {
		return bookName;
	}

	public void setBookName(String bookName) {
		this.bookName = bookName;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
}