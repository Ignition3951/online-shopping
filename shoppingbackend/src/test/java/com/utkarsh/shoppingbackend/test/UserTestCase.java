package com.utkarsh.shoppingbackend.test;

import static org.junit.Assert.assertEquals;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.utkarsh.shoppingbackend.dao.UserDAO;
import com.utkarsh.shoppingbackend.dto.Address;
import com.utkarsh.shoppingbackend.dto.Cart;
import com.utkarsh.shoppingbackend.dto.User;

public class UserTestCase {
	
	private static AnnotationConfigApplicationContext context;
	private static UserDAO userDAO;
	private Address address=null;
	private User user=null;
	private Cart cart=null;
	
	@BeforeClass
	public static void init() {
		context=new AnnotationConfigApplicationContext();
		context.scan("com.utkarsh.shoppingbackend");
		context.refresh();
		userDAO=(UserDAO)context.getBean("userDAO");
	}
	
	/*
	 * @Test public void testAdd() {
	 * 
	 * user=new User(); user.setFirstName("Abd"); user.setLastName("Villiers");
	 * user.setContactNumber("12344567"); user.setEmail("ab@gmail.com");
	 * user.setPassword("12345"); user.setRole("USER");
	 * 
	 * // Add the user
	 * assertEquals("Failed to add the user",true,userDAO.addUser(user));
	 * 
	 * address=new Address(); address.setAddressLineOne("5/139 Vikaskhand");
	 * address.setAddressLineTwo("Gomtinagar lucknow"); address.setCity("Lucknow");
	 * address.setCountry("India"); address.setState("UP");
	 * address.setPostalCode("226010"); address.setBilling(true);
	 * 
	 * // Link the address to the user using user id
	 * 
	 * address.setUserId(user.getId()); // Add the Address
	 * assertEquals("Failed to add the address ", true,userDAO.addAddress(address));
	 * 
	 * if(user.getRole().equals("USER")) { // Add a new cart cart=new Cart();
	 * cart.setUser(user); // Add the cart assertEquals("Failed to add the cart",
	 * true,userDAO.addCart(cart));
	 * 
	 * // Add a shipping address for this user address=new Address();
	 * address.setAddressLineOne("2/82 A");
	 * address.setAddressLineTwo("Gomtinagar lucknow"); address.setCity("Lucknow");
	 * address.setCountry("India"); address.setState("UP");
	 * address.setPostalCode("226010"); // Adding shipping address not billing
	 * address.setShipping(true); // link it with the user
	 * address.setUserId(user.getId()); // add the address
	 * assertEquals("Failed to add the shippinh address", true,
	 * userDAO.addAddress(address)); }
	 * 
	 * 
	 * }
	 */
	/*
	 * @Test public void testAdd() {
	 * 
	 * user=new User(); user.setFirstName("Abd"); user.setLastName("Villiers");
	 * user.setContactNumber("12344567"); user.setEmail("ab@gmail.com");
	 * user.setPassword("12345"); user.setRole("USER");
	 * 
	 * if(user.getRole().equals("USER")) { // Add a new cart cart=new Cart();
	 * cart.setUser(user); user.setCart(cart);
	 * 
	 * } // Add the user
	 * assertEquals("Failed to add the user",true,userDAO.addUser(user));
	 * 
	 * 
	 * }
	 */
	/*
	 * @Test public void testAddAddress() { // Add the user user=new User();
	 * user.setFirstName("Abd"); user.setLastName("Villiers");
	 * user.setContactNumber("12344567"); user.setEmail("ab@gmail.com");
	 * user.setPassword("12345"); user.setRole("USER");
	 * 
	 * // Add the user
	 * assertEquals("Failed to add the user",true,userDAO.addUser(user));
	 * 
	 * // add the billing address address=new Address();
	 * address.setAddressLineOne("5/139 Vikaskhand");
	 * address.setAddressLineTwo("Gomtinagar lucknow"); address.setCity("Lucknow");
	 * address.setCountry("India"); address.setState("UP");
	 * address.setPostalCode("226010"); address.setBilling(true);
	 * 
	 * address.setUser(user); assertEquals("Failed to add the address ",
	 * true,userDAO.addAddress(address)); // Add the shipping Address address=new
	 * Address(); address.setAddressLineOne("2/82 A");
	 * address.setAddressLineTwo("Gomtinagar lucknow"); address.setCity("Lucknow");
	 * address.setCountry("India"); address.setState("UP");
	 * address.setPostalCode("226010"); // Adding shipping address not billing
	 * address.setShipping(true); address.setUser(user);
	 * assertEquals("Failed to add the shipping address ",
	 * true,userDAO.addAddress(address)); }
	 */
//	@Test
//	public void testAddAddress() {
//		user =userDAO.getByEmail("ab@gmail.com");
//		address=new Address();
//		address.setAddressLineOne("2/345 Nirala nagar");
//		address.setAddressLineTwo("Aliganj lucknow"); 
//		address.setCity("Lucknow");
//		address.setCountry("India"); 
//		address.setState("UP");
//		address.setPostalCode("226010"); 
//		address.setShipping(true);
//		address.setUser(user);
//		assertEquals("Failed to add the shipping address ", true,userDAO.addAddress(address));
//	}
	
	@Test
	public void testGetAddresses() {
		
		user=userDAO.getByEmail("ab@gmail.com");
		assertEquals("Failed to get the list of addresses and size does not match",
				2,userDAO.listShippingAddresses(user).size());
		assertEquals("Failed to get the city of billing address", "Lucknow",
				userDAO.getBillingAddress(user).getCity());
		
	}

}
