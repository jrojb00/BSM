package com.mrhi.bsm.model.board.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mrhi.bsm.model.board.BoardPageVO;
import com.mrhi.bsm.model.board.BoardService;
import com.mrhi.bsm.model.board.BoardVO;

@Service("boardService")
public class BoardServiceImpl implements BoardService {
	@Autowired	
	private BoardDAO boardDAO;
	@Autowired	
	private BoardPageVO boardPage;
	
	public BoardServiceImpl() {
		System.out.println("BoardServiceImpl Bean 생성 중입니다...");
	}

	public void insertBoard(BoardVO vo) {
		boardDAO.insertBoard(vo);
	}

	@Override
	public void updateBoard(BoardVO vo) {
		boardDAO.updateBoard(vo);
	}

	@Override
	public void deleteBoard(BoardVO vo) {
		boardDAO.deleteBoard(vo);
	}

	@Override
	public BoardVO getBoard(BoardVO vo) {		
		BoardVO boardVO = boardDAO.getBoard(vo);
		System.out.println(vo.isModify());
		if (!vo.isModify() & !vo.isReplyModify()) {
			boardVO.incReadCnt();
			boardDAO.updateCounter(boardVO);
		}
		return boardVO;
	}
	
	@Override
	public List<BoardVO> getBoardList(BoardVO vo) {
		return boardDAO.getBoardList(vo);
	}
	
	@Override
	public BoardPageVO getBoardPage(BoardVO vo) {
		int pageNumber = vo.getPageNumber();
		int	boardListSize = boardDAO.getBoardCount(vo); 
		boardPage.init(pageNumber, boardListSize);
		return boardPage;
	}
	
	@Override
	public BoardPageVO next() {
		boardPage.nextPage();
		return boardPage;
	}
	
	@Override
	public BoardPageVO prev() {
		boardPage.prevPage();
		return boardPage;
	}
}
