package com.hk.borad.main;

import java.util.ArrayList;
import java.util.List;

import com.hk.board.daos.UserDao;
import com.hk.board.dtos.UserDto;

public class MainTest {

	public static void main(String[] args) {
		getAllListTest();

	}
	
	public static void getAllListTest() {
		List<UserDto> list = new ArrayList<UserDto>();
		
		UserDao uDao = new UserDao();
		list = uDao.getAllUser();
		for (UserDto userDto : list) {
			System.out.println(userDto);
		}
	}

}
