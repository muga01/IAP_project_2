package com.IAP.car_exchange.Controller;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.IAP.car_exchange.SynchronizationConfiguration;
import com.IAP.car_exchange.repository.SynchronizeOfficeService;
import com.IAP.car_exchange.repository.SynchronizeUserService;
import com.IAP.car_exchange.repository.SyncronizationService;


@RestController
public class SyncronizationController {
	
	
	@Autowired
	SyncronizationService syncronizationService;
	@Autowired
	SynchronizeUserService synchronizeUserService;
	@Autowired
	SynchronizeOfficeService synchronizeOfficeService;
	
	private static final Logger logger = LoggerFactory.getLogger(SyncronizationController.class);
	private static final DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("HH:mm:ss");

	
	//@Scheduled(fixedDelay=SynchronizationConfiguration.delay)
	public void synchronizeRequests() {
		logger.info("Scheduled Synchronization Task for requests :: Execution Time - {}",dateFormat.format(LocalDateTime.now()));
		try {
			ResponseEntity<String> sync = syncronizationService.tryToSync();
		} catch(Exception ex) {
			logger.error("We have run into an error: we will reconnect shortly");
		}
	}
	
	//@Scheduled(fixedDelay=SynchronizationConfiguration.delay)
	//@GetMapping("added")
	public @ResponseBody void synchronizeUsers() {
		logger.info("Scheduled Synchronization Task for users :: Execution Time - {}",dateFormat.format(LocalDateTime.now()));
		
		  try { ResponseEntity<String> sync = synchronizeUserService.syncUsers(); }
		  catch(Exception ex) {
		  logger.error("We have run into an error: we will reconnect shortly"); 
		  throw ex; 
		  }		
	}
	
	@Scheduled(fixedDelay=SynchronizationConfiguration.delay)
	//@GetMapping("added")
	public @ResponseBody void synchronizeOffices() {
		logger.info("Scheduled Synchronization Task for Offices :: Execution Time - {}",dateFormat.format(LocalDateTime.now()));
		
		  try { ResponseEntity<String> sync = synchronizeOfficeService.syncOffices(); }
		  catch(Exception ex) {
		  logger.error("We have run into an error: we will reconnect shortly"); 
		  throw ex; 
		  }		
	}

}
