package com.mrhi.bsm.model.board.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mrhi.bsm.model.board.BoardCategoryService;
import com.mrhi.bsm.model.board.BoardCategoryVO;
import com.mrhi.bsm.model.board.BoardVO;

@Service("boardCategoryService")
public class BoardCategoryServiceImpl implements BoardCategoryService {
	@Autowired	
	private BoardCategoryDAO boardCategoryDAO;	

	@Override
	public BoardCategoryVO getBoardCategory(BoardVO vo) {
		BoardCategoryVO boardCategoryVO = new BoardCategoryVO();
		boardCategoryVO.setId(vo.getBcId());
		return boardCategoryDAO.getBoardCategory(boardCategoryVO);
	}
}

