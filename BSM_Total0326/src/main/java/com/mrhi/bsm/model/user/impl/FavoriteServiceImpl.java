package com.mrhi.bsm.model.user.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mrhi.bsm.model.user.FavoriteCategoryVO;
import com.mrhi.bsm.model.user.UserVO;

@Service("favoriteService")
public class FavoriteServiceImpl {
	@Autowired
	private FavoriteDAO favoriteDAO;
	
	public void insertMyFavorite(FavoriteCategoryVO vo) {
		favoriteDAO.insertMyFavorite(vo);
	}

	//관심 사항 삭제
	public void deleteMyFavorite(int uId) {
		FavoriteCategoryVO vo = new FavoriteCategoryVO(uId);
		favoriteDAO.deleteMyFavorite(vo);	
	}

	//관심 사항 중복 검사
	public int checkMyFavorite(FavoriteCategoryVO vo) {
		return favoriteDAO.checkMyFavorite(vo);	
	}

	//관심 사항 보기
	public List<FavoriteCategoryVO> getMyFavorite(UserVO vo) {
		return favoriteDAO.getMyFavorite(vo);
	}
}
