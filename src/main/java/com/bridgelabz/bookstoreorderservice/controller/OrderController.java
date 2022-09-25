package com.bridgelabz.bookstoreorderservice.controller;

import com.bridgelabz.bookstoreorderservice.model.OrderModel;
import com.bridgelabz.bookstoreorderservice.service.IAddressService;
import com.bridgelabz.bookstoreorderservice.service.IOrderService;
import com.bridgelabz.bookstoreorderservice.util.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    IOrderService orderService;


    @PostMapping(value = "/placeOrder")
    ResponseEntity<Response> placeOrder(@RequestHeader String token, @PathVariable Long bookId, @PathVariable Long cartId) {
        Response response = orderService.placeOrder(token, bookId, cartId);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping(value = "/cancelOrder")
    ResponseEntity<Response> cancelOrder(@RequestHeader String token, @PathVariable Long orderId) {
        Response response = orderService.cancelOrder(token, orderId);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping(value = "/getAllOrder/{token}")
    public List<OrderModel> getAllOrderForUser(@RequestHeader String token) {
        return orderService.getAllOrderForUser(token);

    }
    @GetMapping(value = "/getAllOrder")
    public List<OrderModel> getOrder() {
        return orderService.getAllOrder();
    }
}
