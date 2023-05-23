package com.ssafy.house.home.mapper;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.house.home.model.DongDto;

@Mapper
public interface DongMapper {

	public List<DongDto> findSido() throws SQLException;

	public List<DongDto> findGugun(String sido);

	public List<DongDto> findDong(String gugun);

	public List<DongDto> getSiGunGu();



}
