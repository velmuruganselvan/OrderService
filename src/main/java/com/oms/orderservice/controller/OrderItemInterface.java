package com.oms.orderservice.controller;

import com.oms.orderservice.model.OrderItemDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@FeignClient(name = "order-items", url = "http://localhost:8091/orderitem")
public interface OrderItemInterface {

    @GetMapping("{id}")
    public List<OrderItemDTO> getOrderItems(@PathVariable("id") int id);

    @PostMapping
    public void createOrderItems(OrderItemDTO orderItemDTO);

}
