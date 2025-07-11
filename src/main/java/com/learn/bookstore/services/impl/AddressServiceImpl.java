package com.learn.bookstore.services.impl;

import com.learn.bookstore.dto.user.address.AddressRequestDTO;
import com.learn.bookstore.exceptions.ResourceNotFoundException;
import com.learn.bookstore.mappers.AddressMapper;
import com.learn.bookstore.models.user.Address;
import com.learn.bookstore.models.user.User;
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
    public Address addAddress(String email, AddressRequestDTO addressRequestDTO) throws ResourceNotFoundException {
        User user = userRepository.findByEmail(email).orElseThrow(() -> new ResourceNotFoundException("User", "email", email));
        Address address = AddressMapper.toModel(addressRequestDTO);
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
    public Address updateAddress(String email, Long addressId, AddressRequestDTO updatedAddress) throws ResourceNotFoundException {
        Address address = loadAddress(email, addressId);

        address.setLine1(updatedAddress.line1() != null ? updatedAddress.line1() : address.getLine1());
        address.setLine2(updatedAddress.line2() != null ? updatedAddress.line2() : address.getLine2());
        address.setCity(updatedAddress.city() != null ? updatedAddress.city() : address.getCity());
        address.setState(updatedAddress.state() != null ? updatedAddress.state() : address.getState());
        address.setPostalCode(updatedAddress.postalCode() != null ? updatedAddress.postalCode() : address.getPostalCode());
        address.setCountry(updatedAddress.country() != null ? updatedAddress.country() : address.getCountry());
        address.setType(updatedAddress.type() != null ? updatedAddress.type() : address.getType());
        address.setDefault(updatedAddress.isDefault() || address.isDefault());

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
