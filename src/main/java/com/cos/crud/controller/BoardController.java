package com.cos.crud.controller;

import java.util.List;

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
import com.cos.crud.service.BoardService;
import com.cos.crud.utils.Script;

@Controller
public class BoardController {
	
	@Autowired
	private BoardService mService;
	
	// 트랜잭션 테스트
	@GetMapping("/ict/{id}")
	public @ResponseBody String increaseCountAndTimeUpdate(@PathVariable int id) {
		mService.increadCountAndTimeUpdate(id);
		return "테스트 완료";
	}
	
	@GetMapping("/board/list")
	public String boardList(Model model) {
		List<Board> boards = mService.boardList();
		model.addAttribute("boards", boards);
		return "board/list";
	}

	@GetMapping("/board/detail/{id}")
	public String boardDetail(@PathVariable int id, Model model) {
		Board board = mService.boardDetail(id);
		model.addAttribute("board", board);
		return "board/detail";
	}

	@GetMapping("/board/writeForm")
	public String boardWriteForm(HttpSession session) {
		// 인터셉터 처리
		return "board/writeForm";
	}
	
	@PostMapping("/board/write")
	public @ResponseBody String boardWrite(Board board, HttpSession session) {
		// 인터셉터 처리
		int result = mService.boardWrite(board, session);
		if(result == 1) {
			return Script.href("/board/list");
		}else {
			return Script.back("글쓰기 실패");
		}
	}
	
	@DeleteMapping("/board/delete/{id}")
	public @ResponseBody String boardDelete(@PathVariable int id) {
		// 인터셉터 처리
		int result = mService.boardDelete(id);
		if(result == 1) {
			return Script.href("/board/list");
		}else {
			return Script.back("삭제 실패");
		}
	}
	
	@GetMapping("/board/updateForm/{id}")
	public String boardUpdateForm(@PathVariable int id, Model model) {
		// 인터셉터 처리		
		Board board = mService.boardUpdateForm(id);
		model.addAttribute("board", board);
		return "board/updateForm";
	}
	
	@PutMapping("/board/update/{id}")
	public @ResponseBody String boardUpdate(@PathVariable int id, Board board, HttpSession session) {
		// 인터셉터 처리
		int result = mService.boardUpdate(id, board, session);
		if(result == 1) {
			return Script.href("/board/list");
		}else {
			return Script.back("업데이트 실패");
		}
	}
	
}
