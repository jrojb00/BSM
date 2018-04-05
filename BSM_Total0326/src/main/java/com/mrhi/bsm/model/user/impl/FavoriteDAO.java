package com.mrhi.bsm.model.user.impl;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.mrhi.bsm.model.user.FavoriteCategoryVO;
import com.mrhi.bsm.model.user.UserVO;

@Repository
public class FavoriteDAO {
	@Autowired
	private SqlSessionTemplate mybatis;
	
	//관심 사항 등록
	public void insertMyFavorite(FavoriteCategoryVO vo) {
		mybatis.insert("UserDAO.insertMyFavorite", vo);
	}

	//관심 사항 삭제
	public void deleteMyFavorite(FavoriteCategoryVO vo) {	
		mybatis.delete("UserDAO.deleteMyFavorite", vo);	
	}

	//관심 사항 중복 검사
	public int checkMyFavorite(FavoriteCategoryVO vo) {
		return mybatis.selectOne("UserDAO.checkMyFavorite", vo);	
	}

	//관심 사항 보기
	public List<FavoriteCategoryVO> getMyFavorite(UserVO vo) {
		return mybatis.selectList("UserDAO.getMyFavorite", vo);
	}
	
}
