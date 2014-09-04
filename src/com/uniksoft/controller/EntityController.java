package com.uniksoft.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.uniksoft.form.Author;
import com.uniksoft.service.EntityServiceImpl;

@Controller
public class EntityController {

	@SuppressWarnings("rawtypes")
	@Autowired
	private EntityServiceImpl entityService;
	
	@SuppressWarnings("unchecked")
	@RequestMapping("/author")
	public String listEntities(Map<String, Object> map) {
		map.put("author", new Author());
		map.put("authorList", entityService.listEntities(Author.class));
		System.out.println("*** number of authors = " + map.get("authorList"));
		return "author";
	}
}
