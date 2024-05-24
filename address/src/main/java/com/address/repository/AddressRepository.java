package com.address.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.address.entity.Address;

@Repository
public interface AddressRepository extends CrudRepository<Address, Integer>{

}
