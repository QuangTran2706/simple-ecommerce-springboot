package com.example.simpleecommerceweb.model;

import lombok.Data;

import java.util.List;

@Data
public class ProductSearchRequest {
    java.util.List<String> brands;
    List<Integer> prices;
    List<String> productCategory;
    boolean sortByPrice;
}
