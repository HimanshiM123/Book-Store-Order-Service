package com.bridgelabz.bookstoreorderservice.repository;

import com.bridgelabz.bookstoreorderservice.model.AddressModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IAddressRepository extends JpaRepository<AddressModel, Long> {
}
