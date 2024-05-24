package com.employee.service;


import java.util.*;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.employee.entity.Employee;
import com.employee.repository.EmployeeRepository;

@Service
public class EmployeeService {

	

	@Autowired
	private EmployeeRepository employeeRepository;

	
	public Iterable<Employee> getAllEmployees() {
		// TODO Auto-generated method stub
		return employeeRepository.findAll();
	}
	
	public Employee createEmployee(Employee employee) {
		// TODO Auto-generated method stub
		return this.employeeRepository.save(employee);
	}


	
	public Employee updateEmployee(Employee employee) {
		// TODO Auto-generated method stub
		Employee updateEmployee = this.employeeRepository.findById(employee.getId())
				.orElseThrow();
		updateEmployee.setDepartment(employee.getDepartment());
		updateEmployee.setEmail(employee.getEmail());
		updateEmployee.setName(employee.getName());
		updateEmployee.setSalary(employee.getSalary());
		
		return this.employeeRepository.save(updateEmployee);
	}

	
	public void deleteEmployee(int id) {
		// TODO Auto-generated method stub
		
		Employee employee = this.employeeRepository.findById(id).orElseThrow();
		
		this.employeeRepository.delete(employee);
		
	}

	
	public Optional<Employee> getEmployee(int id) {
		// TODO Auto-generated method stub
		
		return this.employeeRepository.findById(id);
	}

}
