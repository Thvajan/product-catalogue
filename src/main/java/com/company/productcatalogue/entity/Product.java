package com.company.productcatalogue.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Product {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	private String name;

	private int quantity;

	private String size;

	private float price;

	@ManyToOne
	@JoinColumn(insertable = false, updatable = false, name = "BRAND_ID")
	private Brand brand;

	private String color;

	@ManyToOne
	@JoinColumn(insertable = false, updatable = false, name = "PRODUCT_CATEGORY_ID")
	private ProductCategory productCategory;

	public Product(long id, String name, int quantity, String size, float price, String brand, String color,
			String productCategoryName) {
		super();
		this.id = id;
		this.name = name;
		this.quantity = quantity;
		this.size = size;
		this.price = price;
		this.brand = new Brand();
		this.brand.setName(brand);
		this.color = color;
		this.productCategory = new ProductCategory();
		this.productCategory.setName(productCategoryName);
	}

	public Product() {
		super();
		// TODO Auto-generated constructor stub
	}

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

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public String getSize() {
		return size;
	}

	public void setSize(String size) {
		this.size = size;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public Brand getBrand() {
		return brand;
	}

	public void setBrand(Brand brand) {
		this.brand = brand;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public ProductCategory getProductCategory() {
		return productCategory;
	}

	public void setProductCategory(ProductCategory productCategory) {
		this.productCategory = productCategory;
	}

}
