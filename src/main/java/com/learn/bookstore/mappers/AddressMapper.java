package com.learn.bookstore.mappers;

import com.learn.bookstore.dto.user.address.AddressRequestDTO;
import com.learn.bookstore.dto.user.address.AddressResponseDTO;
import com.learn.bookstore.models.user.Address;

public class AddressMapper {

    public static AddressResponseDTO toDTO(Address address) {
        return AddressResponseDTO.builder()
                .addressId(address.getId())
                .line1(address.getLine1())
                .line2(address.getLine2())
                .city(address.getCity())
                .state(address.getState())
                .country(address.getCountry())
                .type(address.getType())
                .isDefault(address.isDefault())
                .postalCode(address.getPostalCode())
                .build();
    }

    public static Address toModel(AddressRequestDTO addressRequestDTO) {
        return Address.builder()
                .line1(addressRequestDTO.line1())
                .line2(addressRequestDTO.line2())
                .city(addressRequestDTO.city())
                .state(addressRequestDTO.state())
                .country(addressRequestDTO.country())
                .type(addressRequestDTO.type())
                .isDefault(addressRequestDTO.isDefault())
                .postalCode(addressRequestDTO.postalCode())
                .build();
    }
}
