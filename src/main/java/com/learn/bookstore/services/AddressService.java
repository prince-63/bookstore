package com.learn.bookstore.services;

import com.learn.bookstore.models.Address;

import java.util.List;

public interface AddressService {

    Address addAddress(String email, Address address);

    List<Address> getAddressesByUser(String email);

    Address updateAddress(String email, Long addressId, Address updatedAddress);

    void deleteAddress(String email, Long addressId);

}
