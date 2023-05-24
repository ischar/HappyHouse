package com.ssafy.house.user.dao;

import java.sql.SQLException;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.house.user.model.UserDto;

@Mapper
public interface UserDao {
	
	UserDto loginUser(Map<String, String> map) throws SQLException;
	void updateUser(UserDto user) throws SQLException;
	Integer signup(UserDto user) throws SQLException;
	String userPwdSearch(UserDto user) throws SQLException;
	Integer deleteUser(String userId) throws SQLException;
	Integer checkId(String tmpid);
	String find(UserDto userDto);

	
}