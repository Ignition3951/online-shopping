package com.utkarsh.shoppingbackend.dao;

import java.util.List;

import com.utkarsh.shoppingbackend.dto.Address;
import com.utkarsh.shoppingbackend.dto.Cart;
import com.utkarsh.shoppingbackend.dto.User;

public interface UserDAO {
//	Adding user
	boolean addUser(User user);
	public User getByEmail(String emailId);
//	Adding Address
	boolean addAddress(Address address);
	public Address getBillingAddress(User user);
	public List<Address> listShippingAddresses(User user);
//	Adding Cart
	boolean updateCart(Cart cart);

}
