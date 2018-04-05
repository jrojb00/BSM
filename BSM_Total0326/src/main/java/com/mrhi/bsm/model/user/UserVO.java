package com.mrhi.bsm.model.user;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.mrhi.bsm.model.item.BuyItemVO;

public class UserVO {
	private int id = 0;
	private int isAdmin = 0;
	
	private String name;
	private String gender;
	private String securityNumber;
	private String loginId;
	private String loginPassword;
	private String email;
	private String address;
	private String phoneNumber;
	
	private int pageNumber = 1;
	private String next;
	private String prev;
	
	private List<BuyItemVO> purchasingList;		//구매 내역
	
	private List<FavoriteCategoryVO> myFavoriteList = new ArrayList<>();
	private Integer[] myFavoriteId;	//관심 사항
	
	private String tendencyAnalysis; // 매출분석
	
	//관리자모드 like검색 변수
	private String searchCondition;
	private String searchKeyword;

	public String getName() {
		return name;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getLoginId() {
		return loginId;
	}

	public void setLoginId(String loginId) {
		this.loginId = loginId;
	}

	public String getLoginPassword() {
		return loginPassword;
	}

	public void setLoginPassword(String loginPassword) {
		this.loginPassword = loginPassword;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getBirth() {
		return securityNumber;
	}

	public void setBirth(String birth) {
		this.securityNumber = birth;
	}

	public String getTendencyAnalysis() {
		return tendencyAnalysis;
	}

	public void setTendencyAnalysis(String tendencyAnalysis) {
		this.tendencyAnalysis = tendencyAnalysis;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getSecurityNumber() {
		return securityNumber;
	}

	public void setSecurityNumber(String securityNumber) {
		this.securityNumber = securityNumber;
	}

	public int getIsAdmin() {
		return isAdmin;
	}

	public void setIsAdmin(int isAdmin) {
		this.isAdmin = isAdmin;
	}

	public List<FavoriteCategoryVO> getMyFavoriteList() {
		return myFavoriteList;
	}

	public void setMyFavoriteList(List<FavoriteCategoryVO> myFavoriteList) {
		this.myFavoriteList = myFavoriteList;
	}

	public Integer[] getMyFavoriteId() {
		return myFavoriteId;
	}

	public void setMyFavoriteId(Integer[] myFavoriteId) {
		this.myFavoriteId = myFavoriteId;
	}
	
	public String getSearchCondition() {
		return searchCondition;
	}

	public void setSearchCondition(String searchCondition) {
		this.searchCondition = searchCondition;
	}

	public String getSearchKeyword() {
		return searchKeyword;
	}

	public void setSearchKeyword(String searchKeyword) {
		this.searchKeyword = searchKeyword;
	}

	public List<BuyItemVO> getPurchasingList() {
		return purchasingList;
	}

	public void setPurchasingList(List<BuyItemVO> purchasingList) {
		this.purchasingList = purchasingList;
	}

	public int getPageNumber() {
		return pageNumber;
	}

	public void setPageNumber(int pageNumber) {
		this.pageNumber = pageNumber;
	}

	public String getNext() {
		return next;
	}

	public void setNext(String next) {
		this.next = next;
	}

	public String getPrev() {
		return prev;
	}

	public void setPrev(String prev) {
		this.prev = prev;
	}

	@Override
	public String toString() {
		return "UserVO [id=" + id + ", isAdmin=" + isAdmin + ", name=" + name + ", gender=" + gender
				+ ", securityNumber=" + securityNumber + ", loginId=" + loginId + ", loginPassword=" + loginPassword
				+ ", email=" + email + ", address=" + address + ", phoneNumber=" + phoneNumber + ", myFavoriteList="
				+ myFavoriteList + ", myFavoriteId=" + Arrays.toString(myFavoriteId) + "]";
	}
}
