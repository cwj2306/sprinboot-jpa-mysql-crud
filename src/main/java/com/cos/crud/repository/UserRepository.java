package com.cos.crud.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.cos.crud.model.User;


//CRUD 구현해주는 클래스 JpaRepository
public interface UserRepository extends JpaRepository<User, Integer>{

	User findByEmailAndPassword(String email, String password);
}
