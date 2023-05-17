package com.ssafy.house.user.controller;

import java.sql.SQLException;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.house.user.model.UserDto;
import com.ssafy.house.user.service.UserService;
import com.ssafy.house.user.service.UserServiceImpl;

@Controller
public class UserController {
		
	private UserService userService;
	
	public UserController(UserService userService) {
		super();
		this.userService = userService;
	}

	@GetMapping("/login")
	public String login() {
		return "login/login";
	}
	
	@GetMapping("/logout")
	public String logout(HttpSession session) {
		session.invalidate();
		return "redirect:/index";
	}
	
	@GetMapping("/signup")
	public String signup() {
		return "user/signup";
	}
	
	@GetMapping("/user/userinfo")
	public String userinfo(HttpSession session) {
	
		UserDto user = (UserDto) session.getAttribute("userinfo");
		System.out.println(user.getId());
		return "/user/userinfo";
	}
	
	@PostMapping("/user/userUpdate")
	public String update() {
		return "/user/userUpdate";
	}
	
//	@PostMapping("/user/updateUser")
//	public String update(@RequestParam("id") String id, @RequestParam("pwd") String pwd,
//			@RequestParam("name") String name, @RequestParam("address") String address, 
//			@RequestParam("phone") String phone, UserDto user, HttpSession session) throws SQLException {
//		
//		System.out.println(user);
//		user.setId(id);
//		user.setPwd(pwd);
//		user.setName(name);
//		user.setAddress(address);
//		user.setPhone(phone);
//		userService.updateUser(user);
//		session.setAttribute("userinfo", user);
//
//		System.out.println(session.getAttribute("userinfo"));
//		return "redirect:/user/userinfo";
//	}	
	

	@GetMapping("/user/userPwdSearch")
	public String userPwdSearch() {
		return "/user/userPwdSearch";
	}
	
	@PostMapping("/user/userPwdSearch")
	public String userPwdSearch(@RequestParam("id") String id, @RequestParam("name") String name, @RequestParam("phone") String phone, UserDto user, Model model) throws SQLException {
		user.setId(id);
		user.setName(name);
		user.setPhone(phone);
		String pwd = userService.userPwdSearch(user);
		System.out.println(pwd);
		model.addAttribute("pwd", pwd);
		return "/user/userPwdSearch";
	}
//	
//	@PostMapping("/user/userDelete")
//	public String userDelete(@RequestParam("id") String id, HttpSession session) throws SQLException {
//		userService.deleteUser(id);
//		session.invalidate();
//		return "/index";
//	}
	


}
