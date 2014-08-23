package com.uniksoft.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.uniksoft.form.Book;
import com.uniksoft.service.BookServiceImpl;

@Controller
public class BookController {

	@Autowired
	private BookServiceImpl bookService;
	
	@Autowired
	@Qualifier("bookValidator")
	private Validator validator;
	
	@InitBinder
	private void initBinder(WebDataBinder binder) {
		binder.setValidator(validator);
	}
	
	@RequestMapping("/home")
	public String listBooks(Map<String, Object> map) {
		map.put("book", new Book());
		map.put("bookList", bookService.listBooks());
		return "book";
	}
	
	@RequestMapping(
		value = "/book/add",
		method = RequestMethod.POST
	)
	public String addBook(@ModelAttribute("book") @Validated Book book, BindingResult result, Map<String, Object> map) {
		if (result.hasErrors()) {
			map.put("book", book);
			map.put("bookList", bookService.listBooks());
			return "book";
		} else {
			if (null == book.getId()) {
				bookService.addBook(book);
			} else {
				bookService.updateBook(book);
			}
			return "redirect:/home";
		}
	}
	
	@RequestMapping("/delete/{bookId}")
	public String deleteBook( @PathVariable("bookId") Integer bookId) {
		bookService.removeBook(bookId);
		return "redirect:/home";
	}
	
	@RequestMapping("/edit/{bookId}")
	public String editBook( @PathVariable("bookId") Integer bookId, Map<String, Object> map) {
		map.put("book", bookService.getBookById(bookId));
		map.put("bookList", bookService.listBooks());
		return "book";
	}
}
