package com.example.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.model.Employee;
import com.example.repository.UserRepository;

@Service
public class UserService {
	
	@Autowired
	UserRepository ur;
	
	public void insert(Employee employee) {
		ur.save(employee);
	}
	public void delete(int id) {
		ur.deleteById(id);
	}
	public Optional<Employee> getUserById(int id) {
		Optional<Employee> employee = ur.findById(id);
		return employee;
	}
	
	public Employee getUserByEmailAndPassword(String userEmail, String userPassword) {
		Employee employee = ur.getUserByEmailAndPassword(userEmail, userPassword);
		return employee;
	}
	public void updateEmployeeLeave(Employee employee) {
		ur.updateEmployeeLeave(employee.getLeaveDays(), employee.getEmployeeId());
	}

}
