package com.utkarsh.onlineshopping.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.utkarsh.onlineshopping.util.FileUploadUtility;
import com.utkarsh.onlineshopping.validator.ProductValidator;
import com.utkarsh.shoppingbackend.dao.CategoryDAO;
import com.utkarsh.shoppingbackend.dao.ProductDAO;
import com.utkarsh.shoppingbackend.dto.Category;
import com.utkarsh.shoppingbackend.dto.Product;

@Controller
@RequestMapping(value="/manage")
public class ManagementController {
	
	@Autowired
	private CategoryDAO categoryDAO;
	
	@Autowired
	private ProductDAO productDAO;
	
	private static final Logger logger=LoggerFactory.getLogger(ManagementController.class); 

	@RequestMapping(value="/products",method=RequestMethod.GET)
	public ModelAndView showManageProduct(@RequestParam(name="operation",required=false) String operation) {
		ModelAndView mv= new ModelAndView("page");
		mv.addObject("userClickManageProducts",true);
		mv.addObject("title","Manage Products");
		Product nProduct= new Product();
		
		nProduct.setSupplierId(3);
		nProduct.setActive(true);
		
		mv.addObject("product",nProduct);
		if(operation!=null) {
			if(operation.equals("product")) {
				mv.addObject("message","Product Submitted Successfully!");
			}
			else if(operation.equals("category")) {
				mv.addObject("message","Category Submitted Successfully!");
			}
		}
		
		return mv;
	}
	
	@RequestMapping(value="/{id}/products",method=RequestMethod.GET)
	public ModelAndView showEditProduct(@PathVariable int id) {
		ModelAndView mv= new ModelAndView("page");
		mv.addObject("userClickManageProducts",true);
		mv.addObject("title","Manage Products");
		Product nProduct= productDAO.get(id);
		
		
		mv.addObject("product",nProduct);
		
		
		return mv;
	}
	
	
	/*	HANDLING THE PRODUCT SUBMISSION
		*/
	@RequestMapping(value="/products",method=RequestMethod.POST)
	public String handleProductSubmission(@Valid @ModelAttribute("product") Product mProduct,BindingResult results,Model model,
			HttpServletRequest request) {
		if(mProduct.getId()==0) {
		new ProductValidator().validate(mProduct, results);
		}
		else {
			if(!mProduct.getFile().getOriginalFilename().equals("")) {
				new ProductValidator().validate(mProduct, results);
			}
		}
		if(results.hasErrors()) {
			model.addAttribute("userClickManageProducts",true);
			model.addAttribute("title","Manage Products");
			model.addAttribute("message","Validation for Product Submission has failed !");
			return "page";
			
		}
		logger.info(mProduct.toString());

		if(mProduct.getId()==0) {
//			Creating a new product
			productDAO.add(mProduct);
		}
		else {
//			Else update the existing product
			productDAO.update(mProduct);
		}
		if(!mProduct.getFile().getOriginalFilename().equals("")) {
			FileUploadUtility.uploadFile(request,mProduct.getFile(),mProduct.getCode());
			
		}
		return "redirect:/manage/products?operation=product"; 
	}
	
	@RequestMapping(value="/product/{id}/activation")
	@ResponseBody
	public String handleProductActivation(@PathVariable int id) {
//		Fetching the product
		Product product=productDAO.get(id);
		boolean isActive=product.isActive();
//		Product Activation and Deactivation
		product.setActive(!isActive);
		productDAO.update(product);
		
		return (isActive)? 
				"You have successfully deactivated the product with id "+product.getId():
					"You have successfully activated the product with id "+product.getId();			
	}
	
	@RequestMapping(value="/category", method=RequestMethod.POST)
	public String handleCategorySubmission(@ModelAttribute Category category) {
//		add new Category
		categoryDAO.add(category);
		return "redirect:/manage/products?operation=category";
	}
	
	@ModelAttribute("categories")
	public List<Category> getCategories(){
		return categoryDAO.list();
	}
	
	@ModelAttribute(value="category")
	public Category getCategory() {
		return new Category();
	}
}
