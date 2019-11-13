package com.sundays.merryaround.board.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.sundays.merryaround.board.store.BoardStore;

@Service(value="boardServiceImpl")
public class BoardServiceImpl implements BoardService {
	@Resource(name="boardStoreImpl")
	private BoardStore boardStore;
	
	@Override
	public String dbSelectTest() {
		return boardStore.getData();
	}

}
