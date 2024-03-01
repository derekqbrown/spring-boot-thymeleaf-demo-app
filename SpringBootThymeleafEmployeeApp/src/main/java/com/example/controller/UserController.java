package com.example.controller;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.example.model.Employee;
import com.example.model.LeaveRequest;
import com.example.service.LeaveService;
import com.example.service.UserService;

@Controller
@RequestMapping("/")
@SessionAttributes({"employee", "userId", "userRole", "mismatch"})
public class UserController {
	@Autowired
	UserService us;
	@Autowired
	LeaveService ls;
	
	@GetMapping("/login")
	public String loginPage(Model m) {
		m.addAttribute("employee", new Employee());
		return "login";
	}
	@GetMapping("/loginUser")
	public String loginUser(@ModelAttribute Employee employee, Model m) {
		String loginRole = employee.getRole();
		m.addAttribute("userRole", loginRole);
		employee = (Employee) us.getUserByEmailAndPassword(employee.getEmail(),employee.getPassword());
		m.addAttribute("employee", employee);
		if (employee.getRole() != loginRole && loginRole.equalsIgnoreCase("Manager")) {
			m.addAttribute("mismatch", "True");
		}else {
			m.addAttribute("mismatch", "False");
		}
		m.addAttribute("userId", employee.getEmployeeId());
		return "redirect:home";
	}
	
	@RequestMapping("/register")
	public String registerForm(Model m) {
		m.addAttribute("employee", new Employee());
		return "register";
	}
	@RequestMapping("/registerUser")
	public String registerUser(@ModelAttribute Employee employee) {
		employee.setLeaveDays(20);
		us.insert(employee);
		return "redirect:index";
	}
	@RequestMapping("/profile")
	public String showUserProfile(Model m) {
		return "profile";
	}
	@RequestMapping("/approval")
	public String approveLeave(@RequestParam("requestId") int requestId, @RequestParam("employeeId") int employeeId, @RequestParam("status") String status) {
		Employee employee = us.getUserById(employeeId).get();
		us.updateEmployeeLeave(employee);
		
		LeaveRequest leaveRequest = ls.getById(requestId).get();
		LocalDate start = leaveRequest.getStart().toLocalDate();
		LocalDate end = leaveRequest.getEnd().toLocalDate();
		int days = (int) ChronoUnit.DAYS.between(start, end);
		
		if (days > employee.getLeaveDays() || days < 0) {
			ls.update(requestId, "DENIED");
		}else {
			ls.update(requestId, status);
			if (status.contains("A")) {
				employee.setLeaveDays(employee.getLeaveDays()-days);
				us.updateEmployeeLeave(employee);
			}
		}
			
		return "redirect:approve";
	}
	
}
