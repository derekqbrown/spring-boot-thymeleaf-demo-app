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
	
}
