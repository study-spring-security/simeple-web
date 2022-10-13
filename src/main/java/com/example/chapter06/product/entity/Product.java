package com.example.chapter06.product.entity;

import javax.persistence.*;

@Entity
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;
    private String price;

    @Enumerated(EnumType.STRING)
    private Currency currency;

}
