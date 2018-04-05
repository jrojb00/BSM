package com.mrhi.bsm.model.user;

public class FavoriteCategoryVO {
	private int id;
	private int uId;
	private int icId;
	private String type;
	
	public FavoriteCategoryVO() {
	}
	
	public FavoriteCategoryVO(int uId) {
		this.uId = uId;
	}
	
	public FavoriteCategoryVO(int uId, int icId) {
		this.uId = uId;
		this.icId = icId;
	}

	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public int getuId() {
		return uId;
	}
	
	public void setuId(int uId) {
		this.uId = uId;
	}
	
	public int getIcId() {
		return icId;
	}
	
	public void setIcId(int icId) {
		this.icId = icId;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	@Override
	public String toString() {
		return "FavoriteCategoryVO [id=" + id + ", uId=" + uId + ", icId=" + icId + ", type=" + type + "]";
	}
}
