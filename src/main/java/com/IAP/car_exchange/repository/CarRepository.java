package com.IAP.car_exchange.repository;

import com.IAP.car_exchange.Model.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface CarRepository extends JpaRepository<Car, String> {
	@Query("SELECT c from Car c WHERE c.plateNumber=?1")
	Car getCarByPlateNumber(String plateNumber);
}
