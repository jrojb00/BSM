package com.mrhi.bsm.model.user.impl;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mrhi.bsm.framwork.SecurityUtil;
import com.mrhi.bsm.model.item.BuyItemVO;
import com.mrhi.bsm.model.user.FavoriteCategoryVO;
import com.mrhi.bsm.model.user.UserService;
import com.mrhi.bsm.model.user.UserVO;

@Service("userService")
public class UserServiceImpl implements UserService {
	@Autowired
	private UserDAO userDAO;
	@Autowired
	private FavoriteServiceImpl favoriteServiceImpl;
	
	public UserServiceImpl() {
		System.out.println("UserServiceImpl 생성");
	}

	@Override
	public UserVO LogIn(UserVO vo) {
		//암호화 작업
		securityPassword(vo);
		//로그인 되었을 시 유저
		UserVO user = userDAO.LogIn(vo);
		if (user != null) {
			//유저 객체에 관심 사항 담아주기
			setMyFavoriteList(user);			
		}
		List<BuyItemVO> purchasingList = user.getPurchasingList();
		Collections.sort(purchasingList, new RecentCompare());
		user.setPurchasingList(purchasingList);
		return user;
	}

	@Override
	public void insertUser(UserVO vo) {
		//암호화 작업
		securityPassword(vo);
		userDAO.insertUser(vo);
	}

	@Override
	public int checkID(UserVO vo) {
		return userDAO.checkID(vo);
	}

	@Override
	public UserVO getMyInfo(UserVO vo) {
		UserVO user = userDAO.getMyInfo(vo);
		if (user != null) {
			//유저 객체에 관심 사항 담아주기
			setMyFavoriteList(user);
		}
		List<BuyItemVO> purchasingList = user.getPurchasingList();
		Collections.sort(purchasingList, new RecentCompare());
		user.setPurchasingList(purchasingList);
		return user;
	}
	
	@Override
	public void deleteUser(UserVO vo) {
		//정보 변경 전 유저가 가지고 있는 관심 사항 삭제
		favoriteServiceImpl.deleteMyFavorite(vo.getId());
		userDAO.deleteUser(vo);
	}
	
	@Override
	public void updateInfo(UserVO vo) {
		//정보 변경 전 유저가 가지고 있는 관심 사항 삭제
		favoriteServiceImpl.deleteMyFavorite(vo.getId());
		Integer[] myFavoriteList = vo.getMyFavoriteId();
		if (myFavoriteList != null) {
			for (int myFavorite : myFavoriteList) {
				FavoriteCategoryVO fcVO = new FavoriteCategoryVO(vo.getId(), myFavorite);
				favoriteServiceImpl.insertMyFavorite(fcVO);	//유저의 관심 사항 등록
			}			
		}
		userDAO.updateInfo(vo);
	}

	@Override
	public List<UserVO> getUserList(UserVO vo) {
		return userDAO.getUserList(vo);
	}
	
	@Override
	public List<BuyItemVO> purchasingList(UserVO vo) {
		return userDAO.purchasingList(vo);
	}
	
	//비밀 번호 암호화 작업
	public void securityPassword(UserVO vo) {
		SecurityUtil security = new SecurityUtil();
		String password = security.encryptSHA256(vo.getLoginPassword());
		vo.setLoginPassword(password);
	}
	
	//유저 객체에 관심 사항 담아주기
	public void setMyFavoriteList(UserVO vo) {
		//유저의 관심 사항 가져오기
		List<FavoriteCategoryVO> myFavoriteList = favoriteServiceImpl.getMyFavorite(vo);
		//결과를 객체의 favoriteList에 담아준다
		vo.setMyFavoriteList(myFavoriteList);			
	}

	//유저의 관심상품 등록
	public UserVO addMyFavoriteList(UserVO vo, int itemIcId) {
		FavoriteCategoryVO fcVO = new FavoriteCategoryVO(vo.getId(), itemIcId);
		//산 상품과 관심 상품 간 중복 검색(중복이라면 1 아니면 0)
		//중복이 아니라면 관심 상품 등록
		if (favoriteServiceImpl.checkMyFavorite(fcVO) == 0) {
			favoriteServiceImpl.insertMyFavorite(fcVO);		//유저의 관심 사항 등록
		}
		return getMyInfo(vo);
	}
	
	//최신순 정렬
	class RecentCompare implements Comparator<BuyItemVO> {
		@Override
		public int compare(BuyItemVO obj1, BuyItemVO obj2) {
			return obj2.getBuyTime().compareTo(obj1.getBuyTime());
		}
	}
}
