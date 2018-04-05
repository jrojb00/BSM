package com.mrhi.bsm.model.item;

import java.util.ArrayList;
import java.util.List;

public class CartVO {
	private int cartNumber;							//카트 인덱스
	
	private int[] buyCartNumber;					//카트에 있는 물품중 내가 체크하여 살 물품

	private List<ItemVO> cart = new ArrayList<>();	//카트 리스트
	
	public int getCartNumber() {
		return cartNumber;
	}

	public void setCartNumber(int cartNumber) {
		this.cartNumber = cartNumber;
	}

	public List<ItemVO> getCart() {
		return cart;
	}

	public void setCart(List<ItemVO> cart) {
		this.cart = cart;
	}

	public int[] getBuyCartNumber() {
		return buyCartNumber;
	}

	public void setBuyCartNumber(int[] buyCartNumber) {
		this.buyCartNumber = buyCartNumber;
	}
}
