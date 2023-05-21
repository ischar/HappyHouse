package com.ssafy.house.home.service;

import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.house.home.mapper.FavoriteMapper;

@Service
public class FavoriteServiceImpl implements FavoriteService{

	@Autowired
	private FavoriteMapper favoriteMapper;
	
	@Override
	public Integer addFavorite(String userId, String aptCode) throws SQLException {
		return favoriteMapper.addFavorite(userId, aptCode);
	}

}
