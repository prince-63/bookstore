package com.learn.bookstore.controllers;

import com.learn.bookstore.dto.ResponseDTO;
import com.learn.bookstore.dto.AddressRequestDTO;
import com.learn.bookstore.dto.AddressResponseDTO;
import com.learn.bookstore.mappers.AddressMapper;
import com.learn.bookstore.models.Address;
import com.learn.bookstore.services.AddressService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/user")
@AllArgsConstructor
public class AddressController {

    private AddressService addressService;

    @PostMapping("/address")
    public ResponseEntity<ResponseDTO<AddressResponseDTO>> addAddress(Authentication authentication, @RequestBody AddressRequestDTO address) {
        Address savedAddress = addressService.addAddress(authentication.getName(), address);
        ResponseDTO<AddressResponseDTO> response = new ResponseDTO<>();
        response.setData(AddressMapper.toDTO(savedAddress));
        response.setSuccess(true);
        response.setMessage("Address saved successfully");
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/address")
    public ResponseEntity<ResponseDTO<List<AddressResponseDTO>>> getAddressesByUser(Authentication authentication) {
        List<Address> savedAddress = addressService.getAddressesByUser(authentication.getName());
        ResponseDTO<List<AddressResponseDTO>> response = new ResponseDTO<>();
        response.setData(savedAddress.stream().map(AddressMapper::toDTO).collect(Collectors.toList()));
        response.setSuccess(true);
        response.setMessage("Address retrieved successfully");
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PatchMapping("/address/{id}")
    public ResponseEntity<ResponseDTO<AddressResponseDTO>> updateAddress(Authentication authentication, @PathVariable Long id, @RequestBody AddressRequestDTO address) {
        Address updatedAddress = addressService.updateAddress(authentication.getName(), id, address);
        ResponseDTO<AddressResponseDTO> response = new ResponseDTO<>();
        response.setData(AddressMapper.toDTO(updatedAddress));
        response.setSuccess(true);
        response.setMessage("Address updated successfully");
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @DeleteMapping("/address/{id}")
    public ResponseEntity<ResponseDTO<Boolean>> deleteAddress(Authentication authentication, @PathVariable Long id) {
        addressService.deleteAddress(authentication.getName(), id);
        ResponseDTO<Boolean> response = new ResponseDTO<>();
        response.setData(Boolean.TRUE);
        response.setSuccess(true);
        response.setMessage("Address deleted successfully");
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}
