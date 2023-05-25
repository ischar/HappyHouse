package com.ssafy.house.home.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.house.home.model.DongDto;
import com.ssafy.house.home.model.HouseDto;
import com.ssafy.house.home.service.HouseService;

@Controller
@RequestMapping("/houses")
public class HouseController {
	
	@Autowired
	private HouseService houseService;
		
	@PostMapping("/search")
	public ResponseEntity<?> getHouses(@RequestBody DongDto dongDto) {
		 try {
	            return new ResponseEntity<List<HouseDto>>(houseService.gethouses(dongDto.getSidoName(), dongDto.getGugunName(), dongDto.getDongName()), HttpStatus.OK);
	        } catch (Exception e) {
	            e.printStackTrace();
	            return new ResponseEntity<String>("서버 오류", HttpStatus.INTERNAL_SERVER_ERROR);
	        }
	    }
	
	// Favorite detail에 사용
	@PostMapping("/search/favorite/house")
	public ResponseEntity<?> getfavoriteHouse(@RequestParam("aptCode") String aptCode) {
		try {
			return new ResponseEntity<List<HouseDto>>(houseService.getApts(aptCode), HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<String>("서버 오류", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PostMapping("/search/house")
	public ResponseEntity<?> getHouse(@RequestParam("title") String title) {
		try {
			return new ResponseEntity<List<HouseDto>>(houseService.getHouse(title), HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<String>("서버 오류", HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}

}
