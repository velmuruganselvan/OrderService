package com.example.orderservice.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "orderitem")
public class OrderItem {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "productid")
    private int productId;
    @Column(name = "productcode")
    private String productCode;
    @Column(name = "productname")
    private String productName;
    @Column(name = "quantity")
    private int quantity;
}
