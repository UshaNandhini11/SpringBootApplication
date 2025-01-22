package com.myProject.ecommerce.service;

import com.myProject.ecommerce.model.Product;
import com.myProject.ecommerce.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
    @Autowired
    ProductRepository productRepository;

    public Product saveOrUpdateProduct(Product product) {
        return productRepository.save(product);
    }

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public Product updateProduct(Product product) {
        Product existingProduct = productRepository.findById(product.getId()).orElse(null);
        if (existingProduct == null) {
            return null;  // If product not found, return null
        }
        // Update product fields
        existingProduct.setName(product.getName());
        existingProduct.setPrice(product.getPrice());
        existingProduct.setDescription(product.getDescription());
        return productRepository.save(existingProduct);// Save the updated product
    }

    public boolean deleteProduct(Long id) {
        if (!productRepository.existsById(id)) {
            return false;  // If product doesn't exist, return false
        }
        productRepository.deleteById(id); // Perform deletion
        return true;  // Return true indicating the product was deleted
    }
}
