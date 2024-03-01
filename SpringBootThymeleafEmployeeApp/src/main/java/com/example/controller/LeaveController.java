package com.example.controller;

import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.example.model.Employee;
import com.example.model.LeaveRequest;
import com.example.service.LeaveService;

@Controller
@RequestMapping("/")
@SessionAttributes("requestList")
public class LeaveController {
	@Autowired
	LeaveService ls;
	
	@GetMapping("/")
	public String index(Model m) {
		return "index";
	}
	@GetMapping("/index")
	public String indexPage(Model m) {
		return "index";
	}
	@GetMapping("/home")
	public String homePage(Model m) {
		return "home";
	}
	@GetMapping("/submit")
	public String showRequestForm(Model m) {
		m.addAttribute("request", new LeaveRequest());
		return "submit";
	}
	
	@GetMapping("/approve")
	public String showApproveForm(Model m, @SessionAttribute int userId) {
		ArrayList<LeaveRequest> requestList =ls.getApproveRequests(userId);
		m.addAttribute("requestList", requestList);
		return "approve";
	}
	
	@GetMapping("/view")
	public String viewRequests(Model m, @SessionAttribute int userId) {
		ArrayList<LeaveRequest> requestList =ls.getUserRequests(userId);
		m.addAttribute("requestList", requestList);
		return "view";
	}
		
	@GetMapping("/deleteRequest")
	public String deleteRequest(@RequestParam("id") int id) {
		ls.delete(id);
		return "redirect:view";
	}
	
	@PostMapping("/submitRequest")
	public String submitRequest(@SessionAttribute("employee") Employee employee, @ModelAttribute("request") LeaveRequest leaveRequest) {
		
		leaveRequest.setEmployeeId(employee.getEmployeeId());
		leaveRequest.setStatus("PENDING");
		ls.insert(leaveRequest);;
		return "redirect:view";
	}
	
}
