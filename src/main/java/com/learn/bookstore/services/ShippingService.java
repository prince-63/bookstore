package com.learn.bookstore.services;

import com.learn.bookstore.models.Shipping;

import java.util.Optional;

public interface ShippingService {

    Shipping createShippingForOrder(Long orderId, Shipping shipping);

    Optional<Shipping> trackShipment(String trackingNumber);

}
