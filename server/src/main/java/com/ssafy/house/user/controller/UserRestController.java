package com.ssafy.house.user.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.house.user.model.UserDto;
import com.ssafy.house.user.service.UserService;

import io.swagger.annotations.ApiParam;

@RestController
public class UserRestController {

	@Autowired
	private UserService userService;

	public UserRestController(UserService userService) {
		super();
		this.userService = userService;
	}

	@PostMapping("/login")
	public ResponseEntity<?> login(@RequestBody UserDto userinfo, HttpSession session) throws Exception {

		Map<String, String> map = new HashMap<String, String>();
		map.put("id", userinfo.getId());
		map.put("pwd", userinfo.getPwd());

		if (userService.loginUser(map) != null) {
			ResponseEntity<UserDto> entity = new ResponseEntity<UserDto>(userService.loginUser(map), HttpStatus.OK);
			return entity;
		}
		return new ResponseEntity<String>("fail", HttpStatus.UNAUTHORIZED);
	}

	@PutMapping("/update")
	public ResponseEntity<?> updateUser(@RequestBody UserDto userDto, HttpSession session) {
		try {
			userService.updateUser(userDto);
			session.setAttribute("userinfo", userDto);
			return new ResponseEntity<Integer>(1, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<String>("서버 오류", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PostMapping("/signup")
	@Transactional
	public ResponseEntity<?> signupUser(@RequestBody UserDto userDto, HttpSession session) {
		try {
			return new ResponseEntity<Integer>(userService.signup(userDto), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<String>("서버 오류", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@DeleteMapping("/delete/{id}")
	@Transactional
	public ResponseEntity<?> deleteUser(@PathVariable String id, HttpSession session) {
		try {
			session.invalidate();
			return new ResponseEntity<Integer>(userService.deleteUser(id), HttpStatus.OK);

		} catch (Exception e) {
			return new ResponseEntity<String>("서버 오류", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/checkid/{tmpid}")
	public boolean login(@PathVariable String tmpid, HttpSession session) throws Exception {
		int entity = userService.checkId(tmpid);
		if (entity == 1)
			return false;
		else
			return true;
	}
	
	@PostMapping("/search")
	public String find(@RequestBody UserDto userDto) throws Exception {
		if(!userService.find(userDto).isEmpty()) {
			return userService.find(userDto);
		}else
			return "존재하지 않는 회원입니다.";
	}
	
	

}