package com.utkarsh.onlineshopping.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.utkarsh.shoppingbackend.dto.Product;

public class ProductValidator implements Validator {

	public boolean supports(Class<?> clazz) {
		// TODO Auto-generated method stub
		return Product.class.equals(clazz);
	}

	public void validate(Object target, Errors errors) {
		Product product=(Product) target;
//		Whether File is selected or not
		if(product.getFile().getOriginalFilename().equals("") || product.getFile()==null) {
			errors.rejectValue("file", null, "Please select a file to be uploaded!");
			return;
		}
		
//		Checking more conditions
		if(!
				(
						product.getFile().getContentType().equals("image/jpeg") ||
						product.getFile().getContentType().equals("image/png") ||
						product.getFile().getContentType().equals("image/gif")
						
				)
				) {
			errors.rejectValue("file", null, "The selected file is not an image file!");
			return;
		}

	}

}
