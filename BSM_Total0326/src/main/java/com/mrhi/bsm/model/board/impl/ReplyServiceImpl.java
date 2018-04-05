package com.mrhi.bsm.model.board.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mrhi.bsm.model.board.BoardVO;
import com.mrhi.bsm.model.board.ReplyService;
import com.mrhi.bsm.model.board.ReplyVO;
 
@Service("replyService")
public class ReplyServiceImpl implements ReplyService {    
    @Autowired
    ReplyDAO replyDAO;
    
    // ��� ���
    @Override
    public List<ReplyVO> list(BoardVO vo) {
        return replyDAO.list(vo);
    }
    // ��� �ۼ�
    @Override
    public void insertReply(ReplyVO vo) {
        replyDAO.insertReply(vo);
    }
    // ��� ����
    @Override
    public void updateReply(ReplyVO vo) {
    	replyDAO.updateReply(vo); 
    }
    // ��� ����
    @Override
    public void deleteReply(ReplyVO vo) {
    	replyDAO.deleteReply(vo);
    }
	@Override
	public void deleteReplyList(BoardVO vo) {
		replyDAO.deleteReplyList(vo);
	}
	@Override
	public ReplyVO getReply(ReplyVO vo) {
		return 	replyDAO.getReply(vo);
	}       
}
