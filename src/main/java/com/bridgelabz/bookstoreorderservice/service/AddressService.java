package com.bridgelabz.bookstoreorderservice.service;

import com.bridgelabz.bookstoreorderservice.DTO.AddressDTO;
import com.bridgelabz.bookstoreorderservice.exception.UserException;
import com.bridgelabz.bookstoreorderservice.model.AddressModel;
import com.bridgelabz.bookstoreorderservice.repository.IAddressRepository;
import com.bridgelabz.bookstoreorderservice.util.Response;
import com.bridgelabz.bookstoreorderservice.util.TokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;

@Service
public class AddressService implements IAddressService{

    @Autowired
    IAddressRepository addressRepository;

    @Autowired
    TokenUtil tokenUtil;

    @Autowired
    MailService mailService;

    @Autowired
    RestTemplate restTemplate;
    @Override
    public Response addAddress(AddressDTO addressDTO, String token) {
        boolean isUserPresent = restTemplate.getForObject("http://BS-USER-SERVICE:8083/user/verify/" + token, Boolean.class);
        if (isUserPresent) {
            Long userId = tokenUtil.decodeToken(token);
            AddressModel addressModel = new AddressModel(addressDTO);
            addressModel.setId(userId);
            addressRepository.save(addressModel);
            return new Response("Address Added", 200, addressModel);
        }
        throw new UserException(400, "Token Wrong");
    }

    @Override
    public List<AddressModel> getAllAddress(String token) {
        boolean isUserPresent = restTemplate.getForObject("http://BS-USER-SERVICE:8083/user/verify/" + token, Boolean.class);
        if (isUserPresent) {
            Long userId = tokenUtil.decodeToken(token);
            Optional<AddressModel> addressModel = addressRepository.findById(userId);
            if (addressModel.isPresent()){
                List<AddressModel> getAllAddress = addressRepository.findAll();
                if (getAllAddress.size() > 0){
                    return getAllAddress;
                } else {
                    throw new UserException(400, "No Data Found");
                }
            }
        }
        throw new UserException(400, "Address not Found");
    }

    @Override
    public Response updateAddress(long addressId, AddressDTO addressDTO, String token) {
        boolean isUserPresent = restTemplate.getForObject("http://BS-USER-SERVICE:8083/user/verify/" + token, Boolean.class);
        if (isUserPresent) {
            Long userId = tokenUtil.decodeToken(token);
            Optional<AddressModel> addressModel = addressRepository.findById(addressId);
            if (addressModel.isPresent()){
                addressModel.get().setName(addressDTO.getName());
                addressModel.get().setAddress(addressDTO.getAddress());
                addressModel.get().setPincode(addressDTO.getPincode());
                addressModel.get().setPhoneNo(addressDTO.getPhoneNo());
                addressModel.get().setCity(addressDTO.getCity());
                addressRepository.save(addressModel.get());
                return new Response("Address Upadated successfully", 200, null);
            }
        }
        throw new UserException(400, "Address Not Found");
    }

    @Override
    public Response deleteAddress(Long addressId, String token) {
        boolean isUserPresent = restTemplate.getForObject("http://BS-USER-SERVICE:8083/user/verify/" + token, Boolean.class);
        if (isUserPresent) {
            Long userId = tokenUtil.decodeToken(token);
            Optional<AddressModel> addressModel = addressRepository.findById(userId);
            if (addressModel.isPresent()){
                addressRepository.delete(addressModel.get());
                return new Response("Address Deleted", 200, null);
            }
        }
        throw new UserException(400, "Address Not Found");

    }
}
