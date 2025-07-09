package com.learn.bookstore.services;

import com.learn.bookstore.models.Discount;

import java.util.List;
import java.util.Optional;

public interface DiscountService {

    Discount createDiscount(Discount discount);

    Optional<Discount> getActiveDiscountByCode(String code);

    List<Discount> getDiscountsByBook(Long bookId);

}
