package com.bridgelabz.bookstoreorderservice.service;

import com.bridgelabz.bookstoreorderservice.exception.UserException;
import com.bridgelabz.bookstoreorderservice.model.AddressModel;
import com.bridgelabz.bookstoreorderservice.model.OrderModel;
import com.bridgelabz.bookstoreorderservice.repository.IAddressRepository;
import com.bridgelabz.bookstoreorderservice.repository.IOrderRepository;
import com.bridgelabz.bookstoreorderservice.util.CartResponse;
import com.bridgelabz.bookstoreorderservice.util.Response;
import com.bridgelabz.bookstoreorderservice.util.TokenUtil;
import com.bridgelabz.bookstoreorderservice.util.UserResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class OrderService implements IOrderService{

    @Autowired
    IOrderRepository orderRepository;
    @Autowired
    IAddressRepository addressRepository;
    @Autowired
    TokenUtil tokenUtil;

    @Autowired
    MailService mailService;

    @Autowired
    RestTemplate restTemplate;
    @Override
    public Response placeOrder(String token, Long cartId, Long addressId) {
        UserResponse isUserPresent = restTemplate.getForObject("http://BS-USER-SERVICE:8083/user/verify/" + token, UserResponse.class);
        if (isUserPresent.getStatusCode() == 200){
            CartResponse isCartPresent = restTemplate.getForObject("http://CART-SERVICE:8085/cart/verifyCartItem/" + cartId, CartResponse.class);
            if (isUserPresent.getStatusCode() == 200){
                if (isUserPresent.getObject().getId() == isCartPresent.getObject().getUserId()){
                    OrderModel orderModel = new OrderModel();
                    orderModel.setQuantity(isCartPresent.getObject().getQuantity());
                    orderModel.setPrice(isCartPresent.getObject().getPrice());
                    orderModel.setOrderDate(LocalDateTime.now());
                    orderModel.setBookId(isCartPresent.getObject().getBookId());
                    orderModel.setUserId(isUserPresent.getObject().getId());
                    orderModel.setCancel(false);
                    Optional<AddressModel> isAddressPresent = addressRepository.findById(addressId);
                    if (isAddressPresent.isPresent()){
                        if (isAddressPresent.get().getUserId() == isUserPresent.getObject().getId());
                        orderModel.setAddress(isAddressPresent.get());
                    } else {
                        throw new UserException(400, "Enter Correct Address");
                    }
                    orderRepository.save(orderModel);
                    CartResponse deleteCart = restTemplate.getForObject("http://CART-SERVICE:8085/cart/deleteCartItem/" + cartId, CartResponse.class);
                    String body = "order placed with id: " + orderModel.getId();
                    String subject = "Order Placed ";
                    mailService.send(isUserPresent.getObject().getEmailId(), subject, body);
                    return new Response("Placed order", 200, orderModel);
                }
            }
        }
        throw new UserException(400, "Nothing in cart");
    }

    @Override
    public Response cancelOrder(String token, Long orderId) {
        UserResponse isUserPresent = restTemplate.getForObject("http://BS-USER-SERVICE:8083/user/verify/" + token, UserResponse.class);
        if (isUserPresent.getStatusCode() == 200){
            Optional<OrderModel> isOrderPresent = orderRepository.findById(orderId);
            if (isOrderPresent.isPresent()){
                if (isOrderPresent.get().getUserId() == isUserPresent.getObject().getId());
                isOrderPresent.get().setCancel(true);
                orderRepository.save(isOrderPresent.get());
                Response updateBookQuantity = restTemplate.getForObject("http://BOOK-SERVICE/books/changeBookQuantity/" + isOrderPresent.get().getQuantity() + "/"
                        +isOrderPresent.get().getBookId(), Response.class);
                String body = "order is cancel with this id : " + isOrderPresent.get().getId();
                String subject = "Order canceled ";
                mailService.send(isUserPresent.getObject().getEmailId(), subject, body);
                return new Response("order canceled", 200, isOrderPresent.get());
            }
        }
        throw new UserException(400, "No order Found");
    }

    @Override
    public List<OrderModel> getAllOrderForUser(String token) {
        boolean isUserPresent = restTemplate.getForObject("http://BS-USER-SERVICE:8083/user/verify/" + token, boolean.class);
        if (isUserPresent){
            Long userId = tokenUtil.decodeToken(token);
            Optional<OrderModel> orderModel = orderRepository.findById(userId);
            if (orderModel.isPresent()){
                List<OrderModel> getAllOrderForUser = orderRepository.findAll();
                if (getAllOrderForUser.size() > 0){
                    return getAllOrderForUser;
                } else {
                    throw new UserException(400, "Nothing to order");
                }
            }
        }
        throw new UserException(400, "Not found any order");
    }

    @Override
    public List<OrderModel> getAllOrder() {
        return orderRepository.findAll();
    }
}
