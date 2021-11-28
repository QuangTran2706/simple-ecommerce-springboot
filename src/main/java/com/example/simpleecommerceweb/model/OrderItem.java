package com.example.simpleecommerceweb.model;

import javax.persistence.*;

@Entity
public class OrderItem {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "order_item_seq")
    @SequenceGenerator(name = "order_item_seq", sequenceName = "order_item_seq", initialValue = 1000, allocationSize = 1)
    private Long id;
    private Long amount;
    private Long quantity;

    @OneToOne
    private Product product;
}
