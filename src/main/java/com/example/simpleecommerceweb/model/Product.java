package com.example.simpleecommerceweb.model;

import com.sun.istack.NotNull;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name = "product")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "product_id_seq")
    @SequenceGenerator(name = "product_id_seq", sequenceName = "product_id_seq", initialValue = 1, allocationSize = 1)
    private Long id;
    @NotNull
    private String productName;
    private Integer year;
    private String country;
    private String productCategory;
    private String description;
    private String picturePath;
    private String brand;
    private Integer price;

    public Product() {

    }
}
