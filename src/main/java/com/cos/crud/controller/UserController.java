package com.cos.crud.controller;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cos.crud.model.User;
import com.cos.crud.repository.UserRepository;
import com.cos.crud.utils.Script;

@Controller
public class UserController {
	
	@Autowired
	private UserRepository mRepo;

	@PostMapping("/user/login")
	public @ResponseBody String userLogin(User user, HttpSession session) {
		User u = mRepo.findByEmailAndPassword(user.getEmail(), user.getPassword());
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
	public String userJoin(User user) {
		mRepo.save(user);
		return "redirect:/";
	}
	
	@GetMapping("/user/joinForm")
	public String userJoinForm() {
		return "user/joinForm";
	}
}