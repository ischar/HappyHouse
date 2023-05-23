package com.ssafy.house.home.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.house.home.mapper.DongMapper;
import com.ssafy.house.home.model.DongDto;



@Service
public class DongServiceImpl implements DongService {

	@Autowired
	private DongMapper dongMapper;
	
	
	@Override
	public List<DongDto> findSido() throws Exception {
		System.out.println("findSido() 실행");
		return dongMapper.findSido(); // null 고치기
	}


	@Override
	public List<DongDto> findGugun(String sido) {
		System.out.println("findGugun()!!!!! 실행");
		return dongMapper.findGugun(sido);
	}


	@Override
	public List<DongDto> findDong(String gugun) {
		// TODO Auto-generated method stub
		return dongMapper.findDong(gugun);
	}


	@Override
	public List<DongDto> getSiGunGu() {
		// TODO Auto-generated method stub
		return dongMapper.getSiGunGu();
	}




}
