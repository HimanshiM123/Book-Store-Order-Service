package com.bridgelabz.bookstoreorderservice.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

@Data
@AllArgsConstructor
@ToString
public class AddressDTO {
    private String name;
    private String address;
    private String pincode;
    private String phoneNo;
    private String city;
}
