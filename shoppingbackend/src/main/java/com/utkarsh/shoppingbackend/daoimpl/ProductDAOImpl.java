package com.utkarsh.shoppingbackend.daoimpl;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.utkarsh.shoppingbackend.dao.ProductDAO;
import com.utkarsh.shoppingbackend.dto.Product;

@Repository("productDAO")
@Transactional
public class ProductDAOImpl implements ProductDAO {
	@Autowired
	private SessionFactory sessionFactory;
	public Product get(int productId) {
		
		try{
			return sessionFactory		
				.getCurrentSession()
					.get(Product.class, Integer.valueOf(productId));
		}
		catch(Exception ex) {
			ex.printStackTrace();
		}
		return null;
	}

	public List<Product> list() {
		return sessionFactory
				.getCurrentSession()
					.createQuery("FROM Product",Product.class)
						.getResultList();
	}

	public boolean add(Product product) {
		try {
			sessionFactory
				.getCurrentSession()
						.persist(product);
			return true;
		}
		catch(Exception ex) {
			ex.printStackTrace();
			return false;
		}
		
	}

	public boolean delete(Product product) {
		try {
			product.setActive(false);
			return this.update(product);
		}
		catch(Exception ex) {
			ex.printStackTrace();
			return false;
		}
		
	}

	public boolean update(Product product) {
		try {
			sessionFactory
			.getCurrentSession()
				.update(product);
			return true;
		}
		catch(Exception ex) {
			ex.printStackTrace();
			return false;
		}
		
	}

	public List<Product> listActiveProducts() {
		String selectActiveProducts="FROM Product WHERE isActive = :active";
		return sessionFactory
				.getCurrentSession()
					.createQuery(selectActiveProducts,Product.class)
						.setParameter("active",true)
							.getResultList();
	}

	public List<Product> listActiveProductsByCategory(int categoryId) {
		String selectActiveProductsByCategory="FROM Product WHERE isActive = :active AND categoryId = :categoryId";
		return sessionFactory
				.getCurrentSession()
					.createQuery(selectActiveProductsByCategory,Product.class)
						.setParameter("active",true)
						.setParameter("categoryId", categoryId)
							.getResultList();
		
	}

	public List<Product> getLatestActiveProducts(int count) {
		return sessionFactory
				.getCurrentSession()
					.createQuery("FROM Product WHERE isActive = :active ORDER BY id",Product.class)
						.setParameter("active",true)
							.setFirstResult(0)
							.setMaxResults(count)
								.getResultList();
	}

}
