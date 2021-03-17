package com.company.productcatalogue.service;

import java.util.List;
import java.util.Map;

import com.company.productcatalogue.model.ProductModel;

public interface ProductService {

	List<ProductModel> getAllProducts();

	ProductModel getProductById(long id);

	long createProduct(ProductModel product);

	void updateProduct(ProductModel product);

	void deleteProduct(long id);

	List<ProductModel> getProductsByFilter(Map<String,String> filters);

	List<ProductModel> getProductByName(String name);
}
