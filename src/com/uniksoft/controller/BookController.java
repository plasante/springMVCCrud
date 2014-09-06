package com.uniksoft.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.uniksoft.form.Author;
import com.uniksoft.form.Book;
import com.uniksoft.service.BookServiceImpl;
import com.uniksoft.service.EntityServiceImpl;

@Controller
public class BookController {

	@Autowired
	private BookServiceImpl bookService;
	
	@SuppressWarnings("rawtypes")
	@Autowired
	private EntityServiceImpl entityService;
	
	@Autowired
	@Qualifier("bookValidator")
	private Validator validator;
	
	@InitBinder
	private void initBinder(WebDataBinder binder) {
		binder.setValidator(validator);
	}
	
	@Autowired
	private ConversionService conversionService;
	
	@RequestMapping("/book")
	public String listBooks(Map<String, Object> map) {
		Map<String, String> authorsMap = getAuthorsMap();
		map.put("book", new Book());
		map.put("bookList", bookService.listBooks());
		map.put("authorsMap", authorsMap);
		return "book";
	}
	
	@RequestMapping(
		value = "/book/add",
		method = RequestMethod.POST
	)
	public String addBook(@ModelAttribute("book") @Validated Book book, BindingResult result, Map<String, Object> map) {
		
		if (result.hasErrors()) {
			Map<String, String> authorsMap = getAuthorsMap();
			map.put("book", book);
			map.put("bookList", bookService.listBooks());
			map.put("authorsMap", authorsMap);
			return "book";
		} else {
			if (null == book.getId()) {
				bookService.addBook(book);
			} else {
				bookService.updateBook(book);
			}
			return "redirect:/book";
		}
	}
	
	@RequestMapping("/delete/{bookId}")
	public String deleteBook( @PathVariable("bookId") Integer bookId) {
		bookService.removeBook(bookId);
		return "redirect:/book";
	}
	
	@RequestMapping("/edit/{bookId}")
	public String editBook( @PathVariable("bookId") Integer bookId, Map<String, Object> map) {
		Map<String, String> authorsMap = getAuthorsMap();
		map.put("book", bookService.getBookById(bookId));
		map.put("bookList", bookService.listBooks());
		map.put("authorsMap", authorsMap);
		return "book";
	}
	
	private Map<String, String> getAuthorsMap() {
		Map<String, String> authorsMap = new HashMap<String, String>();
		@SuppressWarnings("unchecked")
		List<Author> authors = entityService.listEntities(Author.class);
		for ( Author author : authors) {
			authorsMap.put(Integer.toString(author.getId()), author.getAuthorName());
		}
		return authorsMap;
	}
}
