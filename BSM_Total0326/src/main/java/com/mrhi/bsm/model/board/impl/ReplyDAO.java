package com.mrhi.bsm.model.board.impl;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.mrhi.bsm.model.board.BoardVO;
import com.mrhi.bsm.model.board.ReplyVO;
 
@Repository
public class ReplyDAO {
    @Autowired
    SqlSessionTemplate mybatis;
    
    // ��� ���
    public List<ReplyVO> list(BoardVO vo) {
        return mybatis.selectList("BoardDAO.listReply", vo);
    }
    // ��� �ۼ�
    public void insertReply(ReplyVO vo) {
        mybatis.insert("BoardDAO.insertReply", vo);
    }
    // ��� ����
    public void updateReply(ReplyVO vo) {
    	mybatis.update("BoardDAO.updateReply", vo);	
    }
    // ��� ����   
	public void deleteReply(ReplyVO vo) {
		mybatis.delete("BoardDAO.deleteReply", vo);		
	}
	//�۰� ���õ� ��� ��ü ����
	public void deleteReplyList(BoardVO vo) {
		mybatis.delete("BoardDAO.deleteReplyList", vo);	
	}
	public ReplyVO getReply(ReplyVO vo) {
		return mybatis.selectOne("BoardDAO.getReply", vo);
	} 	
}
