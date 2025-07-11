package com.learn.bookstore.services;

import com.learn.bookstore.models.order.Order;

import java.util.List;

public interface OrderService {

    Order placeOrder(Long userId);

    Order getOrderById(Long orderId);

    List<Order> getUserOrders(Long userId);

    void cancelOrder(Long orderId);

}
