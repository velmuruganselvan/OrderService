package com.oms.orderservice.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.List;

import static com.oms.orderservice.common.OrderServiceConstants.*;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "orderId",
        "customername",
        "address",
        "orderdate",
        "totalamount",
        "orderItems"
})
public class OrderServiceDTO {

    @JsonProperty("orderId")
    private int orderId;
    @JsonProperty("customername")
    @NotNull
    @Size(min = 1, max = 100, message = NAME_ERROR_MSG)
    private String customername;
    @JsonProperty("address")
    @Size(min = 1, max = 100, message = ADDRESS_ERROR_MSG)
    private String address;
    @JsonProperty("orderdate")
    @NotNull
    @JsonFormat(pattern = DATE_FORAMT)
    private Date orderdate;
    @JsonProperty("totalamount")
    @NotNull
    private double totalamount;
    @JsonProperty("orderItems")
    private List<OrderItemDTO> orderItems;

    public OrderServiceDTO() {
        super();
    }

    public OrderServiceDTO(int orderId, @NotNull @Size(min = 1, max = 100, message = NAME_ERROR_MSG) String customername, @Size(min = 1, max = 100, message = ADDRESS_ERROR_MSG) String address, @NotNull Date orderdate, @NotNull double totalamount, List<OrderItemDTO> orderItems) {
        this.orderId = orderId;
        this.customername = customername;
        this.address = address;
        this.orderdate = orderdate;
        this.totalamount = totalamount;
        this.orderItems = orderItems;
    }

    @Override
    public String toString() {
        return "OrderServiceDTO{" +
                "orderId=" + orderId +
                ", customername='" + customername + '\'' +
                ", address='" + address + '\'' +
                ", orderdate=" + orderdate +
                ", totalamount=" + totalamount +
                ", orderItems=" + orderItems +
                '}';
    }
}
