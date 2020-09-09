package com.oms.orderservice.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name = "orderservice")
public class Order {


    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private int orderId;
    @Column(name = "customername")
    private String customerName;
    @Column(name = "address")
    private String address;
    @Column(name = "orderdate")
    private Date orderDate;
    @Column(name = "total")
    private double totalAmount;

}
