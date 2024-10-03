package com.Chiranjibi.JavaTrading.service;

import com.Chiranjibi.JavaTrading.domain.OrderType;
import com.Chiranjibi.JavaTrading.models.Coin;
import com.Chiranjibi.JavaTrading.models.OrderItem;
import com.Chiranjibi.JavaTrading.models.Orders;
import com.Chiranjibi.JavaTrading.models.User;

import java.util.List;

public interface OrderService {

    Orders createOrder(User user, OrderItem orderItem, OrderType orderType);

    Orders getOrderById(Long orderId);

    List<Orders> getAllOrdersForUser(Long userId, OrderType orderType, String assetSymbol);

    void cancelOrder(Long orderId);


    Orders processOrder(Coin coin, double quantity, OrderType orderType, User user) throws Exception;



}
