package com.ssafy.house.home.service;

import java.util.List;

import com.ssafy.house.home.model.DongDto;

public interface DongService {
	List<DongDto> findSido() throws Exception;

	List<DongDto> findGugun(String sido);

	List<DongDto> findDong(String gugun);
}

