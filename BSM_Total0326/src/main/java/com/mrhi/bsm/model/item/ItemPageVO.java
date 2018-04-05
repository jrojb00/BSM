package com.mrhi.bsm.model.item;

import org.springframework.stereotype.Component;

@Component()
public class ItemPageVO {
	private final int contentNum = 9;	//한 페이지에 몇개 표지할지
	private final int blockNum = 5;		//블록을 몇 개 표현할지
	
	private int totalcount;				//전체 상품 개수
	private int pageNumber = 1;			//현재 페이지 번호
	private int totalPage;				//상품의 총 페이지
	private int tatalBlockCount;		//전체 페이지 블록
	private int pageBlock = 1;			//현재 페이지 블록
	private int startPage = 1;			//현재 페이지 블록의 시작 페이지
	private int endPage = 5;			//현재 페이지 블록의 마지막 페이지
	private boolean prev=false;			//이전 페이지로 가는 화살표
	private boolean next=false;			//다음 페이지로 가는 화살표
	
	public ItemPageVO() {
	}
	
	public void init(int pageNumber, int itemListSize) {
		this.totalcount = itemListSize;
		this.totalPage = (totalcount - 1) / this.contentNum + 1;
		this.pageNumber = pageNumber;
		this.pageBlock = (pageNumber -1) / this.blockNum + 1;
		this.tatalBlockCount = (totalPage - 1) / this.blockNum + 1;
		this.startPage = pageBlock * this.blockNum - 4;
		this.endPage = pageBlock * this.blockNum;
		if (totalPage < endPage) {
			this.endPage = totalPage;
		}
		if (pageBlock < tatalBlockCount) {
			this.next = true;
		} else {
			this.next = false;
		}
		if (pageBlock > 1) {
			this.prev = true;
		} else {
			this.prev = false;
		}
	}
	
	//다음 게시물 페이지 블락
	public void nextPage() {
		this.pageBlock += 1;
		this.prev = true;
		this.startPage = this.startPage + blockNum;
		this.endPage = this.endPage + blockNum;
		if (totalPage < endPage) {
			this.endPage = totalPage;
		}
		this.pageNumber = startPage;
		if (pageBlock < tatalBlockCount) {
			this.next = true;
		} else {
			this.next = false;
		}
	}
	
	//전 게시물 페이지 블락
	public void prevPage() {
		this.pageBlock -= 1;
		this.next = true;
		this.startPage = this.startPage - blockNum;
		this.endPage = this.startPage + blockNum - 1;
		this.pageNumber = startPage;
		if (this.pageBlock != 1) {
			this.prev = true;
		} else {
			this.prev = false;
		}
	}

	public int getTotalcount() {
		return totalcount;
	}
	public void setTotalcount(int totalcount) {
		this.totalcount = totalcount;
	}
	public int getPageNumber() {
		return pageNumber;
	}
	public void setPageNumber(int pageNumber) {
		this.pageNumber = pageNumber;
	}
	public int getContentNum() {
		return contentNum;
	}
	public boolean isPrev() {
		return prev;
	}
	public void setPrev(boolean prev) {
		this.prev = prev;
	}
	public boolean isNext() {
		return next;
	}
	public void setNext(boolean next) {
		this.next = next;
	}
	public int getTotalPage() {
		return totalPage;
	}
	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}
	public int getTatalBlockCount() {
		return tatalBlockCount;
	}
	public void setTatalBlockCount(int tatalBlockCount) {
		this.tatalBlockCount = tatalBlockCount;
	}
	public int getPageBlock() {
		return pageBlock;
	}
	public void setPageBlock(int pageBlock) {
		this.pageBlock = pageBlock;
	}
	public int getStartPage() {
		return startPage;
	}
	public void setStartPage(int startPage) {
		this.startPage = startPage;
	}
	public int getEndPage() {
		return endPage;
	}
	public void setEndPage(int endPage) {
		this.endPage = endPage;
	}
}
