package com.IAP.car_exchange.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.IAP.car_exchange.SynchronizationConfiguration;
import com.IAP.car_exchange.Controller.DataHolders.Response;
import com.IAP.car_exchange.repository.Querries;

@RestController
public class ResponseController {
	@Autowired
	Querries DataAccess;
	
	// create this response as received from HQ
	@PostMapping(SynchronizationConfiguration.uriFromHq)
	public @ResponseBody
	ResponseEntity<String> setDetails(@RequestBody Response responseData){

		DataAccess.carRequestDetails(
				responseData.getWorkerId(),
				responseData.getPlateNumber(),
				responseData.getLicenseNumber(),
				responseData.getModel(),
				responseData.getType(),
				responseData.getVinNumber(),
				false,
				responseData.getRequestId(),
				responseData.getRequestStatus(),
				responseData.getApprovedBy(),
				responseData.getApprovedDate()
				);
		
		return ResponseEntity.ok(null);
		
	}	

}
