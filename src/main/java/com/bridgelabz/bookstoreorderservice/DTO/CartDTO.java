package com.bridgelabz.bookstoreorderservice.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

@Data
@AllArgsConstructor
@ToString
public class CartDTO {
    private Long id;
    private Long userId;
    private Long bookId;
    private Long quantity;
    private Long price;
    private String emailId;

}
