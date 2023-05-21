package com.ssafy.house.home.mapper;

import java.sql.SQLException;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface FavoriteMapper {

	Integer addFavorite(String userId, String aptCode) throws SQLException;

}
