package com.bridgelabz.bookstoreorderservice.model;

import com.bridgelabz.bookstoreorderservice.DTO.AddressDTO;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "address")
@Data
public class AddressModel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String address;
    private String pincode;
    private String phoneNo;
    private String city;
    private Long userId;
    private LocalDateTime registeredTime;
    private LocalDateTime updatedTime;

    public AddressModel(AddressDTO addressDTO) {
        this.name = addressDTO.getName();
        this.address = addressDTO.getAddress();
        this.pincode = addressDTO.getPincode();
        this.phoneNo = addressDTO.getPhoneNo();
        this.city = addressDTO.getCity();
    }

    public AddressModel() {

    }
}
