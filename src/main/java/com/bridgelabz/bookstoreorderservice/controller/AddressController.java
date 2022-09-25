package com.bridgelabz.bookstoreorderservice.controller;

import com.bridgelabz.bookstoreorderservice.DTO.AddressDTO;
import com.bridgelabz.bookstoreorderservice.model.AddressModel;
import com.bridgelabz.bookstoreorderservice.service.IAddressService;
import com.bridgelabz.bookstoreorderservice.util.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/address")
public class AddressController {

    @Autowired
    IAddressService addressService;

    @PostMapping(value = "/addAddress")
    ResponseEntity<Response> addAddress(@RequestBody AddressDTO addressDTO, @RequestHeader String token) {
        Response response = addressService.addAddress(addressDTO, token);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/getAllAddress")
    public List<AddressModel> getAllAddress(@RequestHeader String token){

        return addressService.getAllAddress(token);
    }

    @PutMapping("updateAddress/{id}")
    ResponseEntity<Response> updateAddress( @RequestBody AddressDTO addressDTO, @PathVariable long addressId, @RequestHeader String token ){
        Response response = addressService.updateAddress(addressId, addressDTO, token);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("deleteAddress/{id}")
    ResponseEntity<Response> deleteAddress(@PathVariable Long addressId, @RequestHeader String token){

        Response response = addressService.deleteAddress(addressId, token);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
