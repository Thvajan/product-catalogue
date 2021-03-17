package com.company.productcatalogue.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.company.productcatalogue.entity.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {

	@Query("SELECT new com.company.productcatalogue.entity.Product(p.id, p.name, p.quantity, p.size, p.price, p.brand.name, p.color,"
			+ "p.productCategory.name) from Product p "
			+ "INNER JOIN p.brand b "
			+ "INNER JOIN p.productCategory pc")
	List<Product> findAllProducts();
	
	List<Product> findByName(String name);
}
