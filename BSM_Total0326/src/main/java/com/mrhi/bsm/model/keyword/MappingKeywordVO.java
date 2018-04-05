package com.mrhi.bsm.model.keyword;

public class MappingKeywordVO {
	private int iId;
	private int kwId;
	
	public MappingKeywordVO(int iId, int kwId) {
		this.iId = iId;
		this.kwId = kwId;
	}

	public int getiId() {
		return iId;
	}

	public void setiId(int iId) {
		this.iId = iId;
	}

	public int getKwId() {
		return kwId;
	}

	public void setKwId(int kwId) {
		this.kwId = kwId;
	}

}
