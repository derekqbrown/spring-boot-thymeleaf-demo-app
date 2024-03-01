package com.example.model;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="`leave_requests`")
@Getter
@Setter
@NoArgsConstructor
public class LeaveRequest {
	@Id
	@Column(name="`rid`")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int requestId;
	@Column(name="`employee_id`")
	private int employeeId;
	@Column(name="`reason`")
	private String reason;
	@Column(name="`start`")
	private Date start;
	@Column(name="`end`")
	private Date end;
	@Column(name="`status`")
	private String status;
	
//	public int getRequestId() {
//		return requestId;
//	}
//	public void setRequestId(int requestId) {
//		this.requestId = requestId;
//	}
//	public int getEmployeeId() {
//		return employeeId;
//	}
//	public void setEmployeeId(int employeeId) {
//		this.employeeId = employeeId;
//	}
//	public String getReason() {
//		return reason;
//	}
//	public void setReason(String reason) {
//		this.reason = reason;
//	}
//	public Date getStart() {
//		return start;
//	}
//	public void setStart(Date start) {
//		this.start = start;
//	}
//	public Date getEnd() {
//		return end;
//	}
//	public void setEnd(Date end) {
//		this.end = end;
//	}
//	public String getStatus() {
//		return status;
//	}
//	public void setStatus(String status) {
//		this.status = status;
//	}
	
	
}
