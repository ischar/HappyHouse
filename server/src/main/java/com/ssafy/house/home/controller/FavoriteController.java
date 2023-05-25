package com.ssafy.house.home.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ssafy.house.home.model.HouseDto;
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
	
	@GetMapping("/delete")
	public ResponseEntity<?> deleteFavorite(@RequestParam("userId") String userId, @RequestParam("aptCode") String aptCode) {
		try {
			return new ResponseEntity<Integer>(favoriteService.deleteFavorite(userId, aptCode), HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<String>("서버 오류", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("/check")
	public ResponseEntity<?> checkFavorite(@RequestParam("userId") String userId, @RequestParam("aptCode") String aptCode) {
		try {
			return new ResponseEntity<Integer>(favoriteService.checkFavorite(userId, aptCode), HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<String>("서버 오류", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("/list")
	public ResponseEntity<?> listFavorite(@RequestParam("userId") String userId) {
		try {
			return new ResponseEntity<List<HouseDto>>(favoriteService.listFavorite(userId), HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<String>("서버 오류", HttpStatus.OK);
		}
	}
	
	@GetMapping("/lists")
	public ResponseEntity<?> listsFavorite() {
		try {
			return new ResponseEntity<List<HouseDto>>(favoriteService.listsFavorite(), HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<String>("서버 오류", HttpStatus.OK);
		}
	}

}
