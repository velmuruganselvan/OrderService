package com.oms.orderservice.service.impl;

import com.oms.orderservice.controller.OrderItemInterface;
import com.oms.orderservice.entity.Order;
import com.oms.orderservice.exception.OrderNotFoundException;
import com.oms.orderservice.model.OrderItemDTO;
import com.oms.orderservice.model.OrderServiceDTO;
import com.oms.orderservice.repo.OrderServiceRepo;
import com.oms.orderservice.service.OrderService;
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

@Service
public class OrderServiceImpl implements OrderService {

    private static Logger log = LoggerFactory.getLogger(OrderServiceImpl.class);


    @PersistenceContext
    EntityManager entityManager;

    @Autowired
    ModelMapper modelMapper;

    @Autowired
    OrderServiceRepo orderServiceRepo;

    @Autowired
    OrderItemInterface orderItemInterface;

    @Transactional
    @Override
    public void saveOrderService(OrderServiceDTO orderServiceDTO) {
        try {
            Order order = modelMapper.map(orderServiceDTO, Order.class);
            log.info("OrderService -->{}",order.toString());
            entityManager.persist(order);
            for (OrderItemDTO orderItemDTO : orderServiceDTO.getOrderItems()) {
                orderItemDTO.setOrderId(order.getOrderId());
                orderItemInterface.createOrderItems(orderItemDTO);
            }
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
            List<OrderItemDTO> orderItemDTOS = orderItemInterface.getOrderItems(orderId);
            orderServiceDTO.setOrderItems(orderItemDTOS);
        } catch (Exception e) {
            log.info("Unable to retrieve the order details.. {}",e.getMessage());
            throw  new OrderNotFoundException();
        }
        return orderServiceDTO;
    }

    @Override
    public List<OrderServiceDTO> retrieveOrderServices() {
        List<OrderServiceDTO> orderServiceDTOS = null;
        try {
            List<Order> orders = orderServiceRepo.findAll();
            if (orders!=null && orders.size()>0) {
                orderServiceDTOS = new ArrayList<>();
                for (Order order : orders) {
                    OrderServiceDTO orderServiceDTO = modelMapper.map(order, OrderServiceDTO.class);
                    List<OrderItemDTO> orderItemDTOS = orderItemInterface.getOrderItems(order.getOrderId());
                    orderServiceDTO.setOrderItems(orderItemDTOS);
                    orderServiceDTOS.add(orderServiceDTO);
                }
            } else {
                throw  new OrderNotFoundException();
            }
        } catch (Exception e) {
            log.error("Unable to retrieve the order details.. {}", e.getMessage());
            throw new OrderNotFoundException();
        }


        return orderServiceDTOS;
    }
}
