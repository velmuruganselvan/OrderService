package com.example.orderservice.model;

import com.example.orderservice.common.OrderServiceConstants;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.Size;
import java.util.Date;
import static com.example.orderservice.common.OrderServiceConstants.*;

@Data
public class OrderServiceDTO {

    private int orderId;
    @Size(max = 100, message = NAME_ERROR_MSG)
    private String customerName;
    @Size(max = 100, message = ADDRESS_ERROR_MSG)
    private String address;
    @JsonFormat(pattern = DATE_FORAMT)
    private Date orderDate;
    private double totalAmount;

    public OrderServiceDTO() {}

    public OrderServiceDTO(int orderId, String customerName, String address, Date orderDate, double totalAmount) {
        this.orderId = orderId;
        this.customerName = customerName;
        this.address = address;
        this.orderDate = orderDate;
        this.totalAmount = totalAmount;
    }

    @Override
    public String toString() {
        return "OrderServiceDTO{" +
                "orderId=" + orderId +
                ", customerName='" + customerName + '\'' +
                ", address='" + address + '\'' +
                ", orderDate=" + orderDate +
                ", totalAmount=" + totalAmount +
                '}';
    }
}
