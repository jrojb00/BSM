package com.mrhi.bsm.model.item;

public class ItemCategoryVO {
	private int id;
	private String type;
	private int parentId;
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public String getType() {
		return type;
	}
	
	public void setType(String type) {
		this.type = type;
	}
	
	public int getParentId() {
		return parentId;
	}
	
	public void setParentId(int parentId) {
		this.parentId = parentId;
	}

	@Override
	public String toString() {
		return "ItemCategoryVO [id=" + id + ", type=" + type + ", parentId=" + parentId + "]";
	}
}
