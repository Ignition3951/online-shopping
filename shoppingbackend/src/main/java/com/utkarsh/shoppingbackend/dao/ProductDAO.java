package com.utkarsh.shoppingbackend.dao;

import java.util.List;

import com.utkarsh.shoppingbackend.dto.Product;

public interface ProductDAO {
	
//	CRUD OPeration methods
	Product get(int productId);
	List<Product> list();
	boolean add(Product product);
	boolean delete(Product product);
	boolean update(Product product);
	
//	Business Methods
	List<Product> listActiveProducts();
	List<Product> listActiveProductsByCategory(int categoryId);
	List<Product> getLatestActiveProducts(int count);
}
