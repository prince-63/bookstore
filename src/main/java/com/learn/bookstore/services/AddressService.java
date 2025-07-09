package com.learn.bookstore.services;

import com.learn.bookstore.models.Address;

import java.util.List;

public interface AddressService {

    Address addAddress(Long userId, Address address);

    List<Address> getAddressesByUser(Long userId);

    Address updateAddress(Long id, Address address);

    void deleteAddress(Long id);

}
