package com.mrhi.bsm.model.item;

import java.util.ArrayList;
import java.util.List;

public class CartVO {
	private int cartNumber;							//īƮ �ε���
	
	private int[] buyCartNumber;					//īƮ�� �ִ� ��ǰ�� ���� üũ�Ͽ� �� ��ǰ

	private List<ItemVO> cart = new ArrayList<>();	//īƮ ����Ʈ
	
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
