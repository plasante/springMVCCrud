package com.uniksoft.utilities;

import java.sql.Date;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;

import com.uniksoft.form.Author;
import com.uniksoft.form.Book;
import com.uniksoft.form.Course;
import com.uniksoft.form.Privilege;
import com.uniksoft.form.Role;
import com.uniksoft.form.Student;
import com.uniksoft.form.StudentCourse;
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
		populateStudentCourse();
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
		Role role1 = new Role("superadmin");
		Role role2 = new Role("admin");
		Role role3 = new Role("teacher");
		Role role4 = new Role("student");
		
		
		Privilege priv1 = new Privilege("manage_author");
		Privilege priv2 = new Privilege("manage_book");
		Privilege priv3 = new Privilege("view_book");
		Privilege priv4 = new Privilege("del_book");
		Privilege priv5 = new Privilege("mod_book");
		Privilege priv6 = new Privilege("add_book");
		
		/*
		 * roles are created
		 */
		entityService.addEntity(role1);
		entityService.addEntity(role2);
		entityService.addEntity(role3);
		entityService.addEntity(role4);

		/*
		 * privileges are created
		 */
		entityService.addEntity(priv1);
		entityService.addEntity(priv2);
		entityService.addEntity(priv3);
		entityService.addEntity(priv4);
		entityService.addEntity(priv5);
		entityService.addEntity(priv6);
		
		/*
		 * populating the roles_privileges association table
		 */
		Set<Privilege> privileges = new HashSet<Privilege>();
		privileges.add(priv1);
		privileges.add(priv2);
		role1.setPrivileges(privileges);
		entityService.updateEntity(role1);
	}
	
	@SuppressWarnings("unchecked")
	private void populateStudentCourse() {
		System.out.println("*** Populating students and courses tables");
		
		Student st1 = new Student();
		st1.setStudentName("Pierre Lasante");
		
		Course c1 = new Course();
		c1.setCourseName("c1");
		Course c2 = new Course();
		c2.setCourseName("c2");
		
		StudentCourse sc1 = new StudentCourse();
		sc1.setRegistrationDate(new Date(1));
		sc1.setCourse(c1);
		sc1.setStudent(st1);
		
		StudentCourse sc2 = new StudentCourse();
		sc2.setRegistrationDate(new Date(2));
		sc2.setCourse(c2);
		sc2.setStudent(st1);
		
		entityService.addEntity(st1);
		
		entityService.addEntity(c1);
		entityService.addEntity(c2);
		
		entityService.addEntity(sc1);
		entityService.addEntity(sc2);
		
		entityService.removeEntity(Student.class, 1);
		
		StudentCourse sc3 = new StudentCourse();
		sc3.setCourse(c1);
		List<Student> l = new ArrayList<Student>();
		sc3.setStudent(st1);
		
		List<StudentCourse> scl = new ArrayList<StudentCourse>();
		scl.add(sc3);
		st1.setStudentCourses(scl);
		entityService.addEntity(st1);
	}
}
