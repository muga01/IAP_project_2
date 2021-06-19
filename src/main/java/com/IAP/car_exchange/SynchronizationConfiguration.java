package com.IAP.car_exchange;

public class SynchronizationConfiguration {
	
	// connection to HQ
	public static final String uriToHq = "http://s-vm.northeurope.cloudapp.azure.com:8081/request";
	
	// connection from HQ
	public static final String  uriFromHq = "details";
	
	// delay sync timer in millisecond
	public static final int delay = 5000;

}
