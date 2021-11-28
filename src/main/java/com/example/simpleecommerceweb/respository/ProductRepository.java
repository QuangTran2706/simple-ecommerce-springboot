package com.example.simpleecommerceweb.respository;

import com.example.simpleecommerceweb.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    Product findProductById(Long id);

    List<Product> findAllByBrand(String brand);

    List<Product> findAllByBrandIn(List<String> brand);

    Product findProductByProductName(String productName);

    List<Product> findProductsByBrand(String Brand);

    List<Product> getAllByOrderByIdAsc();

    List<Product> findProductsByProductCategoryIn(List<String> productCategory);

    List<Product> findProductByPriceBetween(int startPrice, int endPrice);


}
