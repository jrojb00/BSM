package com.mrhi.bsm.model.board;

import java.util.Date;

public class ReplyVO {
    private int id;					// ��� ��ȣ
    private int parentId;			// �Խñ� ��ȣ
    private String replyer;			// ��� �ۼ���
    private int replyerId;			// ����ۼ��ڹ�ȣ
    private String replytext;		// ��� ����
    private Date regDate;			// ��� �ۼ�����
    private Date updatedate;		// ��� ��������
	
    // Getter/Setter
    public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getParentId() {
		return parentId;
	}
	public void setParentId(int parentId) {
		this.parentId = parentId;
	}
	public String getReplyer() {
		return replyer;
	}
	public void setReplyer(String replyer) {
		this.replyer = replyer;
	}
	public int getReplyerId() {
		return replyerId;
	}
	public void setReplyerId(int replyerId) {
		this.replyerId = replyerId;
	}
	public String getReplytext() {
		return replytext;
	}
	public void setReplytext(String replytext) {
		this.replytext = replytext;
	}
	public Date getRegDate() {
		return regDate;
	}
	public void setRegDate(Date regDate) {
		this.regDate = regDate;
	}
	public Date getUpdatedate() {
		return updatedate;
	}
	public void setUpdatedate(Date updatedate) {
		this.updatedate = updatedate;
	}
	
	@Override
	public String toString() {
		return "ReplyVO [id=" + id + ", parentId=" + parentId + ", replyer=" + replyer + ", replyerId=" + replyerId
				+ ", replytext=" + replytext + ", regdate=" + regDate + ", updatedate=" + updatedate + "]";
	}	    
}
