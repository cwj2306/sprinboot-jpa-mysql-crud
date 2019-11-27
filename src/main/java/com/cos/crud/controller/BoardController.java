package com.cos.crud.controller;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cos.crud.model.Board;
import com.cos.crud.model.User;
import com.cos.crud.repository.BoardRepository;
import com.cos.crud.utils.Script;

@Controller
public class BoardController {
	
	@Autowired
	private BoardRepository mRepo;
	
	@GetMapping("/board/list")
	public String boardList(Model model) {
		List<Board> boards = mRepo.findAll();
		model.addAttribute("boards", boards);
		return "board/list";
	}

	@GetMapping("/board/detail/{id}")
	public String boardDetail(@PathVariable int id, Model model) {
		Optional<Board> board = mRepo.findById(id);
		model.addAttribute("board", board.get());
		return "board/detail";
	}

	@GetMapping("/board/writeForm")
	public String boardWriteForm(HttpSession session) {
		User user = (User)session.getAttribute("user");
		if(user != null) {
			return "board/writeForm";
		}else {
			return "user/loginForm";
		}
	}
	
	@PostMapping("/board/write")
	public @ResponseBody String boardWrite(Board board, HttpSession session) {
		// 인터셉터 처리 AOP
		User user = (User)session.getAttribute("user");
		if(user != null) {
			board.setUser(user); //세션에서 유저정보 들고와서 넣기
			mRepo.save(board);
			return "redirect:/board/list";
		}else {
			return "redirect:/user/loginForm";
		}
		
	}
	
	@DeleteMapping("/board/delete/{id}")
	public String boardDelete(@PathVariable int id) {
		//세션 있어야 함
		
		mRepo.deleteById(id);
		return "redirect:/board/list";
	}
	
	@GetMapping("/board/updateForm/{id}")
	public String boardUpdateForm(@PathVariable int id, Model model) {
		//세션 있어야 함
		
		Optional<Board> board = mRepo.findById(id);
		model.addAttribute("board", board.get());
		return "board/updateForm";
	}
	
	@PutMapping("/board/update/{id}")
	public String boardUpdate(@PathVariable int id, Board board, HttpSession session) {
		//세션 있어야 함

		User user = (User)session.getAttribute("user");
		if(user != null) {
			board.setUser(user); //세션에서 유저정보 들고와서 넣기
			board.setId(id);
			mRepo.save(board);
			return "redirect:/board/list";
		}else {
			return "redirect:/user/loginForm";
		}
	}
	
}