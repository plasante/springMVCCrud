package com.uniksoft.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionFailedException;
import org.springframework.core.convert.TypeDescriptor;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.uniksoft.form.Author;
import com.uniksoft.service.EntityServiceImpl;

@Component
public class StringToAuthorConverter implements Converter<String, Author>{

	@SuppressWarnings("rawtypes")
	@Autowired
	private EntityServiceImpl entityService;
	
	@Override
	public Author convert(String authorIdStr) {
		int authorId = -1;
		try {
			authorId = Integer.parseInt(authorIdStr);
		} catch (NumberFormatException e) {
			throw new ConversionFailedException(TypeDescriptor.valueOf(String.class), 
					TypeDescriptor.valueOf(Author.class), authorIdStr, null);
		}
		
		@SuppressWarnings("unchecked")
		Author author = (Author) entityService.getEntityById(Author.class, authorId);
		return author;
	}

}
