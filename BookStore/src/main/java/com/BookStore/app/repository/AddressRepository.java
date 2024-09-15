package com.BookStore.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.BookStore.app.model.Address;

public interface AddressRepository extends JpaRepository<Address, Long> {

}
