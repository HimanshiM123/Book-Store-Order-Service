package com.bridgelabz.bookstoreorderservice.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

@Data
@AllArgsConstructor
@ToString
public class OrderDTO {
    private Long price;
    private Long quantity;
}
