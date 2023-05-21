package com.ssafy.house.home.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ssafy.house.home.model.FavoriteDto;
import com.ssafy.house.home.service.FavoriteService;

@Controller
@RequestMapping("/favorite")
public class FavoriteController {
	
	@Autowired
	private FavoriteService favoriteService; 
	
	@PostMapping("/add")
	public ResponseEntity<?> addFavorite(@RequestParam("userId") String userId, @RequestParam("aptCode") String aptCode) {
		try {
			return new ResponseEntity<Integer>(favoriteService.addFavorite(userId, aptCode), HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<String>("서버 오류", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
