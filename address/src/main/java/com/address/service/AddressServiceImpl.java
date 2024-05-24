package com.address.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.address.entity.Address;
import com.address.repository.AddressRepository;


@Service
public class AddressServiceImpl implements AddressService{
	
	@Autowired
	private AddressRepository addressRepository;

	@Override
	public List<Address> getAllAddress() {
		// TODO Auto-generated method stub
		List<Address> addressList = new ArrayList<>();
		addressList = (List<Address>) this.addressRepository.findAll();
		return addressList;
	}


	@Override
	public Address updateAddress(Address address) {
		// TODO Auto-generated method stub
		Address updateAddress = addressRepository.findById(address.getId())
				.orElseThrow();
         
		updateAddress.setStreet(address.getStreet());
		updateAddress.setFlat(address.getFlat());
		updateAddress.setState(address.getState());
		updateAddress.setZip(address.getZip());
		
		return this.addressRepository.save(updateAddress);
	}

	@Override
	public Address createAddress(Address address) {
		// TODO Auto-generated method stub
		return this.addressRepository.save(address);
	}

	@Override
	public Optional<Address> getAddressById(int id) {
		Optional<Address> address = null;
		// TODO Auto-generated method stub
		
		address = this.addressRepository.findById(id);
		return address;
	}


	@Override
	public void deleteAddress(int id) {
		// TODO Auto-generated method stub
		 Address address = addressRepository.findById(id)
	                .orElseThrow();

	        addressRepository.delete(address);
	}
	
}
