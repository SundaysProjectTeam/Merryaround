package com.sundays.merryaround.board.controller;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sundays.merryaround.board.service.BoardService;

@RestController
public class BoardController {
	@Resource(name="boardServiceImpl")
	private BoardService boardService;
	
	@GetMapping(value="/get")
	public String doGet() {
		return boardService.dbSelectTest();
	}

}
