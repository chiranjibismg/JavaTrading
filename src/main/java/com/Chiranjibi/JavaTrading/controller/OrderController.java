package com.Chiranjibi.JavaTrading.controller;


import com.Chiranjibi.JavaTrading.domain.OrderType;
import com.Chiranjibi.JavaTrading.models.Coin;
import com.Chiranjibi.JavaTrading.models.Orders;
import com.Chiranjibi.JavaTrading.models.User;
import com.Chiranjibi.JavaTrading.request.CreateOrderRequest;
import com.Chiranjibi.JavaTrading.service.CoinService;
import com.Chiranjibi.JavaTrading.service.OrderService;
import com.Chiranjibi.JavaTrading.service.UserService;
import com.Chiranjibi.JavaTrading.service.WalletTransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
public class OrderController {


    @Autowired
    private OrderService orderService;

    @Autowired
    private UserService userSerivce;

    @Autowired
    private CoinService coinService;

    @Autowired
    private WalletTransactionService walletTransactionService;



    @PostMapping("/pay")
    public ResponseEntity<Orders> payOrderPayment(
            @RequestHeader("Authorization") String jwt,
            @RequestBody CreateOrderRequest req

    ) throws Exception {
        User user = userSerivce.findUserProfileByJwt(jwt);
        Coin coin =coinService.findById(req.getCoinId());


        Orders order = orderService.processOrder(coin,req.getQuantity(),req.getOrderType(),user);

        return ResponseEntity.ok(order);

    }

    @GetMapping("/{orderId}")
    public ResponseEntity<Orders> getOrderById(
            @RequestHeader("Authorization") String jwtToken,
            @PathVariable Long orderId
    ) throws Exception {


        User user = userSerivce.findUserProfileByJwt(jwtToken);

        Orders order = orderService.getOrderById(orderId);
        if (order.getUser().getId().equals(user.getId())) {
            return ResponseEntity.ok(order);
        } else {
            throw  new Exception("This order cant be accessed by you.");
        }
    }

    @GetMapping()
    public ResponseEntity<List<Orders>> getAllOrdersForUser(
            @RequestHeader("Authorization") String jwt,
            @RequestParam(required = false) OrderType order_type,
            @RequestParam(required = false) String asset_symbol
    ) throws Exception {


        Long userId = userSerivce.findUserProfileByJwt(jwt).getId();

        List<Orders> userOrders = orderService.getAllOrdersForUser(userId,order_type,asset_symbol);
        return ResponseEntity.ok(userOrders);
    }




}
