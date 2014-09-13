package com.uniksoft.utilities;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;

import com.uniksoft.form.Author;
import com.uniksoft.form.Book;
import com.uniksoft.service.EntityServiceImpl;

public class Seed {

	/*
	 * This will be executed after Seed is instantiated
	 */
	@SuppressWarnings("rawtypes")
	@Autowired
	private EntityServiceImpl entityService;
	
	/*
	 * This will be executed after entityService is initialized by Spring
	 */
	@PostConstruct
	public void init() {
		System.out.println("*** seeding the db");
		populateAuthorBook();
	}
	
	@SuppressWarnings("unchecked")
	private void populateAuthorBook() {
		System.out.println("*** Populating authors and books tables");
		Author auth1 = new Author("Pierre Lasante");
		Author auth2 = new Author("Karo Spenard");
		Author auth3 = new Author("Juby Spenard");
		Author auth4 = new Author("Ginger Lasante");
		
		Book book1 = new Book("JoR",1,2);
		Book book2 = new Book("RoR",3,4);
		Book book3 = new Book("PoR",5,6);
		
		book1.setAuthor(auth1);
		book2.setAuthor(auth1);
		book3.setAuthor(auth2);
		
		List<Book> books1 = new ArrayList<Book>();
		List<Book> books2 = new ArrayList<Book>();
		
		books1.add(book1);
		books1.add(book2);
		
		auth1.setBooks(books1);
		
		books2.add(book3);
		
		auth2.setBooks(books2);
		
		entityService.addEntity(auth1);
		entityService.addEntity(auth2);
		entityService.addEntity(auth3);
		entityService.addEntity(auth4);
		entityService.addEntity(book1);
		entityService.addEntity(book2);
		entityService.addEntity(book3);
	}
}
