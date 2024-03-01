package com.example.repository;

import java.util.ArrayList;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.example.model.LeaveRequest;

@Repository
public interface LeaveRepository extends JpaRepository<LeaveRequest, Integer>{
	@Query("select r from LeaveRequest r where r.employeeId=:employeeId")
	ArrayList<LeaveRequest> getRequestsByUserId(@Param("employeeId") int id);

	@Query("select r from LeaveRequest r where r.employeeId!=:employeeId and r.status = 'PENDING'")
	ArrayList<LeaveRequest> getApproveRequests(@Param("employeeId") int id);

	@Modifying
	@Transactional
	@Query("update from LeaveRequest r set r.status=:status where requestId=:requestId")
	void updateLeaveRequest(@Param("status") String status, @Param("requestId") int requestId);
	
}
