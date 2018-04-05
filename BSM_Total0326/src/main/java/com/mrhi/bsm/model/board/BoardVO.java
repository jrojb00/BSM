package com.mrhi.bsm.model.board;

import java.sql.Date;

// VO(Value Object)
public class BoardVO {
	private int id;
	private int bcId;
	private String title;
	private String writer;
	private int writerId; 
	private Date regDate;
	private String content;
	private String goodpoint;
	private String hatepoint;
	private int comments; //´ñ±Û¼ö
	private int count;
	private String searchCondition;
	private String searchKeyword;
	private boolean modify;
	private boolean replyModify;
	private int pageNumber=1;
	private String next;
	private String prev;
	
	public void incReadCnt() {
		count++;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}	

	public int getBcId() {
		return bcId;
	}

	public void setBcId(int bcId) {
		this.bcId = bcId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getWriter() {
		return writer;
	}

	public void setWriter(String writer) {
		this.writer = writer;
	}	

	public int getWriterId() {
		return writerId;
	}

	public void setWriterId(int writerId) {
		this.writerId = writerId;
	}

	public Date getRegDate() {
		return regDate;
	}

	public void setRegDate(Date regDate) {
		this.regDate = regDate;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getGoodpoint() {
		return goodpoint;
	}

	public void setGoodpoint(String goodpoint) {
		this.goodpoint = goodpoint;
	}

	public String getHatepoint() {
		return hatepoint;
	}

	public void setHatepoint(String hatepoint) {
		this.hatepoint = hatepoint;
	}

	public int getComments() {
		return comments;
	}

	public void setComments(int comments) {
		this.comments = comments;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
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

	public boolean isModify() {
		return modify;
	}

	public void setModify(boolean modify) {
		this.modify = modify;
	}

	public boolean isReplyModify() {
		return replyModify;
	}

	public void setReplyModify(boolean replyModify) {
		this.replyModify = replyModify;
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
		return "BoardVO [id=" + id + ", bcId=" + bcId + ", title=" + title + ", writer=" + writer + ", writerId="
				+ writerId + ", regDate=" + regDate + ", content=" + content + ", goodpoint=" + goodpoint
				+ ", hatepoint=" + hatepoint + ", comments=" + comments + ", count=" + count + ", searchCondition="
				+ searchCondition + ", searchKeyword=" + searchKeyword + ", modify=" + modify + ", replyModify="
				+ replyModify + ", pageNumber=" + pageNumber + "]";
	}	
}
