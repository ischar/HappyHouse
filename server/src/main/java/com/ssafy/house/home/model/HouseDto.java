package com.ssafy.house.home.model;


public class HouseDto {

	private String roadName;
	private String buildYear;
	private String dong;
	private String apartmentName;
	private double lng;
	private double lat;
	private String dealYear;
	private String dealMonth;
	private String dealDay;
	private double area;
	private String dealAmount;
	
	public HouseDto() {
	}

	public HouseDto(String roadName, String buildYear, String dong, String apartmentName, double lng, double lat,
			String dealYear, String dealMonth, String dealDay, double area, String dealAmount) {
		super();
		this.roadName = roadName;
		this.buildYear = buildYear;
		this.dong = dong;
		this.apartmentName = apartmentName;
		this.lng = lng;
		this.lat = lat;
		this.dealYear = dealYear;
		this.dealMonth = dealMonth;
		this.dealDay = dealDay;
		this.area = area;
		this.dealAmount = dealAmount;
	}

	public String getRoadName() {
		return roadName;
	}
	
	public void setRoadName(String roadName) {
		this.roadName = roadName;
	}

	public String getBuildYear() {
		return buildYear;
	}

	public void setBuildYear(String buildYear) {
		this.buildYear = buildYear;
	}

	public String getDong() {
		return dong;
	}

	public void setDong(String dong) {
		this.dong = dong;
	}

	public String getApartmentName() {
		return apartmentName;
	}

	public void setApartmentName(String apartmentName) {
		this.apartmentName = apartmentName;
	}

	public double getLng() {
		return lng;
	}

	public void setLng(double lng) {
		this.lng = lng;
	}

	public double getLat() {
		return lat;
	}

	public void setLat(double lat) {
		this.lat = lat;
	}

	public String getDealYear() {
		return dealYear;
	}

	public void setDealYear(String dealYear) {
		this.dealYear = dealYear;
	}

	public String getDealMonth() {
		return dealMonth;
	}

	public void setDealMonth(String dealMonth) {
		this.dealMonth = dealMonth;
	}

	public String getDealDay() {
		return dealDay;
	}

	public void setDealDay(String dealDay) {
		this.dealDay = dealDay;
	}

	public double getArea() {
		return area;
	}

	public void setArea(double area) {
		this.area = area;
	}

	public String getDealAmount() {
		return dealAmount;
	}

	public void setDealAmount(String dealAmount) {
		this.dealAmount = dealAmount;
	}

}
