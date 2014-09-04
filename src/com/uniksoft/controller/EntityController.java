package com.uniksoft.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.uniksoft.form.Author;
import com.uniksoft.form.Book;
import com.uniksoft.service.EntityServiceImpl;

@Controller
public class EntityController {

	@SuppressWarnings("rawtypes")
	@Autowired
	private EntityServiceImpl entityService;
	
	@SuppressWarnings("unchecked")
	@RequestMapping("/author")
	public String listEntities(Map<String, Object> map, HttpServletRequest request) {
		map.put( "author" , new Author());
		map.put( "authorList", entityService.listEntities(Author.class));
		return "author";
	}
	
	@RequestMapping(
		value = "/author/add",
		method = RequestMethod.POST
	)
	public String addAuthor(@ModelAttribute("author") Author author) {
		if (null == author.getId()) {
			entityService.addEntity(author);
		} else {
			entityService.updateEntity(author);
		}
		return "redirect:/author";
	}
	
	@RequestMapping("/author/edit/{authorId}")
	public String editAuthor(@PathVariable("authorId") Integer authorId, Map<String, Object> map) {
		map.put("author", entityService.getEntityById(Author.class, authorId));
		map.put("authorList", entityService.listEntities(Author.class));
		return "author";
	}
}
