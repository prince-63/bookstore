package com.learn.bookstore.services.impl;

import com.learn.bookstore.exceptions.ResourceNotFoundException;
import com.learn.bookstore.models.Address;
import com.learn.bookstore.models.User;
import com.learn.bookstore.repositories.AddressRepository;
import com.learn.bookstore.repositories.UserRepository;
import com.learn.bookstore.services.AddressService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class AddressServiceImpl implements AddressService {

    private final UserRepository userRepository;
    private final AddressRepository addressRepository;

    @Override
    public Address addAddress(String email, Address address) throws ResourceNotFoundException {
        User user = userRepository.findByEmail(email).orElseThrow(() -> new ResourceNotFoundException("User", "email", email));
        address.setUser(user);
        addressRepository.save(address);
        return address;
    }

    @Override
    public List<Address> getAddressesByUser(String email) throws ResourceNotFoundException {
        User user = userRepository.findByEmail(email).orElseThrow(() -> new ResourceNotFoundException("User", "email", email));
        return addressRepository.findAllByUserId(user.getId());
    }

    @Override
    public Address updateAddress(String email, Long addressId, Address updatedAddress) throws ResourceNotFoundException {
        Address address = loadAddress(email, addressId);

        address.setLine1(updatedAddress.getLine1());
        address.setLine2(updatedAddress.getLine2());
        address.setCity(updatedAddress.getCity());
        address.setState(updatedAddress.getState());
        address.setPostalCode(updatedAddress.getPostalCode());
        address.setCountry(updatedAddress.getCountry());
        address.setType(updatedAddress.getType());
        address.setDefault(updatedAddress.isDefault());

        return addressRepository.save(address);
    }

    @Override
    public void deleteAddress(String email, Long addressId) {
       Address address = loadAddress(email, addressId);
       addressRepository.deleteById(address.getId());
    }

    private Address loadAddress(String email, Long addressId) {
        User user = userRepository.findByEmail(email).orElseThrow(() -> new ResourceNotFoundException("User", "email", email));
        Address address = addressRepository.findById(addressId).orElseThrow(() -> new ResourceNotFoundException("Address", "id", addressId.toString()));

        if (!address.getUser().getId().equals(user.getId())) {
            throw new ResourceNotFoundException("Address", "user", "User does not own this address");
        }

        return address;
    }
}
