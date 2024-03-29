package com.cos.crud.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cos.crud.model.User;
import com.cos.crud.service.UserService;
import com.cos.crud.utils.Script;

@Controller
public class UserController {
	
	@Autowired
	private UserService mService;

	@PostMapping("/user/login")
	public @ResponseBody String userLogin(User user, HttpSession session) {
		User u = mService.userLogin(user);
		if(u != null) {
			 session.setAttribute("user", u);
			 return Script.href("/");
		}else {
			return Script.back("로그인 실패");
		}
		
	}
	
	@GetMapping("/user/logout")
	public String userLogout(HttpSession session) {
		session.invalidate();
		return "redirect:/";
	}
	
	@GetMapping("/user/loginForm")
	public String userLoginForm() {
		return "user/loginForm";
	}
	
	@PostMapping("/user/join")
	public @ResponseBody String userJoin(User user) {
		int result = mService.userJoin(user);
		if(result == 1) {
			return Script.href("/");
		}else {
			return Script.back("회원가입 실패");
		}
		
	}
	
	@GetMapping("/user/joinForm")
	public String userJoinForm() {
		return "user/joinForm";
	}
	
	
	// 유저 정보 10번 출력해주는 예제
	@GetMapping("/user/{id}")
	public @ResponseBody List<User> getUser(@PathVariable int id){
		return mService.getUser(id);
	}
}
