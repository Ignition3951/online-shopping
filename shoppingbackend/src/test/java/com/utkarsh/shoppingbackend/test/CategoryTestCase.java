package com.utkarsh.shoppingbackend.test;

import static org.junit.Assert.assertEquals;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.utkarsh.shoppingbackend.dao.CategoryDAO;
import com.utkarsh.shoppingbackend.dto.Category;

public class CategoryTestCase {
	private static AnnotationConfigApplicationContext context;
	
	private static CategoryDAO categoryDAO;
	
	private Category category;
	
	@BeforeClass
	public static void  init()
	{
		context = new AnnotationConfigApplicationContext();
		context.scan("com.utkarsh.shoppingbackend");
		context.refresh();
		categoryDAO=(CategoryDAO)context.getBean("categoryDAO");
	}
	
	/*
	 * @Test public void testAddCategory() { category=new Category();
	 * category.setName("Mobile");
	 * category.setDescription("This is the description of a mobile");
	 * category.setImageUrl("CAT_103.png");
	 * assertEquals("Message Successfully Added", true,categoryDAO.add(category)); }
	 */
	
	/*
	 * @Test public void testGetCategory() { category=categoryDAO.get(3);
	 * assertEquals("Successfully got the category","Laptop",category.getName()); }
	 */
	
	/*
	 * @Test public void testUpdateCategory() { category=categoryDAO.get(2);
	 * category.setName("TV");
	 * assertEquals("Successfully changed the name of category",true,categoryDAO.
	 * update(category)); }
	 */
	/*
	 * @Test public void testDeleteCategory() { category=categoryDAO.get(2);
	 * assertEquals("Successfully deleted the category",true,categoryDAO.delete(
	 * category)); }
	 */
	
	/*
	 * @Test public void testListCategory() {
	 * assertEquals("Successfully fetched the list of categories",3,categoryDAO.list
	 * ().size()); }
	 */
	
	@Test
	public void testCRUDCategory() {
		category= new Category();
		category.setName("Refrigerator");
		category.setDescription("This is the description of refrigerator");
		category.setImageUrl("CAT_107.png");
		assertEquals("Successfully changed the name of category",true,categoryDAO.add(category));
		category=categoryDAO.get(2);
		category.setName("TV");
		assertEquals("Successfully updated the name of category",true,categoryDAO.update(category));
		assertEquals("Successfully deleted the name of category",true,categoryDAO.delete(category));
		assertEquals("Successfully got the list categories",3,categoryDAO.list().size());
	}
}
