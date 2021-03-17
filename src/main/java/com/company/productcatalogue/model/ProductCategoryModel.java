package com.company.productcatalogue.model;

import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class ProductCategoryModel {

	private long id;

	private String name;

	@JsonIgnore
	private Set<ProductModel> products;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Set<ProductModel> getProducts() {
		return products;
	}

	public void setProducts(Set<ProductModel> products) {
		this.products = products;
	}

}
