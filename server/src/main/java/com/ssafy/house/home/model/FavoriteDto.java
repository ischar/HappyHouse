package com.ssafy.house.home.model;

public class FavoriteDto {
	private String userId;
	private String aptCode;
	
	
	public FavoriteDto() {
		super();
	}

	public FavoriteDto(String userId, String aptCode) {
		super();
		this.userId = userId;
		this.aptCode = aptCode;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getAptCode() {
		return aptCode;
	}

	public void setAptCode(String aptCode) {
		this.aptCode = aptCode;
	}
	
}
