package com.address.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.address.entity.Address;
import com.address.service.AddressService;

@RestController
@RequestMapping("/api/address")
public class AddressController {
	
	@Autowired
	private AddressService addressService;
	
	
	@GetMapping
    public List<Address> getAllAddresses() {
        return addressService.getAllAddress();
    }

    @GetMapping("/{id}")
    public Address getAddressById(@PathVariable int id) {
        return addressService.getAddressById(id)
                .orElseThrow();
    }

    @PostMapping("/createAddress")
    public Address createAddress(@RequestBody Address address) {
        return addressService.createAddress(address);
    }

    @PutMapping("/updateAddress")
    public Address updateAddress( @RequestBody Address addressDetails) {
        return addressService.updateAddress(addressDetails);
    }

    @DeleteMapping("/{id}")
    public void deleteAddress(@PathVariable int id) {
        this.addressService.deleteAddress(id);
    }

}
