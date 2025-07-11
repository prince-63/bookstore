package com.learn.bookstore.services;

import com.learn.bookstore.models.order.CartItem;

import java.util.List;

public interface CartService {

    void addToCart(Long userId, Long bookId, int quantity);

    void removeFromCart(Long userId, Long cartItemId);

    List<CartItem> getUserCart(Long userId);

    void clearCart(Long userId);

}
