package com.mrhi.bsm.model.board;

import java.util.List;

import com.mrhi.bsm.model.board.ReplyVO;
 
public interface ReplyService {
    // ´ñ±Û ¸ñ·Ï
    public List<ReplyVO> list(BoardVO vo);
    // ´ñ±Û ÀÔ·Â
    public void insertReply(ReplyVO vo);
    // ´ñ±Û ¼öÁ¤
    public void updateReply(ReplyVO vo);
    // ´ñ±Û »èÁ¦
    public void deleteReply(ReplyVO vo);
    // ´ñ±Û ÀüÃ¼ »èÁ¦
    public void deleteReplyList(BoardVO vo);
    // ´ñ±Û °¡Á®¿À±â
	public ReplyVO getReply(ReplyVO vo);
}
