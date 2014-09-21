package com.uniksoft.utilities;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;

import com.uniksoft.form.Author;
import com.uniksoft.form.Book;
import com.uniksoft.form.Privilege;
import com.uniksoft.form.Role;
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
		populateRolePrivilege();
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
	
	@SuppressWarnings({ "unchecked" })
	public void populateRolePrivilege() {
		System.out.println("*** Populating roles and privileges tables");
		Role role1 = new Role("admin");
		Role role2 = new Role("user");
		
		Privilege priv1 = new Privilege("manage_author");
		Privilege priv2 = new Privilege("manage_book");
		Privilege priv3 = new Privilege("view_book");
		Privilege priv4 = new Privilege("del_book");
		Privilege priv5 = new Privilege("mod_book");
		Privilege priv6 = new Privilege("add_book");
		
		/*
		 * privileges 1 and 2 are assigned to role1
		 */
		Set<Privilege> privileges1 = new HashSet<Privilege>();
		privileges1.add(priv1);
		privileges1.add(priv2);
		role1.setPrivileges(privileges1);
		
		/*
		 * privileges 3 is assigned to role 2
		 */
		Set<Privilege> privileges2 = new HashSet<Privilege>();
		privileges2.add(priv3);
		role2.setPrivileges(privileges2);
		
		/*
		 * role1 and role2 are saved creating priv1 priv2 and priv3
		 */
		entityService.addEntity(role1);
		entityService.addEntity(role2);

		/*
		 * priv4 priv5 and priv6 are created
		 */
		entityService.addEntity(priv4);
		entityService.addEntity(priv5);
		entityService.addEntity(priv6);
	}
}
