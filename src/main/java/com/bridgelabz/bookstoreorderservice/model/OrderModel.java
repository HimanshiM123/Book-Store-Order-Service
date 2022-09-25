package com.bridgelabz.bookstoreorderservice.model;

import com.bridgelabz.bookstoreorderservice.DTO.OrderDTO;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "order")
@Data
public class OrderModel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private LocalDateTime orderDate;
    private Long price;
    private Long quantity;
    private Long userId;
    private Long bookId;
    private boolean cancel;
   @ManyToOne
   @JoinColumn(name = "addressId")
    private AddressModel address;

    public OrderModel(OrderDTO orderDTO) {
        this.price = orderDTO.getPrice();
        this.quantity = orderDTO.getQuantity();
    }

    public OrderModel() {

    }
}
