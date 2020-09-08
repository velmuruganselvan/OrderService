package com.example.orderservice.controller;


import com.example.orderservice.common.OrderServiceConstants;
import com.example.orderservice.exception.OrderNotFoundException;
import com.example.orderservice.model.OrderServiceDTO;
import com.example.orderservice.service.OrderService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(OrderServiceConstants.ORDER_SERVICR_API)
public class OrderServiceController {

    Logger log = LoggerFactory.getLogger(OrderServiceController.class);

    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    OrderService orderService;


    @PostMapping()
    public ResponseEntity<String> handleOrderService(@Valid @RequestBody OrderServiceDTO orderServiceDTO) {
        try {
            orderService.saveOrderService(orderServiceDTO);
            ResponseEntity.ok("Order Saved Successfully");
        } catch (Exception e) {
            log.info("Unable to save the order details. {}", e.getMessage());
        }
        return null;
    }

    @GetMapping("{id}")
    public OrderServiceDTO getOrderDetails(@PathVariable String id) {
        OrderServiceDTO orderServiceDTO = null;
        try {
            orderServiceDTO = orderService.getOrderServiceDetailByID(Integer.parseInt(id));
        } catch (Exception e) {
            log.error("Unable to retrieve order details ...{}",e.getMessage());
            throw new OrderNotFoundException();
        }
        return  orderServiceDTO;
    }

    @GetMapping("/")
    public List<OrderServiceDTO> getAllOrderDetails() {
        List<OrderServiceDTO> orderServiceDTOs = null;
        try {
            orderServiceDTOs = orderService.retrieveOrderServices();
        } catch (Exception e) {
            log.error("Unable to retrieve order details ...{}",e.getMessage());
            throw new OrderNotFoundException();
        }
        return  orderServiceDTOs;
    }

}
