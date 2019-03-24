package com.utkarsh.onlineshopping.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.utkarsh.onlineshopping.exception.ProductNotFoundException;
import com.utkarsh.shoppingbackend.dao.CategoryDAO;
import com.utkarsh.shoppingbackend.dao.ProductDAO;
import com.utkarsh.shoppingbackend.dto.Category;
import com.utkarsh.shoppingbackend.dto.Product;

@Controller
public class PageController {
	
	private static final Logger logger=LoggerFactory.getLogger(PageController.class);
	
	@Autowired
	private CategoryDAO categoryDAO;
	
	@Autowired
	private ProductDAO productDAO;
	
	@RequestMapping(value= {"/","/home","/index"})
	public ModelAndView index() {
		logger.info("Inside the Page Controller index - INFO");
		logger.debug("Inside the Page Controller index - DEBUG");
		ModelAndView mv = new ModelAndView("page");
		mv.addObject("title","Home");
		mv.addObject("categories",categoryDAO.list());
		mv.addObject("userClickHome",true);
		return mv;
	}
	
	@RequestMapping(value= "/about")
	public ModelAndView about() {
		ModelAndView mv = new ModelAndView("page");
		mv.addObject("title","About");
		mv.addObject("userClickAbout",true);
		return mv;
	}
	
	@RequestMapping(value= "/contact")
	public ModelAndView contactUs() {
		ModelAndView mv = new ModelAndView("page");
		mv.addObject("title","Contact Us");
		mv.addObject("userClickContactUs",true);
		return mv;
	}
	
//   Methods To Add All the Products based on category
	@RequestMapping(value= "/show/all/products")
	public ModelAndView showAllProducts() {
		ModelAndView mv = new ModelAndView("page");
		mv.addObject("title","All Products");
		mv.addObject("categories",categoryDAO.list());
		mv.addObject("userClickAllProducts",true);
		return mv;
	}
	
	@RequestMapping(value= "/show/category/{id}/products")
	public ModelAndView showCategoryProducts(@PathVariable("id") int id) {
		
//		categoryDao to fetch single category
		Category category=null;
		category=categoryDAO.get(id);
		ModelAndView mv = new ModelAndView("page");
		mv.addObject("title",category.getName());
//		passing all categories
		mv.addObject("categories",categoryDAO.list());
//		passing single category
		mv.addObject("category",category);
		mv.addObject("userClickCategoryProducts",true);
		return mv;
	}
	
//	To Show the single product
	@RequestMapping(value="/show/{id}/product")
	public ModelAndView showSingleProduct(@PathVariable int id) throws ProductNotFoundException {
		ModelAndView mv =new ModelAndView("page");
		Product product = productDAO.get(id);
		
		if(product==null) throw new ProductNotFoundException();
		
		//updating the views
		product.setViews(product.getViews()+1);
		productDAO.update(product);
		
		mv.addObject("title",product.getName());
		mv.addObject("product",product);
		mv.addObject("userClickShowProduct",true);
		return mv;
	}
	
	
//	@RequestMapping(value= {"/test"})
//	public ModelAndView test(@RequestParam(value="greeting",required=false) String greeting) {
//		if(greeting==null)
//		{
//			greeting="Default Greeting";
//		}
//		ModelAndView mv = new ModelAndView("page");
//		mv.addObject("greeting",greeting);
//		return mv;
//	}
	
	/*
	 * @RequestMapping(value= {"/test/{greeting}"}) public ModelAndView
	 * test(@PathVariable("greeting") String greeting) { if(greeting==null) {
	 * greeting="Default Greeting"; } ModelAndView mv = new ModelAndView("page");
	 * mv.addObject("greeting",greeting); return mv; }
	 */
}
