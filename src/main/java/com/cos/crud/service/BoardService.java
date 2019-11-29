package com.cos.crud.service;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cos.crud.model.Board;
import com.cos.crud.model.User;
import com.cos.crud.repository.BoardRepository;

@Service
public class BoardService {

	@Autowired
	private BoardRepository mRepo;
	
	@Transactional
	public void increadCountAndTimeUpdate(int id) {
		try {
			mRepo.increaseCount(id);
			mRepo.timeUpdate(id);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		
	}
	
	public List<Board> boardList() {
		List<Board> boards = mRepo.findAll();
		return boards;
	}

	public Board boardDetail(int id) {
		Optional<Board> board = mRepo.findById(id);
		return board.get();
	}
	
	public int boardWrite(Board board, HttpSession session) {
		User user = (User)session.getAttribute("user");
		board.setUser(user); //세션에서 유저정보 들고와서 넣기
		try {
			mRepo.save(board);
			return 1;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return -1;
	}
	
	public int boardDelete(int id) {
		try {
			mRepo.deleteById(id);
			return 1;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return -1;
	}
	
	public Board boardUpdateForm(int id) {
		Optional<Board> board = mRepo.findById(id);
		return board.get();
	}
	
	public int boardUpdate(int id, Board board, HttpSession session) {
		User user = (User)session.getAttribute("user");
		board.setUser(user); //세션에서 유저정보 들고와서 넣기
		board.setId(id);
		try {
			mRepo.save(board);
			return 1;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return -1;
	}
}
