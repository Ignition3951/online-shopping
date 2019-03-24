package com.utkarsh.shoppingbackend.test;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.utkarsh.shoppingbackend.dao.ProductDAO;
import com.utkarsh.shoppingbackend.dto.Product;

public class ProductTestCase {
	private static AnnotationConfigApplicationContext context;
	private static ProductDAO productDAO;
	private Product product;
	
	@BeforeClass
	public static void init() {
		context=new AnnotationConfigApplicationContext();
		context.scan("com.utkarsh.shoppingbackend");
		context.refresh();
		productDAO =   (ProductDAO)context.getBean("productDAO");
	}
	
	/*
	 * @Test public void testCRUDProduct() { product= new Product();
	 * product.setName("Oppo Selfie A32"); product.setBrand("OPPO");
	 * product.setDescription("This is the phone of OPPO");
	 * product.setUnitPrice(18000); product.setActive(true);
	 * product.setCategoryId(3); product.setSupplierId(3);
	 * 
	 * assertEquals("Something went wrong",true,productDAO.add(product));
	 * product=productDAO.get(3); product.setName("Samsung galaxy A5");
	 * assertEquals("Something went wrong",true,productDAO.update(product));
	 * assertEquals("Something went wrong",true,productDAO.delete(product));
	 * assertEquals("Something went wrong",6,productDAO.list().size()); }
	 */
	@Test
	public void testListActiveProducts() {
		assertEquals("Something went wrong",5,productDAO.listActiveProducts().size());
	}
	
	@Test
	public void testListActiveProductsByCategory() {
		assertEquals("Something went wrong",3,productDAO.listActiveProductsByCategory(3).size());
		assertEquals("Something went wrong",2,productDAO.listActiveProductsByCategory(1).size());
	}
	
	@Test
	public void testGetLatestActiveProducts() {
		assertEquals("Something went wrong",3,productDAO.getLatestActiveProducts(3).size());
	}

}
