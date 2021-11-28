package com.example.simpleecommerceweb.controller;

import com.example.simpleecommerceweb.exception.InputFieldException;
import com.example.simpleecommerceweb.model.Product;
import com.example.simpleecommerceweb.model.ProductRequest;
import com.example.simpleecommerceweb.model.ProductSearchRequest;
import com.example.simpleecommerceweb.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/products")
public class ProductController {
    private final ProductService productService;

    @GetMapping
    public ResponseEntity<List<Product>> getAllProduct() {
        return ResponseEntity.ok(productService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable("id") Long productId) {
        return ResponseEntity.ok(productService.findProductById(productId));
    }

    @PostMapping("/add")
    public ResponseEntity<Product> addProduct(@RequestBody ProductRequest productRequest) {
        return ResponseEntity.ok(productService.saveProduct(productRequest));
    }

    @PostMapping("/edit")
    public ResponseEntity<Product> updateProduct(@RequestBody ProductRequest productRequest) {
        return ResponseEntity.ok(productService.saveProduct(productRequest));
    }

    @PostMapping("/search")
    public ResponseEntity<List<Product>> search(@RequestBody ProductSearchRequest productSearchRequest) {
        return ResponseEntity.ok(productService.filter(productSearchRequest.getBrands(),
                                                        productSearchRequest.getPrices(),
                                                        productSearchRequest.getProductCategory(),
                                                        productSearchRequest.isSortByPrice()));
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<List<Product>> deletePerfume(@PathVariable(value = "id") Long id) {
        return ResponseEntity.ok(productService.deleteProduct(id));
    }
}
