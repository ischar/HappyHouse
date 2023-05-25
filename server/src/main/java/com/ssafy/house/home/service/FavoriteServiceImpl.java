package com.ssafy.house.home.service;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.house.home.mapper.FavoriteMapper;
import com.ssafy.house.home.model.FavoriteDto;
import com.ssafy.house.home.model.HouseDto;

@Service
public class FavoriteServiceImpl implements FavoriteService{

	@Autowired
	private FavoriteMapper favoriteMapper;
	
	@Override
	public Integer addFavorite(String userId, String aptCode) throws SQLException {
		return favoriteMapper.addFavorite(userId, aptCode);
	}

	@Override
	public Integer deleteFavorite(String userId, String aptCode) {
		return favoriteMapper.deleteFavorite(userId, aptCode);
	}

	@Override
	public Integer checkFavorite(String userId, String aptCode) {
		return favoriteMapper.checkFavorite(userId, aptCode);
	}

	@Override
	public List<HouseDto> listFavorite(String userId) {
		return favoriteMapper.listFavorite(userId);
	}

	@Override
	public List<HouseDto> listsFavorite() {
		return favoriteMapper.listsFavorite();
	}

}
