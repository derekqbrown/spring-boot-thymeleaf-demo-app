package com.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.example.model.Employee;

@Repository
public interface UserRepository extends JpaRepository<Employee, Integer>{
	
	@Query("select u from Employee u where u.password=:password and u.email=:email")
	Employee getUserByEmailAndPassword(@Param("email") String email, @Param("password") String password);

	@Modifying
	@Transactional
	@Query("update from Employee set leaveDays=:leaveDays where eid=:eid")
	void updateEmployeeLeave(@Param("leaveDays") int days, @Param("eid") int eid);
}
