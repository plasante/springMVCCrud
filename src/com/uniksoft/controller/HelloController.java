package com.uniksoft.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HelloController {

	@RequestMapping("/welcome")
	public ModelAndView helloworld() {
		String message = "<br><div align='center'>" + "<h1>Hello World, Spring 3.2.1 MVC Hibernate example <h1> <br>";
		return new ModelAndView("welcome", "message", message);
	}
}
