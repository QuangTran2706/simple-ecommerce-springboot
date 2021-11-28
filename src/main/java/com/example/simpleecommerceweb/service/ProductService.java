package com.example.simpleecommerceweb.service;

import com.example.simpleecommerceweb.model.Product;
import com.example.simpleecommerceweb.model.ProductRequest;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ProductService {

    List<Product> findAll();

    Product findProductById(Long ProductId);

    List<Product> filter(List<String> brands, List<Integer> prices, List<String> productCategory, boolean sortByPrice);

    Product saveProduct(ProductRequest productRequest);

    List<Product>  deleteProduct(Long ProductId);
}
