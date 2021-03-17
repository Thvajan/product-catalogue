package com.company.productcatalogue.controller;

import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.company.productcatalogue.model.ProductModel;
import com.company.productcatalogue.service.ProductService;

@RestController
@RequestMapping("/product-catalogue/products")
public class ProductController {

	ProductService productService;

	public ProductController(ProductService productService) {
		this.productService = productService;
	}

	@GetMapping("/all")
	public List<ProductModel> getAllProducts() {
		return productService.getAllProducts();
	}

	@GetMapping("/")
	public ResponseEntity<ProductModel> getProductById(@RequestParam("id") long id) {
		ProductModel product = productService.getProductById(id);
		if (product != null)
			return ResponseEntity.ok(product);
		return ResponseEntity.notFound().build();
	}
	
	@GetMapping("/filter")
	public ResponseEntity<List<ProductModel>> getProductByFilter(@RequestParam Map<String, String> filterMap) {
		System.out.println(filterMap);
		List<ProductModel> products = productService.getProductsByFilter(filterMap);
		if (products != null && !products.isEmpty())
			return ResponseEntity.ok(products);
		return ResponseEntity.noContent().build();
	}

}
