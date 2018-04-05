package com.mrhi.bsm.model.board.impl;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.mrhi.bsm.model.board.BoardCategoryVO;

//DAO(Data Access Object)
@Repository //@Repository->�����
public class BoardCategoryDAO {
	@Autowired
	private SqlSessionTemplate mybatis;	

	//�� �� ��ȸ
	public BoardCategoryVO getBoardCategory(BoardCategoryVO vo) {
		return mybatis.selectOne("BoardDAO.getBoardCategory", vo);	
	}	
}
