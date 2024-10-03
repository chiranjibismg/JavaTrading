package com.Chiranjibi.JavaTrading.request;

import com.Chiranjibi.JavaTrading.domain.OrderType;
import lombok.Data;

@Data
public class CreateOrderRequest {

    private String coinId;
    private double quantity;
    private OrderType orderType;
}
