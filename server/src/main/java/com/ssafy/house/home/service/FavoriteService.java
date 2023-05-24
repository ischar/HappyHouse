package com.ssafy.house.home.service;

import java.sql.SQLException;
import java.util.List;


import com.ssafy.house.home.model.HouseDto;

public interface FavoriteService {

	Integer addFavorite(String userId, String aptCode) throws SQLException;

	Integer deleteFavorite(String userId, String aptCode);

	Integer checkFavorite(String userId, String aptCode);
	List<HouseDto> listFavorite(String userId);

	List<HouseDto> listsFavorite();

}
