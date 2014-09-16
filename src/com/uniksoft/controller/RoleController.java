package com.uniksoft.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.uniksoft.form.Privilege;
import com.uniksoft.form.Role;
import com.uniksoft.service.EntityServiceImpl;

@Controller
public class RoleController {

	@SuppressWarnings("rawtypes")
	@Autowired
	private EntityServiceImpl entityService;
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/roles", method = RequestMethod.GET)
	public ModelAndView listEntities(Map<String, Object> map) {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("role");
		mav.addObject("role", new Role());
		mav.addObject("roleList", entityService.listEntities(Role.class));
		Map<String, String> privilegeMap = new HashMap<String, String>();
		List<Privilege> privileges = entityService.listEntities(Privilege.class);
		for (Privilege privilege : privileges) {
			privilegeMap.put(Integer.toString(privilege.getId()), privilege.getPrivilegeName());
		}
		mav.addObject("privilegeMap", privilegeMap);
		return mav;
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/roles/edit/{roleID}", method = RequestMethod.GET)
	public ModelAndView editEntity(@PathVariable Integer roleID, Map<String, Object> map) {
		ModelAndView mav = new ModelAndView();
		mav.addObject("role", entityService.getEntityById(Role.class, roleID));
		mav.addObject("roleList", entityService.listEntities(Role.class));
		Map<String, String> privilegeMap = new HashMap<String, String>();
		List<Privilege> privileges = entityService.listEntities(Privilege.class);
		for (Privilege privilege : privileges) {
			privilegeMap.put(Integer.toString(privilege.getId()), privilege.getPrivilegeName());
		}
		mav.addObject("privilegeMap", privilegeMap);
		mav.setViewName("role");
		return mav;
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/roles" , method = RequestMethod.POST)
	public String create(@Valid @ModelAttribute("role") Role role,
            BindingResult result, Map<String, Object> map) {
		if (result.hasErrors()) {
			map.put("role", role);
			map.put("roleList", entityService.listEntities(Role.class));
			Map<String, String> privilegeMap = new HashMap<String, String>();
			List<Privilege> privileges = entityService.listEntities(Privilege.class);
			for (Privilege privilege : privileges) {
				privilegeMap.put(Integer.toString(privilege.getId()), privilege.getPrivilegeName());
			}
			map.put("privilegeMap", privilegeMap);
			return "role";
		} else {
			entityService.addEntity(role);
			return "redirect:/roles";
		}
	}
}
