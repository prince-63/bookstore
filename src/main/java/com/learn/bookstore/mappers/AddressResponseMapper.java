package com.learn.bookstore.mappers;

import com.learn.bookstore.dto.address.AddressResponseDTO;
import com.learn.bookstore.models.Address;

public class AddressResponseMapper {

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
}
