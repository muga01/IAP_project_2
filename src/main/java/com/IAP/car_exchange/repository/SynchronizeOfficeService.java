package com.IAP.car_exchange.repository;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;

import com.IAP.car_exchange.SynchronizationConfiguration;
import com.IAP.car_exchange.Controller.DataHolders.OfficeData;
import com.IAP.car_exchange.Model.Office;
import lombok.Data;

@Repository
@Data
public class SynchronizeOfficeService {
	final OfficeRepository officeRepository;
	OfficeData offData = new OfficeData();
	
	public SynchronizeOfficeService(OfficeRepository officeRepository) {
		this.officeRepository = officeRepository;	
	}
	
	public List<Office> allOfficesUn(){
		
		return officeRepository.allUnsycedOffices();
	}
	
	
	public ResponseEntity<String> syncOffices(){
		
		
		/*
		 * List<Request> requests = syncronizationService.getAllUnsycedRequests();
		 * if(requests.isEmpty()) { System.out.println("am empty "+requests); }
		 */
		List<Office> offices = allOfficesUn();
		
		if (offices.isEmpty() == false) {
			for(Office o:offices){
				offData.setId(o.getId());
				offData.setType(o.type);
				offData.setCity(o.getCity());
				offData.setAddress(o.getAddress());


				//System.out.println(offData);
				// Now send it
				//String uri = "http://localhost:8080/user";
				RestTemplate restTemplate = new RestTemplate();
				//SynchronizationConfiguration.uriToHq
				
				
				  try { 
					  OfficeData result = restTemplate.postForObject(SynchronizationConfiguration.uriToHqOffice,offData, OfficeData.class);
				  } catch(Exception e) {
				  System.out.println("We are sorry something happened we will try again later!"); 
				  throw e; 
				  }
				 
				
				// If success, change the sync status
				o.setSync(true);
				officeRepository.save(o);
			}
		}

		return ResponseEntity.ok(null);
	}

}
