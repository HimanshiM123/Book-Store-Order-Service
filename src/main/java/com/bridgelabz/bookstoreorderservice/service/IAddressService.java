package com.bridgelabz.bookstoreorderservice.service;

import com.bridgelabz.bookstoreorderservice.DTO.AddressDTO;
import com.bridgelabz.bookstoreorderservice.model.AddressModel;
import com.bridgelabz.bookstoreorderservice.util.Response;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface IAddressService {
    Response addAddress(AddressDTO addressDTO, String token);

    List<AddressModel> getAllAddress(String token);

    Response updateAddress(long addressId, AddressDTO addressDTO, String token);

    Response deleteAddress(Long addressId, String token);
}
