package com.uniksoft.converter;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionFailedException;
import org.springframework.core.convert.TypeDescriptor;
import org.springframework.core.convert.converter.Converter;

import com.uniksoft.form.Author;
import com.uniksoft.form.Privilege;
import com.uniksoft.service.EntityServiceImpl;

public class StringToPrivilegeConverter implements Converter<String, Set<Privilege> > {

	@SuppressWarnings("rawtypes")
	@Autowired
	private EntityServiceImpl entityService;

	@SuppressWarnings("unchecked")
	@Override
	public Set<Privilege> convert(String privilegeId) {
		int privId = -1;
		try {
			privId = Integer.parseInt(privilegeId);
		} catch (NumberFormatException e) {
			throw new ConversionFailedException(TypeDescriptor.valueOf(String.class), 
					TypeDescriptor.valueOf(Author.class), privilegeId, null);
		}
		Set<Privilege> privilegeLst = new HashSet<Privilege>();
		Privilege privilege = (Privilege) entityService.getEntityById(Privilege.class, privId);
		privilegeLst.add(privilege);
		return privilegeLst;
	}

}
