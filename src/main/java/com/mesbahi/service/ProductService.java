package com.mesbahi.service;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.ReflectionUtils;
import org.springframework.stereotype.Service;

import com.mesbahi.entity.Product;
import com.mesbahi.repository.ProductRepo;

@Service
public class ProductService {

    @Autowired
    private ProductRepo repository;


    public Product saveProduct(Product product) {
        return repository.save(product);
    }

    public List<Product> getProducts() {
        return repository.findAll();
    }

    public Product getProductById(int id) {
        return repository.findById(id).get();
    }


    public Product updateProduct(int id, Product productRequest) {
        // get the product from DB by id
        // update with new value getting from request
        Product existingProduct = repository.findById(id).get(); // DB
        existingProduct.setName(productRequest.getName());
        existingProduct.setDescription(productRequest.getDescription());
        existingProduct.setPrice(productRequest.getPrice());
        existingProduct.setProductType(productRequest.getProductType());
        return repository.save(existingProduct);
    }


    public long deleteProduct(int id) {
        repository.deleteById(id);
        return repository.count();
    }

    public Product updateProductByFields(int id, Map<String, Object> fields) {
        Optional<Product> existingProduct = repository.findById(id);

        if (existingProduct.isPresent()) {
            fields.forEach((key, value) -> {
                Field field = ReflectionUtils.findRequiredField(Product.class, key);
                field.setAccessible(true);
                ReflectionUtils.setField(field, existingProduct.get(), value);
            });
            return repository.save(existingProduct.get());
        }
        return null;
    }

}
