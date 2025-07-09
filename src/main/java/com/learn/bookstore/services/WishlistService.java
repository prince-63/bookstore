package com.learn.bookstore.services;

import com.learn.bookstore.models.Wishlist;

import java.util.List;

public interface WishlistService {

    void addToWishlist(Long userId, Long bookId);

    void removeFromWishlist(Long userId, Long bookId);

    List<Wishlist> getWishlist(Long userId);

}

