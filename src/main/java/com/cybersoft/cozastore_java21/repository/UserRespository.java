package com.cybersoft.cozastore_java21.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cybersoft.cozastore_java21.entity.UserEntity;

@Repository
public interface UserRespository extends JpaRepository<UserEntity, Integer>{
	
//	@Query("from users where email = ?1")
//	List<UserEntity> getUserByEmail(String email);
	// dùng cách trên hay dưới đều được
	List<UserEntity> findByEmail(String email);
}
