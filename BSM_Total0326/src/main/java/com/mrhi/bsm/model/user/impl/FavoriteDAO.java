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
	
	//���� ���� ���
	public void insertMyFavorite(FavoriteCategoryVO vo) {
		mybatis.insert("UserDAO.insertMyFavorite", vo);
	}

	//���� ���� ����
	public void deleteMyFavorite(FavoriteCategoryVO vo) {	
		mybatis.delete("UserDAO.deleteMyFavorite", vo);	
	}

	//���� ���� �ߺ� �˻�
	public int checkMyFavorite(FavoriteCategoryVO vo) {
		return mybatis.selectOne("UserDAO.checkMyFavorite", vo);	
	}

	//���� ���� ����
	public List<FavoriteCategoryVO> getMyFavorite(UserVO vo) {
		return mybatis.selectList("UserDAO.getMyFavorite", vo);
	}
	
}
