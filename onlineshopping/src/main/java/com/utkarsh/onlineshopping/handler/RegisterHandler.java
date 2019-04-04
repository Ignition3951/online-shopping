package com.utkarsh.onlineshopping.handler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.binding.message.MessageBuilder;
import org.springframework.binding.message.MessageContext;
import org.springframework.stereotype.Component;

import com.utkarsh.onlineshopping.model.RegisterModel;
import com.utkarsh.shoppingbackend.dao.UserDAO;
import com.utkarsh.shoppingbackend.dto.Address;
import com.utkarsh.shoppingbackend.dto.Cart;
import com.utkarsh.shoppingbackend.dto.User;

@Component
public class RegisterHandler {
	
	@Autowired
	private UserDAO userDAO;
	
	public RegisterModel init() {
		
		return new RegisterModel();
	}
	
	public void addUser(RegisterModel registerModel,User user) {
		registerModel.setUser(user);
	}
	
	public void addBilling(RegisterModel registerModel,Address billing) {
		registerModel.setBilling(billing);
		
	}
	
	public String validateUser(User user,MessageContext errors) {
		String transitionValue="success";
//		Checking if password matches
		if(!(user.getPassword().equals(user.getConfirmPassword()))) {
			errors.addMessage(new MessageBuilder()
					.error()
					.source("confirmPassword")
					.defaultText("The password do not match")
					.build());
			transitionValue="failure";
		}
//		Checking the uniqueness of email id
		if(userDAO.getByEmail(user.getEmail())!=null) {
			
			errors.addMessage(new MessageBuilder()
					.error()
					.source("email")
					.defaultText("The email id already exists")
					.build());
			transitionValue="failure";
		}
		
		return transitionValue;
	}
	
	public String saveAll(RegisterModel model) {
		String transitionValue="success";
//		Fetch the User
		User user=model.getUser();
		if(user.getRole().equals("USER")) {
			Cart cart=new Cart();
			cart.setUser(user);
			user.setCart(cart);
		}
//		Add the user
		userDAO.addUser(user);
		Address billing=model.getBilling();
		billing.setUser(user);
		billing.setBilling(true);
		userDAO.addAddress(billing);
		return transitionValue;
	}

}
