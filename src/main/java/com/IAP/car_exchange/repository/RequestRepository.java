package com.IAP.car_exchange.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.IAP.car_exchange.Model.Request;

public interface RequestRepository extends JpaRepository<Request,Long> {
	@Query(value = "SELECT * FROM requests r ORDER BY r.request_id DESC LIMIT 1", nativeQuery = true)
	Request getLastRecord();
	
	@Query("SELECT r FROM Request r WHERE r.requestId=?1 AND r.assignedCar != null")
	Request getUnassignedCar(Long requestId);
	
	@Query("SELECT r FROM Request r WHERE r.status != true")
	List<Request> getAllUnyced();
}
