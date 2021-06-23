package com.IAP.car_exchange.repository;

import com.IAP.car_exchange.Model.User;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UserRepository extends JpaRepository<User, Long> {
	@Query("SELECT u FROM User u WHERE u.id = ?1")
	User getUserWithId(Long id);
	
	@Query("SELECT u FROM User u WHERE u.sync != true")
	List<User> getUnsyncedUsers();
}
