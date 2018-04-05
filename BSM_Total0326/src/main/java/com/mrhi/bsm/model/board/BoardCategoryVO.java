package com.mrhi.bsm.model.board;

public class BoardCategoryVO {
	private int id;
	private String name;
	private int readGrade;
	private int writerGrade;
	
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
	
	public int getReadGrade() {
		return readGrade;
	}
	public void setReadGrade(int readGrade) {
		this.readGrade = readGrade;
	}
	public int getWriterGrade() {
		return writerGrade;
	}
	public void setWriterGrade(int writerGrade) {
		this.writerGrade = writerGrade;
	}
	
	@Override
	public String toString() {
		return "BoardCategoryVO [id=" + id + ", name=" + name + ", readGrade=" + readGrade + ", writerGrade="
				+ writerGrade + "]";
	}	
}
