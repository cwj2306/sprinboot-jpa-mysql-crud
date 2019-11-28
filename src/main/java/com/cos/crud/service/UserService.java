package com.cos.crud.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cos.crud.model.User;
import com.cos.crud.repository.UserRepository;

@Service
public class UserService {
	
	@Autowired
	private UserRepository mRepo;
	
	public User userLogin(User user) {
		User u = mRepo.findByEmailAndPassword(user.getEmail(), user.getPassword());
		return u;
	}
	
	
	public int userJoin(User user) {
		try {
			mRepo.save(user);
			return 1;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return -1;
	}
	
	
	// 유저 정보 10번 출력해주는 예제
	public List<User> getUser(int id){
		List<User> users = new ArrayList<>();
		for (int i = 0; i < 10; i++) {
			users.add(mRepo.findById(id).get());
		}
		return users;
	}
	
	
}
