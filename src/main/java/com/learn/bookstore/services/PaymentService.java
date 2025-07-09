package com.learn.bookstore.services;

import com.learn.bookstore.models.Payment;

public interface PaymentService {

    Payment processPayment(Long orderId, Payment payment);

    Payment getPaymentByOrderId(Long orderId);

}
