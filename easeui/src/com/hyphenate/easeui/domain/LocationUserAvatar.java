package com.hyphenate.easeui.domain;

import java.io.Serializable;

public class LocationUserAvatar extends User implements Serializable{
	private static final long serialVersionUID = 1L;
	private Integer mlocationId;
	private Double mlocationLatitude;
	private Double mlocationLongitude;
	private Boolean mlocationIsSearched;
	private String mlocationLastUpdateTime;
	private Double distance;
	public LocationUserAvatar() {
		super();
	}

	public LocationUserAvatar(String muserName, String muserNick, Integer mavatarId, String mavatarPath,String mavatarSuffix,
			Integer mavatarType, String mavatarLastUpdateTime,Integer mlocationId, Double mlocationLatitude, Double mlocationLongitude,
			Boolean mlocationIsSearched, String mlocationLastUpdateTime, Double distance) {
		super(muserName, muserNick, mavatarId, mavatarPath,mavatarSuffix, mavatarType, mavatarLastUpdateTime);
		this.mlocationId = mlocationId;
		this.mlocationLatitude = mlocationLatitude;
		this.mlocationLongitude = mlocationLongitude;
		this.mlocationIsSearched = mlocationIsSearched;
		this.mlocationLastUpdateTime = mlocationLastUpdateTime;
		this.distance = distance;
	}

	public Integer getMLocationId() {
		return mlocationId;
	}
	public void setMLocationId(Integer mlocationId) {
		this.mlocationId = mlocationId;
	}
	public Double getMLocationLatitude() {
		return mlocationLatitude;
	}
	public void setMLocationLatitude(Double mlocationLatitude) {
		this.mlocationLatitude = mlocationLatitude;
	}
	public Double getMLocationLongitude() {
		return mlocationLongitude;
	}
	public void setMLocationLongitude(Double mlocationLongitude) {
		this.mlocationLongitude = mlocationLongitude;
	}
	public Boolean getMLocationIsSearched() {
		return mlocationIsSearched;
	}
	public void setMLocationIsSearched(Boolean mlocationIsSearched) {
		this.mlocationIsSearched = mlocationIsSearched;
	}
	public String getMLocationLastUpdateTime() {
		return mlocationLastUpdateTime;
	}
	public void setMLocationLastUpdateTime(String mlocationLastUpdateTime) {
		this.mlocationLastUpdateTime = mlocationLastUpdateTime;
	}
	public Double getDistance() {
		return distance;
	}
	public void setDistance(Double distance) {
		this.distance = distance;
	}

	@Override
	public String toString() {
		return "LocationUserAvatar [mlocationId=" + mlocationId + ", mlocationLatitude=" + mlocationLatitude
				+ ", mlocationLongitude=" + mlocationLongitude + ", mlocationIsSearched=" + mlocationIsSearched
				+ ", mlocationLastUpdateTime=" + mlocationLastUpdateTime + ", distance=" + distance + "]";
	}
	
}
