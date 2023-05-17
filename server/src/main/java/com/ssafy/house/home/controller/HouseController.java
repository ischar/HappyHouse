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
		
	@GetMapping("/{sido}/{gugun}/{dong}")
	public ResponseEntity<List<HouseDto>> getHouses(@PathVariable("sido") String sido, @PathVariable("gugun") String gugun, @PathVariable("dong") String dong) {
		List<HouseDto> houses = houseService.gethouses(dong);
		return ResponseEntity.ok(houses);
	}
	
//	@GetMapping("/{sido}/{gugun}/{dong}")
//	public ResponseEntity<?> getHouses(@RequestBody DongDto dongDto) {
//		List<HouseDto> houses = houseService.gethouses(dongDto.getDongName());
//		try {
//			return new ResponseEntity<List<HouseDto>>(houses, HttpStatus.OK);
//		}catch (Exception e) {
//            e.printStackTrace();
//            return new ResponseEntity<String>("서버 오류", HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//	}
//	
//	
//	
//	/*
//		@PostMapping("/{sido}/{gugun}/{dong}")
//	 @ResponseBody
//	public ResponseEntity<?> getHouses(@RequestBody DongDto dongDto) {
//        try {
//            return new ResponseEntity<List<HouseDto>>(houseService.gethouses(dongDto.getDongName()), HttpStatus.OK);
//        } catch (Exception e) {
//            e.printStackTrace();
//            return new ResponseEntity<String>("서버 오류", HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//    }
//	*/
	
}
