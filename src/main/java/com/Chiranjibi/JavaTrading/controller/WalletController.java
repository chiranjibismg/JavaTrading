package com.Chiranjibi.JavaTrading.controller;


import com.Chiranjibi.JavaTrading.models.*;
import com.Chiranjibi.JavaTrading.response.PaymentResponse;
import com.Chiranjibi.JavaTrading.service.OrderService;
import com.Chiranjibi.JavaTrading.service.PaymentService;
import com.Chiranjibi.JavaTrading.service.UserService;
import com.Chiranjibi.JavaTrading.service.WalletService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/wallet")
public class WalletController {


    @Autowired
    private WalletService walleteService;

    @Autowired
    private UserService userService;


    @Autowired
    private OrderService orderService;
//
//    @Autowired
//    private WalletTransactionService walletTransactionService;
//
    @Autowired
    private PaymentService paymentService;

    @GetMapping("/api/wallet")
    public ResponseEntity<?> getUserWallet(@RequestHeader("Authorization")String jwt) throws Exception {
        User user=userService.findUserProfileByJwt(jwt);

        Wallet wallet = walleteService.getUserWallet(user);

        return new ResponseEntity<>(wallet, HttpStatus.ACCEPTED);
    }

    @PutMapping("/api/wallet/{walletId}/transfer")
    public ResponseEntity<Wallet> walletToWalletTransfer(@RequestHeader("Authorization")String jwt,
                                                         @PathVariable Long walletId,
                                                         @RequestBody WalletTranscation req
    ) throws Exception {
        User senderUser =userService.findUserProfileByJwt(jwt);


        Wallet reciverWallet = walleteService.findWalletById(walletId);

        Wallet wallet =walleteService.walletToWalletTransfer(senderUser,reciverWallet,req.getAmount()) ;

        return new ResponseEntity<>(wallet,HttpStatus.ACCEPTED);

    }

    @PutMapping("/api/wallet/order/{orderId}/pay")
    public ResponseEntity<Wallet> payOrderPayment(@RequestHeader("Authorization")String jwt,
                                                         @PathVariable Long orderId

    ) throws Exception {
        User user =userService.findUserProfileByJwt(jwt);

        Orders order=orderService.getOrderById(orderId);

        Wallet wallet = walleteService.payOrderPayment(order,user);

        return new ResponseEntity<>(wallet,HttpStatus.OK);


    }

    @PutMapping("/api/wallet/deposit")
    public ResponseEntity<Wallet> addMoneyToWallet(
            @RequestHeader("Authorization")String jwt,
            @RequestParam(name="order_id") Long orderId,
            @RequestParam(name="payment_id")String paymentId
    ) throws Exception {
        User user =userService.findUserProfileByJwt(jwt);
        Wallet wallet = walleteService.getUserWallet(user);


        PaymentOrder order = paymentService.getPaymentOrderById(orderId);
        Boolean status=paymentService.proceedPaymentOrder(order,paymentId);
        PaymentResponse res = new PaymentResponse();
        res.setPayment_url("Deposit Successful");

        if(status){
            wallet=walleteService.addBalance(wallet, order.getAmount());
        }


        return new ResponseEntity<>(wallet,HttpStatus.OK);

    }
}
