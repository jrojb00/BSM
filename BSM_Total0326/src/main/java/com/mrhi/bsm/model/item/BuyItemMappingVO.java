package com.mrhi.bsm.model.item;

import java.util.Date;

public class BuyItemMappingVO {
	private int u_id;				//구매자
	private int i_id;				//구매 상품
	private int purchasingQuantity;	//구매 수량
	private Date buyTime;			//구매 시간
	private String type;			//구매 상품의 타입
	private String birth;			//구매자의 나이
	private String gender;			//구매자의 성별
	
	public BuyItemMappingVO() {
	}
	
	public BuyItemMappingVO(int u_id, int i_id, int purchasingQuantity) {
		this.u_id = u_id;
		this.i_id = i_id;
		this.purchasingQuantity = purchasingQuantity;
	}

	public int getU_id() {
		return u_id;
	}

	public void setU_id(int u_id) {
		this.u_id = u_id;
	}

	public int getI_id() {
		return i_id;
	}

	public void setI_id(int i_id) {
		this.i_id = i_id;
	}

	public int getPurchasingQuantity() {
		return purchasingQuantity;
	}

	public void setPurchasingQuantity(int purchasingQuantity) {
		this.purchasingQuantity = purchasingQuantity;
	}

	public Date getBuyTime() {
		return buyTime;
	}

	public void setBuyTime(Date buyTime) {
		this.buyTime = buyTime;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getBirth() {
		return birth;
	}

	public void setBirth(String birth) {
		this.birth = birth;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	@Override
	public String toString() {
		return "BuyItemMappingVO [cNum=" + u_id + ", pId=" + i_id + ", purchasingQuantity=" + purchasingQuantity
				+ ", buyTime=" + buyTime + ", type=" + type + ", birth=" + birth + ", gender=" + gender + "]";
	}
}
