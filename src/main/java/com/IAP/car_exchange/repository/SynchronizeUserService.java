package com.IAP.car_exchange.repository;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;

import com.IAP.car_exchange.SynchronizationConfiguration;
import com.IAP.car_exchange.Controller.DataHolders.UserData;
import com.IAP.car_exchange.Model.User;

import lombok.Data;

@Repository
@Data
public class SynchronizeUserService {
	
	final UserRepository userRepository;
	
	UserData userData = new UserData();
	
	public SynchronizeUserService(UserRepository userRepository) {
		this.userRepository = userRepository;
	}
	
	
	public List<User> getUnsynced(){
		
		return userRepository.getUnsyncedUsers();
	}
	
	
	

	public ResponseEntity<String> syncUsers(){
		
		
		/*
		 * List<Request> requests = syncronizationService.getAllUnsycedRequests();
		 * if(requests.isEmpty()) { System.out.println("am empty "+requests); }
		 */
		List<User> users = getUnsynced();
		
		if (users.isEmpty() == false) {
			for(User u:users){
				//System.out.println(r.getRequestorId());
				
				// Prepare the data to be sent to HQ for this request
				userData.setId(u.getId());
				userData.setFirstName(u.getFirstName());
				userData.setMiddleName(u.getMiddleName());
				userData.setSurname(u.getSurname());
				userData.setPesel(u.getPesel());
				userData.setGender(u.getGender());
				userData.setBirthDate(u.getBirthDate().toString());
				userData.setRoleId(u.getRoleId().getId());
				userData.setOfficeId(u.getOfficeId().getId());

				
				// Now send it
				//String uri = "http://localhost:8080/user";
				RestTemplate restTemplate = new RestTemplate();
				//SynchronizationConfiguration.uriToHq
				
				
				  try { 
					  UserData result = restTemplate.postForObject(SynchronizationConfiguration.uriToHqUser,userData, UserData.class);
				  } catch(Exception e) {
				  System.out.println("We are sorry something happened we will try again later!"); 
				  throw e; 
				  }
				 
				
				// If success, change the sync status
				u.setSync(true);
				userRepository.save(u);
			}
		}

		return ResponseEntity.ok(null);
	}
	
	

}
