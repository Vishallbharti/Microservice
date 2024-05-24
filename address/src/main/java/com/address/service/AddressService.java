package com.address.service;

import java.util.List;
import java.util.Optional;

import com.address.entity.Address;

public interface AddressService {
	
	public List<Address> getAllAddress();
	
	public Optional<Address> getAddressById(int id);
	
	public Address updateAddress(Address address);
	
	public Address createAddress(Address address);
	
	public void deleteAddress(int id);
	

}
