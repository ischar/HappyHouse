package com.ssafy.house.home.mapper;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.house.home.model.DongDto;
import com.ssafy.house.home.model.HouseDto;

@Mapper
public interface HouseMapper {

	List<HouseDto> gethouses(String dong);

	List<HouseDto> gethouse(String title);



}
