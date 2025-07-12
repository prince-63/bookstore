package com.learn.bookstore.dto;

import com.learn.bookstore.models.AddressType;
import lombok.Builder;

@Builder
public record AddressResponseDTO(Long addressId, String line1, String line2, String city, String state, String postalCode, String country, boolean isDefault, AddressType type) {
}
