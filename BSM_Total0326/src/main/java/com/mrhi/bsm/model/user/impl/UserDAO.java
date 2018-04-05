package com.mrhi.bsm.model.user.impl;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.mrhi.bsm.model.item.BuyItemVO;
import com.mrhi.bsm.model.user.FavoriteCategoryVO;
import com.mrhi.bsm.model.user.UserVO;


//사용자의 회원가입으로 회원정보가 데이터베이스에 넘어가고
//관리자가 이를 관리하는 클래스
@Repository
public class UserDAO {
	@Autowired
	private SqlSessionTemplate mybatis;
	
	//회원 가입
	public void insertUser(UserVO vo) {
		mybatis.insert("UserDAO.insertUser", vo);
	}
	
	//회원 정보 변경
	public void updateInfo(UserVO vo) {
		mybatis.update("UserDAO.updateInfo", vo);
	}

	//회원 관리 회원 삭제
	public void deleteUser(UserVO vo) {	
		mybatis.delete("UserDAO.deleteUser", vo);	
	}

	//ID중복검사 확인 메소드
	public int checkID(UserVO vo) {
		return mybatis.selectOne("UserDAO.checkID", vo);	
	}
	
	//로그인 확인
	public UserVO LogIn(UserVO vo) {
		return mybatis.selectOne("UserDAO.LogIn", vo);
	}
	
	//회원 정보 리스트 가져오기
	public List<UserVO> getUserList(UserVO vo) {
		return mybatis.selectList("UserDAO.getUserList", vo);		
	}

	//회원 정보 보기
	public UserVO getMyInfo(UserVO vo) {
		return mybatis.selectOne("UserDAO.getMyInfo", vo);
	}
	
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
	
	//아이디 찾기
	public static String FindID(UserVO vo) {
		String id = null;
		String seleteSql = "select * from \"T_Member\" where name = ? and phonenumber = ?;";	
		return id;
	}
	
	//비밀 번호 찾기
	public static boolean FindPassword(UserVO vo) {
		boolean logCK = false;
		String seleteSql = "select * from \"T_Member\" where id = ? and phonenumber = ?;";
		return logCK;
	}

	//비밀번호 변경
	public static void updatePassword(String id, String password) {
		String updateSql = "update \"T_Member\" set password = ? where id = ?;";
	}
	
	//구매 내역 보기
	public List<BuyItemVO> purchasingList(UserVO vo) {
		return mybatis.selectList("getPurchasingList", vo);
	}
}
