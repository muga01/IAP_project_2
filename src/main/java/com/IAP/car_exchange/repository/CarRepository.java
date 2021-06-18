package com.IAP.car_exchange.repository;

import com.IAP.car_exchange.Model.Car;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CarRepository extends JpaRepository<Car, String> {
}
