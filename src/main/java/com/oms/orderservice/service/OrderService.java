package com.oms.orderservice.service;

import com.oms.orderservice.model.OrderServiceDTO;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderService {

    public void saveOrderService(OrderServiceDTO orderServiceDTO);

    public OrderServiceDTO getOrderServiceDetailByID(int orderId);

    public List<OrderServiceDTO> retrieveOrderServices();


}
