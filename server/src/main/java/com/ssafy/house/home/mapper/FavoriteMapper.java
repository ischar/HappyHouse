package com.ssafy.house.home.mapper;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.house.home.model.FavoriteDto;
import com.ssafy.house.home.model.HouseDto;

@Mapper
public interface FavoriteMapper {

	Integer addFavorite(String userId, String aptCode) throws SQLException;

	Integer deleteFavorite(String userId, String aptCode);

	Integer checkFavorite(String userId, String aptCode);

	List<HouseDto> listFavorite(String userId);

}
