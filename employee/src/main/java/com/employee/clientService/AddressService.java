package com.employee.clientService;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import com.employee.entity.Address;

@FeignClient(name = "address")
public interface AddressService {
	
	@GetMapping("/api/address")
	public List<Address> getAddress();	
}
