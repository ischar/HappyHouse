package com.ssafy.house.home.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;
import com.ssafy.house.home.model.DongDto;
import com.ssafy.house.home.service.DongService;
import com.ssafy.house.user.model.UserDto;



@RestController
@RequestMapping("/address")
public class DongController {

	// Logger는 로깅을 위한 인터페이스로, 로그 메시지를 출력하는 데 사용됩니다. LoggerFactory는 Logger 인스턴스를 생성하고
	// 관리하는 팩토리 클래스
	// 컨트롤러에서 로그 메시지를 출력하기 위해
	private final Logger logger = LoggerFactory.getLogger(DongController.class);

	@Autowired
	private DongService dongService; 
	
	
	@GetMapping("/sigungu") 
	public ResponseEntity<?> getSiGungu() throws Exception {
		try {
			return new ResponseEntity<List<DongDto>>(dongService.getSiGunGu(), HttpStatus.OK); 
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<String>("서버 오류", HttpStatus.OK);
		}
		
	}
	@GetMapping("/sido")
    public ResponseEntity<List<String>> getSido() throws Exception {
        List<DongDto> list = dongService.findSido();
        if (list != null) {
            List<String> names = new ArrayList<>();
            for(int i = 0 ; i < list.size(); i++) {
                names.add(list.get(i).getSidoName());
            }
            return ResponseEntity.ok(names);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
	
	@GetMapping("/gugun")
    public ResponseEntity<List<String>> getGugun(@RequestParam("sido") String sido) throws Exception {
        List<DongDto> list = dongService.findGugun(sido);
        if (list != null) {
            List<String> names = new ArrayList<>();
            for(int i = 0 ; i < list.size(); i++) {
                names.add(list.get(i).getGugunName());
            }
            return ResponseEntity.ok(names);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
	
	@GetMapping("/dong")
    public ResponseEntity<List<String>> getDong(@RequestParam("gugun") String gugun) throws Exception {
        List<DongDto> list = dongService.findDong(gugun);
        if (list != null) {
            List<String> names = new ArrayList<>();
            for(int i = 0 ; i < list.size(); i++) {
                names.add(list.get(i).getDongName());
            }
            return ResponseEntity.ok(names);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
	
		
	}

