package com.example.crud.controller;

import com.example.crud.model.Product;
import com.example.crud.repository.ProductRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    private final ProductRepository productRepository;

    public ProductController(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    // Create Product
    @PostMapping
    public Product createProduct(@RequestBody Product product) {
        return productRepository.save(product);
    }

    // Get All Products
    @GetMapping
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    // Get Product By ID
@GetMapping("/{id}")
public Product getProductById(@PathVariable String id) {
    return productRepository.findById(id).orElse(null);
}

// Update Product
@PutMapping("/{id}")
public Product updateProduct(@PathVariable String id, @RequestBody Product product) {

    Product existingProduct = productRepository.findById(id).orElse(null);

    if (existingProduct != null) {
        existingProduct.setName(product.getName());
        existingProduct.setPrice(product.getPrice());
        existingProduct.setCategory(product.getCategory());
        existingProduct.setQuantity(product.getQuantity());

        return productRepository.save(existingProduct);
    }

    return null;
}

// Delete Product
@DeleteMapping("/{id}")
public String deleteProduct(@PathVariable String id) {

    productRepository.deleteById(id);

    return "Product deleted successfully";
}
}