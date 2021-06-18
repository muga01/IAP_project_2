package com.IAP.car_exchange.repository;

import com.IAP.car_exchange.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
