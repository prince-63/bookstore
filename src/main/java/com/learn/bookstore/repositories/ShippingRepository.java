package com.learn.bookstore.repositories;

import com.learn.bookstore.models.Shipping;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ShippingRepository extends JpaRepository<Shipping, Long> {

    Optional<Shipping> findByTrackingNumber(String trackingNumber);

}

