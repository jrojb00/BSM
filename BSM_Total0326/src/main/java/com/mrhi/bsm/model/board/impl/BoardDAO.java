package com.mrhi.bsm.model.board.impl;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.mrhi.bsm.model.board.BoardVO;

//DAO(Data Access Object)
@Repository //@Repository->저장소
public class BoardDAO {
	@Autowired
	private SqlSessionTemplate mybatis;	
	//CRUD 기능의 메소드 구현
	//글 등록
	public void insertBoard(BoardVO vo) {
		System.out.println("===> mybatis로 insertBoard() 기능 처리");
		mybatis.insert("BoardDAO.insertBoard", vo);
	}

	//글 수정
	public void updateBoard(BoardVO vo) {
		System.out.println("===> mybatis로 updateBoard() 기능 처리");
		mybatis.update("BoardDAO.updateBoard", vo);
	}

	//글 삭제
	public void deleteBoard(BoardVO vo) {
		System.out.println("===> mybatis로 deleteBoard() 기능 처리");
		mybatis.delete("BoardDAO.deleteBoard", vo);
	}

	//글 상세 조회
	public BoardVO getBoard(BoardVO vo) {		
		System.out.println("===> mybatis로 getBoard() 기능 처리");
		return mybatis.selectOne("BoardDAO.getBoard", vo);	
	}

	//글 목록 조회
	public List<BoardVO> getBoardList(BoardVO vo) {
		System.out.println("===> mybatis로 getBoardList() 기능 처리");
		return mybatis.selectList("BoardDAO.getBoardList", vo);
	}
	
	public int getBoardCount(BoardVO vo) {
		System.out.println("===> mybatis로 getPageNumberList() 기능 처리");
		return mybatis.selectOne("BoardDAO.getBoardCount", vo);
	}
	
	public void updateCounter(BoardVO vo) {
		mybatis.update("BoardDAO.updateCounter", vo);
	}
	
}
