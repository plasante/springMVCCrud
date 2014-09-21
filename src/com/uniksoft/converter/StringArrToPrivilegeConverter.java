package com.uniksoft.converter;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;

import com.uniksoft.form.Privilege;
import com.uniksoft.service.EntityServiceImpl;

public class StringArrToPrivilegeConverter implements Converter<String[], Set<Privilege> > {

	@SuppressWarnings("rawtypes")
	@Autowired
	private EntityServiceImpl entityService;

	@SuppressWarnings("unchecked")
	@Override
	public Set<Privilege> convert(String[] privilegeID) {
		Set<Privilege> privileges = new HashSet<Privilege>();
		for (String privID : privilegeID) {
			Privilege privilege = (Privilege) entityService.getEntityById(Privilege.class, Integer.parseInt(privID));
			privileges.add(privilege);
		}
		return privileges;
	}
}
