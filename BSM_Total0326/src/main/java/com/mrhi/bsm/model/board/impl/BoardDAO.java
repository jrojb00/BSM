package com.mrhi.bsm.model.board.impl;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.mrhi.bsm.model.board.BoardVO;

//DAO(Data Access Object)
@Repository //@Repository->�����
public class BoardDAO {
	@Autowired
	private SqlSessionTemplate mybatis;	
	//CRUD ����� �޼ҵ� ����
	//�� ���
	public void insertBoard(BoardVO vo) {
		System.out.println("===> mybatis�� insertBoard() ��� ó��");
		mybatis.insert("BoardDAO.insertBoard", vo);
	}

	//�� ����
	public void updateBoard(BoardVO vo) {
		System.out.println("===> mybatis�� updateBoard() ��� ó��");
		mybatis.update("BoardDAO.updateBoard", vo);
	}

	//�� ����
	public void deleteBoard(BoardVO vo) {
		System.out.println("===> mybatis�� deleteBoard() ��� ó��");
		mybatis.delete("BoardDAO.deleteBoard", vo);
	}

	//�� �� ��ȸ
	public BoardVO getBoard(BoardVO vo) {		
		System.out.println("===> mybatis�� getBoard() ��� ó��");
		return mybatis.selectOne("BoardDAO.getBoard", vo);	
	}

	//�� ��� ��ȸ
	public List<BoardVO> getBoardList(BoardVO vo) {
		System.out.println("===> mybatis�� getBoardList() ��� ó��");
		return mybatis.selectList("BoardDAO.getBoardList", vo);
	}
	
	public int getBoardCount(BoardVO vo) {
		System.out.println("===> mybatis�� getPageNumberList() ��� ó��");
		return mybatis.selectOne("BoardDAO.getBoardCount", vo);
	}
	
	public void updateCounter(BoardVO vo) {
		mybatis.update("BoardDAO.updateCounter", vo);
	}
	
}
