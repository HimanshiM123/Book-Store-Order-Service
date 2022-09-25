package com.bridgelabz.bookstoreorderservice.util;

import com.bridgelabz.bookstoreorderservice.DTO.CartDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

@Data
@AllArgsConstructor
@ToString

public class CartResponse {
    private String message;
    private int statusCode;
    private CartDTO Object;

    public CartResponse() {
    }
}
