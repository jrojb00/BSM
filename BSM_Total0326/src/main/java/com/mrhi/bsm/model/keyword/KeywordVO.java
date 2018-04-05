package com.mrhi.bsm.model.keyword;

public class KeywordVO {
	private int id;
	private String word; //키워드
	
	private int pageNumber = 1;
	private String next;
	private String prev;

	private String sortType = "accuracyCompare";	//검색시 검색리스트 정렬 방법
	
	public KeywordVO() {
		
	}
	
	//키워드만드는 생성자 워드만 포함
	public KeywordVO(String word) {		
		this.word = word;
	}

	public KeywordVO(int id, String word) {
		this.id = id;
		this.word = word;
	}

	public int getId() {
		return id;
	}

	public String getWord() {
		return word;
	}

	public void setWord(String word) {
		this.word = word;
	}

	public String getSortType() {
		return sortType;
	}

	public void setSortType(String sortType) {
		this.sortType = sortType;
	}

	public void setId(int id) {
		this.id = id;
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
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		KeywordVO other = (KeywordVO) obj;
		if (word == null) {
			if (other.word != null)
				return false;
		} else if (!word.equals(other.word))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "KeywordVO [id=" + id + ", word=" + word + "]";
	}
	
}