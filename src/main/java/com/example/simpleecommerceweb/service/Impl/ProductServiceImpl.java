package com.example.simpleecommerceweb.service.Impl;

import com.example.simpleecommerceweb.model.Product;
import com.example.simpleecommerceweb.model.ProductRequest;
import com.example.simpleecommerceweb.respository.ProductRepository;
import com.example.simpleecommerceweb.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.Console;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final ModelMapper modelMapper;


    @Override
    public List<Product> findAll() {
        return productRepository.findAll();
    }

    @Override
    public Product findProductById(Long id) {
        return productRepository.findProductById(id);
    }

    @Override
    public List<Product> filter(List<String> brands, List<Integer> prices, List<String> productCategory, boolean sortByPrice) {
        List<Product> products = new ArrayList<>();

        if(!brands.isEmpty() || !prices.isEmpty() || !productCategory.isEmpty()) {
            if (!brands.isEmpty()) {
                if(!products.isEmpty()) {
                    List<Product> brandList = new ArrayList<>();
                    for (String brand : brands) {
                        brandList.addAll(brandList.stream()
                                .filter(product -> product.getBrand().equals(brand))
                                .collect(Collectors.toList()));
                    }
                    products = brandList;
                } else {
                    products.addAll(productRepository.findAllByBrandIn(brands));
                }
            } 
            if (!productCategory.isEmpty()) {
                if(!products.isEmpty()) {
                    List<Product> categoryList = new ArrayList<>();
                    for (String category : productCategory) {
                        categoryList.addAll(categoryList.stream()
                                .filter(product -> product.getProductCategory().equals(category))
                                .collect(Collectors.toList()));
                    }
                    products = categoryList;
                } else {
                    products.addAll(productRepository.findProductsByProductCategoryIn(productCategory));
                }
            } else {
                products.addAll(productRepository.getAllByOrderByIdAsc());
            }
            if (!prices.isEmpty()) {
                products.addAll(productRepository.findProductByPriceBetween(prices.get(0), prices.get(1)));
            }
            if(sortByPrice) {
                products.sort(Comparator.comparing(Product::getPrice));
            } else {
                products.sort((product1, product2) -> product2.getPrice().compareTo(product1.getPrice()));
            }
        }
        return products;
    }


    @Override
    public Product saveProduct(ProductRequest productRequest) {
        Product product = modelMapper.map(productRequest, Product.class);
        productRepository.save(product);
        return product;
    }

    @Override
    @Transactional
    public List<Product> deleteProduct(Long ProductId) {
        Product product = productRepository.findProductById(ProductId);
        productRepository.delete(product);
        List<Product> products = productRepository.findAll();
        return products;
    }
}
