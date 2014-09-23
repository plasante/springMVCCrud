package com.uniksoft.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
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
	
	@Autowired
	private ConversionService conversionService;
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/roles", method = RequestMethod.GET)
	public String listEntities(Map<String, Object> map) {
		map.put("role", new Role());
		map.put("roleList", entityService.listEntities(Role.class));
		map.put("privilegeMap", getPrivilegesMap());
		return "role";
	}
	
	@RequestMapping(value = "/roles/new", method = RequestMethod.GET)
	public String newEntity(Map<String, Object> map) {
		return "redirect:/roles";	// This will redirect to the listEntities method then render the role.jsp
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/roles/delete/{roleID}", method = RequestMethod.GET)
	public String deleteEntity(@PathVariable Integer roleID, Map<String, Object> map) {
		if ( roleID != null) {
			entityService.removeEntity(Role.class, roleID);
		}
		return "redirect:/roles";		// This will render the role.jsp
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/roles/edit/{roleID}", method = RequestMethod.GET)
	public String editEntity(@PathVariable Integer roleID, Map<String, Object> map) {
		map.put("role", entityService.getEntityById(Role.class, roleID));
		map.put("roleList", entityService.listEntities(Role.class));
		map.put("privilegeMap", getPrivilegesMap());
		return "role";
	}
	
	/*
	 * The form will be data-binded, converted and validated before to be submitted
	 * to the entityService for create or update.
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/roles" , method = RequestMethod.POST)
	public String create(@ModelAttribute("role") @Valid Role role,
            BindingResult result, Map<String, Object> map) {
		if (result.hasErrors()) {
			map.put("role", role);
			map.put("roleList", entityService.listEntities(Role.class));
			map.put("privilegeMap", getPrivilegesMap());
			return "role";
		} else {
			if (role.getId() == null) {
				entityService.addEntity(role);
			} else 
				entityService.updateEntity(role);
			return "redirect:/roles";
		}
	}

	/*
	 * This is to populate the privileges drop-down in the role.jsp view
	 */
	@SuppressWarnings("unchecked")
	private Map<String, String> getPrivilegesMap() {
		Map<String, String> privilegeMap = new HashMap<String, String>();
		List<Privilege> privileges = entityService.listEntities(Privilege.class);
		for (Privilege privilege : privileges) {
			privilegeMap.put(Integer.toString(privilege.getId()), privilege.getPrivilegeName());
		}
		return privilegeMap;
	}
}
