package com.uniksoft.form;

import static javax.persistence.GenerationType.IDENTITY;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "AUTHORS")
public class Author {

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "ID", unique = true, nullable = false)
	private Integer id;
	
	@Column(name="AUTHOR_NAME", unique = true, nullable = false)
	private String authorName;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "author")
	private List<Book> books;
	
	
	/*
	 * This default constructor is needed for EntityController.java
	 */
	public Author() {}
	
	/*
	 * This constructor is needed for Seed.java
	 */
	public Author(String authorName) {
		this.authorName = authorName;
	}

	public List<Book> getBooks() {
		return books;
	}

	public void setBooks(List<Book> books) {
		this.books = books;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getAuthorName() {
		return authorName;
	}

	public void setAuthorName(String authorName) {
		this.authorName = authorName;
	}

}
