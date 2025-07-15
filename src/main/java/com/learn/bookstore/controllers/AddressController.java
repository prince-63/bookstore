package com.learn.bookstore.controllers;

import com.learn.bookstore.constants.UserEndPointsConstants;
import com.learn.bookstore.dto.AddressRequestDTO;
import com.learn.bookstore.dto.AddressResponseDTO;
import com.learn.bookstore.dto.ErrorResponseDTO;
import com.learn.bookstore.dto.ResponseDTO;
import com.learn.bookstore.mappers.AddressMapper;
import com.learn.bookstore.models.Address;
import com.learn.bookstore.services.AddressService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@Tag(
        name = "User Address APIs",
        description = "Endpoints to manage addresses of authenticated users. Includes create, read, update, and delete operations."
)
@RestController
@AllArgsConstructor
public class AddressController {

    private AddressService addressService;

    @Operation(
            summary = "Create new address",
            description = "Creates a new address for the authenticated user and returns the saved address."
    )
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Address created successfully"),
            @ApiResponse(responseCode = "401", description = "Unauthorized - JWT missing or invalid"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error",
                    content = @Content(schema = @Schema(implementation = ErrorResponseDTO.class)))
    })
    @PostMapping(UserEndPointsConstants.ADD_USER_ADDRESS)
    public ResponseEntity<ResponseDTO<AddressResponseDTO>> addAddress(Authentication authentication, @RequestBody AddressRequestDTO address) {
        Address savedAddress = addressService.addAddress(authentication.getName(), address);
        ResponseDTO<AddressResponseDTO> response = new ResponseDTO<>();
        response.setData(AddressMapper.toDTO(savedAddress));
        response.setSuccess(true);
        response.setMessage("Address saved successfully");
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @Operation(
            summary = "Get user addresses",
            description = "Fetches all addresses associated with the authenticated user."
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Addresses retrieved successfully"),
            @ApiResponse(responseCode = "401", description = "Unauthorized - JWT missing or invalid"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error",
                    content = @Content(schema = @Schema(implementation = ErrorResponseDTO.class)))
    })
    @GetMapping(UserEndPointsConstants.GET_USER_ADDRESS)
    public ResponseEntity<ResponseDTO<List<AddressResponseDTO>>> getAddressesByUser(Authentication authentication) {
        List<Address> savedAddress = addressService.getAddressesByUser(authentication.getName());
        ResponseDTO<List<AddressResponseDTO>> response = new ResponseDTO<>();
        response.setData(savedAddress.stream().map(AddressMapper::toDTO).collect(Collectors.toList()));
        response.setSuccess(true);
        response.setMessage("Address retrieved successfully");
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @Operation(
            summary = "Update address by ID",
            description = "Updates an existing address belonging to the authenticated user using the provided address ID."
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Address updated successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid address ID or request body"),
            @ApiResponse(responseCode = "401", description = "Unauthorized - JWT missing or invalid"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error",
                    content = @Content(schema = @Schema(implementation = ErrorResponseDTO.class)))
    })
    @PatchMapping(UserEndPointsConstants.UPDATE_ADDRESS_BY_ID)
    public ResponseEntity<ResponseDTO<AddressResponseDTO>> updateAddress(Authentication authentication, @PathVariable Long id, @RequestBody AddressRequestDTO address) {
        Address updatedAddress = addressService.updateAddress(authentication.getName(), id, address);
        ResponseDTO<AddressResponseDTO> response = new ResponseDTO<>();
        response.setData(AddressMapper.toDTO(updatedAddress));
        response.setSuccess(true);
        response.setMessage("Address updated successfully");
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @Operation(
            summary = "Delete address by ID",
            description = "Deletes an address belonging to the authenticated user using the address ID."
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Address deleted successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid address ID"),
            @ApiResponse(responseCode = "401", description = "Unauthorized - JWT missing or invalid"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error",
                    content = @Content(schema = @Schema(implementation = ErrorResponseDTO.class)))
    })
    @DeleteMapping(UserEndPointsConstants.DELETE_ADDRESS_BY_ID)
    public ResponseEntity<ResponseDTO<Boolean>> deleteAddress(Authentication authentication, @PathVariable Long id) {
        addressService.deleteAddress(authentication.getName(), id);
        ResponseDTO<Boolean> response = new ResponseDTO<>();
        response.setData(Boolean.TRUE);
        response.setSuccess(true);
        response.setMessage("Address deleted successfully");
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}
