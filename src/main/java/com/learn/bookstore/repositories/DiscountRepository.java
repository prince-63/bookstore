package com.learn.bookstore.repositories;

import com.learn.bookstore.models.Discount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DiscountRepository extends JpaRepository<Discount, Long> {

    Optional<Discount> findByCodeAndActiveIsTrue(String code);

    List<Discount> findByBookId(Long bookId);

}
