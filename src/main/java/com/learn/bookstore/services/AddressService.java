package com.learn.bookstore.services;

import com.learn.bookstore.dto.AddressRequestDTO;
import com.learn.bookstore.models.Address;

import java.util.List;

public interface AddressService {

    Address addAddress(String email, AddressRequestDTO address);

    List<Address> getAddressesByUser(String email);

    Address updateAddress(String email, Long addressId, AddressRequestDTO updatedAddress);

    void deleteAddress(String email, Long addressId);

}
