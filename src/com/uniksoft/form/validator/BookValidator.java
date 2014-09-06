package com.uniksoft.form.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.uniksoft.form.Book;

public class BookValidator implements Validator {

	@Override
	public boolean supports(Class<?> paramClass) {
		return Book.class.equals(paramClass);
	}

	@Override
	public void validate(Object obj, Errors errors) {	
//		Book book = (Book) obj;
//		if (book.getId() <= 0) {
//			errors.rejectValue("id", "negativeValue", new Object[]{"'id'"}, "id can't be negative");
//		}
		
//		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "bookName", "bookName.required");
//		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "author", "author.required");
//		if (book.getPrice() < 1) 
//			errors.rejectValue("price", "price.negative", new Object[]{"'price'"}, "Price can't be zero");
//		if (book.getQuantity() < 1)
//			errors.rejectValue("quantity", "quantity.negative", new Object[]{"'quantity'"}, "Quantity can't be zero");
	}

}
