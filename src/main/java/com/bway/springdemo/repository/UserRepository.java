package com.bway.springdemo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.bway.springdemo.model.User;

public interface UserRepository extends JpaRepository<User, Integer>{
	
	//Spring Data JPA convention
	User findByEmailAndPassword(String eml, String psw);
	
	//SQL Native
	@Query(value = "select * from user_tbl where email = :eml and password = :psw ", nativeQuery = true)
	User isExist(@Param("eml") String eml, @Param("psw") String psw);
	
	//HQL
	@Query("FROM User where email = :eml and password = :psw")
	User findUser(String eml, String psw);

}
