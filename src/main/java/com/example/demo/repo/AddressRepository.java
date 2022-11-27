package com.example.demo.repo;


import com.example.demo.models.Address;
import org.springframework.data.repository.CrudRepository;

public interface AddressRepository extends CrudRepository<Address, Long> {
    Address findByStreet(String street);
}
