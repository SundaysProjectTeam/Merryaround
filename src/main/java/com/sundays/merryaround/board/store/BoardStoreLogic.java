package com.sundays.merryaround.board.store;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service(value="boardStoreImpl")
public class BoardStoreLogic implements BoardStore {
	@Autowired
	private BoardDao boardDao;

	@Override
	public String getData() {
		String result = null;
		try {
			result = boardDao.selectData();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
}
