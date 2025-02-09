package com.myProject.ecommerce.controller;

import com.myProject.ecommerce.model.Product;
import com.myProject.ecommerce.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//@CrossOrigin(origins = "http://localhost:5173")
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api")
public class ProductController {

    @Autowired
    ProductService productService;

    @GetMapping("/test")
    public String test() {
        return "Hello from Spring Boot!!";
    }

    @PostMapping("/product")
    public ResponseEntity<Product> createOrUpdateProduct(@RequestBody Product product) {
        Product savedProduct = productService.saveOrUpdateProduct(product);
        return new ResponseEntity<>(savedProduct, HttpStatus.CREATED);
    }

    @GetMapping("/products")
    public List<Product> getAllProducts() {
        return productService.getAllProducts();
    }

    @PutMapping("/product")
    public ResponseEntity<Product> updateProduct(@RequestBody Product product) {
        if (product.getId() == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);  // If id is missing
        }
        Product updatedProduct = productService.updateProduct(product); // Delegate update to service layer
        if (updatedProduct == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);  // If product not found
        }
        return ResponseEntity.ok(updatedProduct);  // Return updated product
    }

    // Delete method (id passed in the body)
    @DeleteMapping("/product")
    public ResponseEntity<String> deleteProduct(@RequestBody Product product) {
        if (product.getId() == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Product ID is required");
        }
        boolean isDeleted = productService.deleteProduct(product.getId()); // Delegate delete to service layer
        if (!isDeleted) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Product not found");
        }
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Product deleted successfully");
    }
}
