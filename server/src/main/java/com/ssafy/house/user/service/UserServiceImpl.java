package com.ssafy.house.user.service;

import java.sql.SQLException;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.house.user.dao.UserDao;
import com.ssafy.house.user.model.UserDto;

@Service
public class UserServiceImpl implements UserService {


	@Autowired
	private UserDao userDao;

	public UserServiceImpl(UserDao userDao) {
		super();
		this.userDao = userDao;
	}

	@Override
	public UserDto loginUser(Map<String, String> map) throws Exception {
 		return userDao.loginUser(map);
	}

	@Override
	public UserDto getUser(Object attribute) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void updateUser(UserDto user) throws SQLException {
		userDao.updateUser(user);		
	}

	@Override
	public Integer signup(UserDto user) throws SQLException {
		return userDao.signup(user);
	}

	@Override
	public String userPwdSearch(UserDto user) throws SQLException {
		return userDao.userPwdSearch(user);
	}

	@Override
	public Integer deleteUser(String userId) throws SQLException {
		return userDao.deleteUser(userId);
	}
}
