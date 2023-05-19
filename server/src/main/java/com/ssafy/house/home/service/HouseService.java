package com.ssafy.house.home.service;

import java.util.List;

import org.springframework.http.HttpStatus;

import com.ssafy.house.home.model.HouseDto;

public interface HouseService {

	List<HouseDto> gethouses(String dong);

	List<HouseDto> getHouse(String title);


}
