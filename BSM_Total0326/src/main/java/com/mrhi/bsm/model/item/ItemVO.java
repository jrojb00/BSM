package com.mrhi.bsm.model.item;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.mrhi.bsm.model.keyword.KeywordVO;

public class ItemVO {	
	private int id;
	private int icId;					//카테고리 id
	
	private String name;
	private int price;		
	private int stockQuantity; 			//재고수량
	private int salesQuantity = 0;		//판매 수량
	private String type;
	
	private boolean alive = true;		//판매 가능 상품
	
	private int totalSalesAmount = price * salesQuantity; //매출액
	private String comment;				//제품설명
	private Date registerDate;			//등록 날짜
	
	private List<KeywordVO> seachedKeywordList = new ArrayList<>();	//검색된 키워드
	private int accuracy = 1;			//검색 정확도
	
	private int pageNumber = 1;
	private String next;
	private String prev;
	
	//이미지 관련
	private MultipartFile imgFile;
	private String imgPath;
	private String contentType;
	private byte[] img;
	
	//관리자모드 like검색 변수
	private String searchCondition;
	private String searchKeyword;	

	public ItemVO() {
		
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getIcId() {
		return icId;
	}

	public void setIcId(int icId) {
		this.icId = icId;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public int getStockQuantity() {
		return stockQuantity;
	}

	public void setStockQuantity(int stockQuantity) {
		this.stockQuantity = stockQuantity;
	}

	public int getSalesQuantity() {
		return salesQuantity;
	}

	public void setSalesQuantity(int salesQuantity) {
		this.salesQuantity = salesQuantity;
	}

	public Date getRegisterDate() {
		return registerDate;
	}

	public void setRegisterDate(Date registerDate) {
		this.registerDate = registerDate;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public String getImgPath() {
		return imgPath;
	}

	public void setImgPath(String imgPath) {
		this.imgPath = imgPath;
	}

	public byte[] getImg() {
		return img;
	}

	public void setImg(byte[] img) {
		this.img = img;
	}

	public MultipartFile getImgFile() {
		return imgFile;
	}

	public void setImgFile(MultipartFile imgFile) {
		this.imgFile = imgFile;
	}
	
	public String getContentType() {
		return contentType;
	}

	public void setContentType(String contentType) {
		this.contentType = contentType;
	}

	public int getTotalSalesAmount() {
		this.totalSalesAmount = (this.price * this.salesQuantity);
		return totalSalesAmount;
	}

	public void setTotalSalesAmount(int totalSalesAmount) {
		this.totalSalesAmount = totalSalesAmount;
	}
	
	public Date getRegisterDay() {
		return registerDate;
	}

	public void setRegisterDay(Date registerDay) {
		this.registerDate = registerDay;
	}
	
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public boolean isAlive() {
		return alive;
	}

	public void setAlive(boolean alive) {
		this.alive = alive;
	}

	public int getAccuracy() {
		return accuracy;
	}

	public void setAccuracy(int accuracy) {
		this.accuracy = accuracy;
	}

	public List<KeywordVO> getKeywordList() {
		return seachedKeywordList;
	}

	public void setKeywordList(List<KeywordVO> seachedKeywordList) {
		this.seachedKeywordList = seachedKeywordList;
	}

	public void updateImg() throws IOException {
		this.imgPath = imgFile.getOriginalFilename();
		contentType = imgFile.getContentType();
		img = imgFile.getBytes();
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
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ItemVO other = (ItemVO) obj;
		if (id != other.id)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "ItemVO [id=" + id + ", name=" + name + ", icId=" + icId + ", price=" + price + ", stockQuantity="
				+ stockQuantity + ", salesQuantity=" + salesQuantity + ", totalSalesAmount="
				+ totalSalesAmount + ", comment=" + comment + ", accuracy = " + accuracy;
	}
}