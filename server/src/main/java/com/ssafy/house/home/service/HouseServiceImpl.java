package com.ssafy.house.home.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.house.home.mapper.HouseMapper;
import com.ssafy.house.home.model.HouseDto;

@Service
public class HouseServiceImpl implements HouseService{
	
	
	@Autowired
	private HouseMapper houseMapper; 
	
	@Override
	public List<HouseDto> gethouses(String dong) {
		return houseMapper.gethouses(dong);
	}

	@Override
	public List<HouseDto> getHouse(String title) {
		return houseMapper.gethouse(title);
	}



}
