package com.example.service;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.model.LeaveRequest;
import com.example.repository.LeaveRepository;

@Service
public class LeaveService {
	
	@Autowired
	LeaveRepository lr;

	public void delete(int id) {
		lr.deleteById(id);
		
	}

	public void insert(LeaveRequest leaveRequest) {
		lr.save(leaveRequest);
	}

	public void update(int requestId, String status) {
		lr.updateLeaveRequest(status, requestId);
	}

	public Optional<LeaveRequest> getById(int id) {
		return lr.findById(id);
	}

	public ArrayList<LeaveRequest> getUserRequests(int id) {
		ArrayList<LeaveRequest> li =lr.getRequestsByUserId(id);
		return li;
		
	}
	
	public ArrayList<LeaveRequest> getApproveRequests(int id) {
		ArrayList<LeaveRequest> li =lr.getApproveRequests(id);
		return li;
		
	}

}
