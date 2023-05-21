package com.ssafy.house.home.service;

import java.sql.SQLException;

public interface FavoriteService {

	Integer addFavorite(String userId, String aptCode) throws SQLException;

}
