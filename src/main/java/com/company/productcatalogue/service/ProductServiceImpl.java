package com.company.productcatalogue.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.company.productcatalogue.entity.Product;
import com.company.productcatalogue.model.ProductModel;
import com.company.productcatalogue.repository.ProductRepository;

@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	EntityManager entityManager;

	@Autowired
	ProductRepository productRepository;

	@Override
	public List<ProductModel> getAllProducts() {
		List<Product> products = productRepository.findAllProducts();
		List<ProductModel> productModels = new ArrayList<>();
		products.parallelStream().forEach((prod) -> {
			ProductModel productModel = new ProductModel();
			BeanUtils.copyProperties(prod, productModel);
			productModels.add(productModel);
		});
		return productModels;
	}

	@Override
	public ProductModel getProductById(long id) {
		ProductModel productModel = null;
		Optional<Product> productOptional = productRepository.findById(id);
		if (productOptional.isPresent()) {
			productModel = new ProductModel();
			BeanUtils.copyProperties(productOptional.get(), productModel);
		}
		return productModel;
	}

	@Override
	public long createProduct(ProductModel productModel) {
		Product product = new Product();
		BeanUtils.copyProperties(productModel, product);
		return productRepository.save(product).getId();
	}

	@Override
	public void updateProduct(ProductModel productModel) {
		Product product = new Product();
		BeanUtils.copyProperties(productModel, product);
		productRepository.save(product);
	}

	@Override
	public void deleteProduct(long id) {
		productRepository.deleteById(id);
	}

	@Override
	public List<ProductModel> getProductsByFilter(Map<String, String> filters) {
		if (filters == null || filters.isEmpty())
			return getAllProducts();

		List<Product> products = null;
		List<ProductModel> productModels = new ArrayList<>();
		StringBuilder baseQuery = new StringBuilder(
				"SELECT new com.company.productcatalogue.entity.Product(p.id, p.name, p.quantity, p.size, p.price, p.brand.name, p.color,"
						+ "p.productCategory.name) from Product p " + "INNER JOIN p.brand b "
						+ "INNER JOIN p.productCategory pc");
		List<String> whereClauseList = new ArrayList<>(filters.size());
		filters.forEach((k, v) -> {
			switch (k) {
			case "brand":
				whereClauseList.add("b.name in :brands");
				break;
			case "color":
				whereClauseList.add("p.color in :colors");
				break;
			}
		});
		if (!whereClauseList.isEmpty()) {
			baseQuery.append(" where ");
			for (int i = 0; i < whereClauseList.size(); i++) {
				String clause = whereClauseList.get(i);
				if (i != 0) {
					baseQuery.append(" and ");
				}
				baseQuery.append(clause);
			}
			TypedQuery<Product> createQuery = entityManager.createQuery(baseQuery.toString(), Product.class);
			filters.forEach((k, v) -> {
				switch (k) {
				case "brand":
					createQuery.setParameter("brands", List.of(v.split(",")));
					break;
				case "color":
					createQuery.setParameter("colors", List.of(v.split(",")));
					break;
				}
			});
			products = createQuery.getResultList();
			if (!products.isEmpty()) {
				products.parallelStream().forEach((prod) -> {
					ProductModel productModel = new ProductModel();
					BeanUtils.copyProperties(prod, productModel);
					productModels.add(productModel);
				});
			}
		}
		return productModels;
	}

	@Override
	public List<ProductModel> getProductByName(String name) {
		List<ProductModel> productModels = null;
		List<Product> products = productRepository.findByName(name);
		if (!products.isEmpty()) {
			BeanUtils.copyProperties(products, productModels);
		}
		return productModels;
	}
}
