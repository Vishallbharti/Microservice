package com.address.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.address.entity.Address;
import com.address.repository.AddressRepository;

@Service
public class AddressService {
	

	@Autowired
	private AddressRepository addressRepository;

	
	public List<Address> getAllAddress() {
		List<Address> addressList = null;
		addressList = (List<Address>) this.addressRepository.findAll();
		return addressList;
	}


	
	public Address updateAddress(Address address) {
		Address updateAddress = addressRepository.findById(address.getId())
				.orElseThrow();
         
		updateAddress.setStreet(address.getStreet());
		updateAddress.setFlat(address.getFlat());
		updateAddress.setState(address.getState());
		updateAddress.setZip(address.getZip());
		
		return this.addressRepository.save(updateAddress);
	}

	
	public Address createAddress(Address address) {
		return this.addressRepository.save(address);
	}

	
	public Optional<Address> getAddressById(int id) {
		Optional<Address> address = null;
		address = this.addressRepository.findById(id);
		return address;
	}


	
	public void deleteAddress(int id) {
		 Address address = addressRepository.findById(id)
	                .orElseThrow();

	        addressRepository.delete(address);
	}
}
