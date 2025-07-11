package com.learn.bookstore.repositories;

import com.learn.bookstore.models.user.Address;
import com.learn.bookstore.models.user.AddressType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AddressRepository extends JpaRepository<Address, Long> {

    List<Address> findByUserId(Long userId);

    List<Address> findByUserIdAndType(Long userId, AddressType type);

    List<Address> findAllByUserId(Long userId);
}

