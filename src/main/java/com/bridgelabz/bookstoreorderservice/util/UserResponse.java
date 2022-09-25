package com.bridgelabz.bookstoreorderservice.util;

import com.bridgelabz.bookstoreorderservice.DTO.CartDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

@Data
@AllArgsConstructor
@ToString

public class UserResponse {
    private String message;
    private int statusCode;
    private CartDTO Object;

    public UserResponse() {
    }
}
