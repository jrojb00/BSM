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
    
    // ´ñ±Û ¸ñ·Ï
    public List<ReplyVO> list(BoardVO vo) {
        return mybatis.selectList("BoardDAO.listReply", vo);
    }
    // ´ñ±Û ÀÛ¼º
    public void insertReply(ReplyVO vo) {
        mybatis.insert("BoardDAO.insertReply", vo);
    }
    // ´ñ±Û ¼öÁ¤
    public void updateReply(ReplyVO vo) {
    	mybatis.update("BoardDAO.updateReply", vo);	
    }
    // ´ñ±Û »èÁ¦   
	public void deleteReply(ReplyVO vo) {
		mybatis.delete("BoardDAO.deleteReply", vo);		
	}
	//±Û°ú °ü·ÃµÈ ´ñ±Û ÀüÃ¼ »èÁ¦
	public void deleteReplyList(BoardVO vo) {
		mybatis.delete("BoardDAO.deleteReplyList", vo);	
	}
	public ReplyVO getReply(ReplyVO vo) {
		return mybatis.selectOne("BoardDAO.getReply", vo);
	} 	
}
