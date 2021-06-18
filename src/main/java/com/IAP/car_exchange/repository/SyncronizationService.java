package com.IAP.car_exchange.repository;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;

import com.IAP.car_exchange.SynchronizationConfiguration;
import com.IAP.car_exchange.Controller.DataHolders.RequestData;
import com.IAP.car_exchange.Model.Request;

import lombok.Data;

@Repository
@Data
public class SyncronizationService {
	final RequestRepository requestRepository;
	
	RequestData requestData = new RequestData();
	
	  public SyncronizationService(RequestRepository requestRepository) {
	  this.requestRepository = requestRepository; }
	 
	
	public List<Request> getAllUnsycedRequests(){
		
		return requestRepository.getAllUnyced();
	}
	
	public ResponseEntity<String> tryToSync(){
		
		
		/*
		 * List<Request> requests = syncronizationService.getAllUnsycedRequests();
		 * if(requests.isEmpty()) { System.out.println("am empty "+requests); }
		 */
		List<Request> requests = getAllUnsycedRequests();
		if (requests.isEmpty() == false) {
			for(Request r:requests) {
				//System.out.println(r.getRequestorId());
				
				// Prepare the data to be sent to HQ for this request

				requestData.setRequestorId(r.getRequestorId().getId());
				requestData.setBranchId(r.getBranchId());
				requestData.setCarModel(r.getCarModel());
				requestData.setVehiclePreffered(r.getVehiclePreffered());
				requestData.setRequestDate(r.getRequestDate());
				requestData.setRequestId(r.getRequestId());
				
				// Now send it
				//String uri = "http://localhost:8080/request";
				RestTemplate restTemplate = new RestTemplate();
				try {
					RequestData result = restTemplate.postForObject(SynchronizationConfiguration.uriToHq,requestData, RequestData.class);
				} catch(Exception e) {	
					System.out.println("We are sorry something happened we will try again later!");
					//System.out.println(requestData.getRequestorId());
					//e.printStackTrace();
					throw e;	
				}
				
				// If success, change the sync status
				r.setStatus(true);
				requestRepository.save(r);
			}
		}

		return ResponseEntity.ok(null);
	}

}
