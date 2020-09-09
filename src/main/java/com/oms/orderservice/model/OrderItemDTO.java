package com.oms.orderservice.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "productid",
        "orderId",
        "productcode",
        "productname",
        "quantity"
})
public class OrderItemDTO {


    @JsonProperty("productid")
    private int productid;
    @JsonProperty("productcode")
    @NotNull
    @Size(min = 1, max = 50, message = "PRD_CODE_ERROR_MSG")
    private String productcode;
    @JsonProperty("productname")
    @NotNull
    @Size(min = 1, max = 100, message = "PRD_NAME_ERROR_MSG")
    private String productname;
    @JsonProperty("quantity")
    @NotNull
    @Positive
    private int quantity;
    @JsonProperty("orderId")
    private int orderId;

    public OrderItemDTO(){}

    public OrderItemDTO(int productid, @NotNull @Size(min = 1, max = 50, message = "PRD_CODE_ERROR_MSG") String productcode, @NotNull @Size(min = 1, max = 100, message = "PRD_NAME_ERROR_MSG") String productname, @NotNull @Positive int quantity, int orderId) {
        this.productid = productid;
        this.productcode = productcode;
        this.productname = productname;
        this.quantity = quantity;
        this.orderId = orderId;
    }
}
