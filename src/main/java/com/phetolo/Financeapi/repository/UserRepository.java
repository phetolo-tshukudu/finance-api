package com.phetolo.Financeapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.phetolo.Financeapi.model.User;

@Repository
public interface UserRepository extends JpaRepository<User,Long>{
	User findByEmail(String emial);
	boolean existsByEmail(String email);
	
}
