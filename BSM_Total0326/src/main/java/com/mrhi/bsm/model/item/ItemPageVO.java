package com.mrhi.bsm.model.item;

import org.springframework.stereotype.Component;

@Component()
public class ItemPageVO {
	private final int contentNum = 9;	//�� �������� � ǥ������
	private final int blockNum = 5;		//����� �� �� ǥ������
	
	private int totalcount;				//��ü ��ǰ ����
	private int pageNumber = 1;			//���� ������ ��ȣ
	private int totalPage;				//��ǰ�� �� ������
	private int tatalBlockCount;		//��ü ������ ���
	private int pageBlock = 1;			//���� ������ ���
	private int startPage = 1;			//���� ������ ����� ���� ������
	private int endPage = 5;			//���� ������ ����� ������ ������
	private boolean prev=false;			//���� �������� ���� ȭ��ǥ
	private boolean next=false;			//���� �������� ���� ȭ��ǥ
	
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
	
	//���� �Խù� ������ ���
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
	
	//�� �Խù� ������ ���
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
