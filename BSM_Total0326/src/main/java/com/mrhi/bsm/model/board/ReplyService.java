package com.mrhi.bsm.model.board;

import java.util.List;

import com.mrhi.bsm.model.board.ReplyVO;
 
public interface ReplyService {
    // ��� ���
    public List<ReplyVO> list(BoardVO vo);
    // ��� �Է�
    public void insertReply(ReplyVO vo);
    // ��� ����
    public void updateReply(ReplyVO vo);
    // ��� ����
    public void deleteReply(ReplyVO vo);
    // ��� ��ü ����
    public void deleteReplyList(BoardVO vo);
    // ��� ��������
	public ReplyVO getReply(ReplyVO vo);
}
