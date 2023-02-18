package com.mesbahi.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mesbahi.entity.Product;
import com.mesbahi.service.ProductService;

@RestController
@RequestMapping("/products")
public class ProductController {

	@Autowired
	private ProductService service;

	@PostMapping
	public Product addProduct(@RequestBody Product product) {
		return service.saveProduct(product);
	}

	@GetMapping
	public List<Product> getProducts() {
		return service.getProducts();
	}

	@GetMapping("/{id}")
	public Product getProductById(@PathVariable int id) {
		return service.getProductById(id);
	}

	@PutMapping("/{id}")
	public Product updateProduct(@PathVariable int id, @RequestBody Product productRequest) {
		return service.updateProduct(id, productRequest);
	}

	@PatchMapping("/{id}")
	public Product updateProductFields(@PathVariable int id, @RequestBody Map<String, Object> fields) {
		return service.updateProductByFields(id, fields);
	}

	@DeleteMapping("/{id}")
	public long deleteProduct(@PathVariable int id) {
		return service.deleteProduct(id);
	}

}
