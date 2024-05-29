package com.employee.controller;

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

import com.employee.clientService.AddressService;
import com.employee.entity.Address;
import com.employee.entity.Employee;
import com.employee.service.EmployeeService;


@RestController
@RequestMapping("/employees")
public class EmployeeController {
	@Autowired
	private AddressService addressService;
	
	@Autowired
	private EmployeeService employeeServiceImpl;
	
	
	@GetMapping
    public List<Employee> getAllEmployee() {
        return (List<Employee>) this.employeeServiceImpl.getAllEmployees();
    }

    @GetMapping("/{id}")
    public Employee getEmployeeById(@PathVariable int id) {
        return this.employeeServiceImpl.getEmployee(id)
                .orElseThrow();
    }

    @PostMapping("/createEmployee")
    public Employee createAddress(@RequestBody Employee employee) {
        return this.employeeServiceImpl.createEmployee(employee);
    }

    @PutMapping("/updateEmployee")
    public Employee updateAddress( @RequestBody Employee employeeDetails) {
        return this.employeeServiceImpl.updateEmployee(employeeDetails);
    }

    @DeleteMapping("/{id}")
    public void deleteAddress(@PathVariable int id) {
    	this.employeeServiceImpl.deleteEmployee(id);
    }
	
    // addres service
    @GetMapping("/address")
    public List<Address> getAllAddress(){
    	return this.addressService.getAddress();
    }

}
