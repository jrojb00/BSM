package com.mrhi.bsm.model.user;

import java.util.List;

import com.mrhi.bsm.model.item.BuyItemVO;

public interface UserService {
	//CRUD 기능의 메소드 구현
	//회원 등록
	public void insertUser(UserVO vo);
	//ID 중복검사
	public int checkID(UserVO vo);
	//회원 로그인
	public UserVO LogIn(UserVO vo);
	//회원 정보 보기
	public UserVO getMyInfo(UserVO vo);
	//회원 정보 수정
	public void updateInfo(UserVO vo);
	//회원 탈퇴
	public void deleteUser(UserVO vo);
	//회원 정보 리스트 받아오기
	public List<UserVO> getUserList(UserVO vo);
	//구매 내역
	public List<BuyItemVO> purchasingList(UserVO vo);
}
