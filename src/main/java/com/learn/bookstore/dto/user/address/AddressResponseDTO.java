package com.learn.bookstore.dto.user.address;

import com.learn.bookstore.models.user.AddressType;
import lombok.Builder;

@Builder
public record AddressResponseDTO(Long addressId, String line1, String line2, String city, String state, String postalCode, String country, boolean isDefault, AddressType type) {
}
