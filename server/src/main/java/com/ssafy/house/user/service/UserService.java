package com.ssafy.house.user.service;

import java.sql.SQLException;
import java.util.Map;



import com.ssafy.house.user.model.UserDto;

public interface UserService {

	public UserDto loginUser(Map<String, String> map) throws Exception;
	
	public UserDto getUser(Object attribute);

	public void updateUser(UserDto user) throws SQLException;

	public Integer signup(UserDto user) throws SQLException;

	public String userPwdSearch(UserDto user) throws SQLException;
	
	public Integer deleteUser(String userId) throws SQLException;

	public Integer checkId(String tmpid);

	public String find(UserDto userDto);


}