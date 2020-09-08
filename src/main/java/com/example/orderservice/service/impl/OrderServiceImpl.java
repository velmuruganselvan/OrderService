package com.example.orderservice.service.impl;

import com.example.orderservice.entity.Order;
import com.example.orderservice.exception.OrderNotFoundException;
import com.example.orderservice.model.OrderServiceDTO;
import com.example.orderservice.repo.OrderServiceRepo;
import com.example.orderservice.service.OrderService;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class OrderServiceImpl implements OrderService {

    private static Logger log = LoggerFactory.getLogger(OrderServiceImpl.class);


    @PersistenceContext
    EntityManager entityManager;

    @Autowired
    ModelMapper modelMapper;

    @Autowired
    OrderServiceRepo orderServiceRepo;

    @Transactional
    @Override
    public void saveOrderService(OrderServiceDTO orderServiceDTO) {
        try {
            Order order = modelMapper.map(orderServiceDTO, Order.class);
            log.info("OrderService -->{}",order.toString());
            entityManager.persist(order);
            log.info("Order Saved Successfully");
        } catch (Exception e) {
         log.info("Unable to Save the Order Service details {}",e.getMessage());
        }
    }

    @Override
    public OrderServiceDTO getOrderServiceDetailByID(int orderId) {
        OrderServiceDTO orderServiceDTO;
        try {
            Order order = orderServiceRepo.findByOrderID(orderId);
            orderServiceDTO = modelMapper.map(order, OrderServiceDTO.class);
        } catch (Exception e) {
            log.info("Unable to retrieve the order details.. {}",e.getMessage());
            throw  new OrderNotFoundException();
        }
        return orderServiceDTO;
    }

    @Override
    public List<OrderServiceDTO> retrieveOrderServices() {
        List<OrderServiceDTO> orderServiceDTOS = new ArrayList<>();
        try {
            List<Order> orders = orderServiceRepo.findAll();
            for (Order order : orders) {
                OrderServiceDTO orderServiceDTO = modelMapper.map(order, OrderServiceDTO.class);
                orderServiceDTOS.add(orderServiceDTO);
            }
        } catch (Exception e) {
            log.error("Unable to retrieve the order details.. {}", e.getMessage());
            throw new OrderNotFoundException();
        }


        return orderServiceDTOS;
    }
}
