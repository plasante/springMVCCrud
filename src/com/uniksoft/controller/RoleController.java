package com.uniksoft.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.uniksoft.form.Role;
import com.uniksoft.service.EntityServiceImpl;

@Controller
public class RoleController {

	@SuppressWarnings("rawtypes")
	@Autowired
	private EntityServiceImpl entityService;
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/roles", method = RequestMethod.GET)
	public String listEntities(Map<String, Object> map) {
		map.put("role", new Role());
		map.put("roleList", entityService.listEntities(Role.class));
		return "role";
	}
}
