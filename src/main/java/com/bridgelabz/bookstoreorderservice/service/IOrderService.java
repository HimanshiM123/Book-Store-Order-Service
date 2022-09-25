package com.bridgelabz.bookstoreorderservice.service;

import com.bridgelabz.bookstoreorderservice.model.OrderModel;
import com.bridgelabz.bookstoreorderservice.util.Response;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface IOrderService {

    Response placeOrder(String token, Long bookId, Long cartId);

    Response cancelOrder(String token, Long orderId);


   List<OrderModel> getAllOrderForUser(String token);

    List<OrderModel> getAllOrder();
}
