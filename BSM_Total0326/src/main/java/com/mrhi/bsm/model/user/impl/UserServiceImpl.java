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
		System.out.println("UserServiceImpl ����");
	}

	@Override
	public UserVO LogIn(UserVO vo) {
		//��ȣȭ �۾�
		securityPassword(vo);
		//�α��� �Ǿ��� �� ����
		UserVO user = userDAO.LogIn(vo);
		if (user != null) {
			//���� ��ü�� ���� ���� ����ֱ�
			setMyFavoriteList(user);			
		}
		List<BuyItemVO> purchasingList = user.getPurchasingList();
		Collections.sort(purchasingList, new RecentCompare());
		user.setPurchasingList(purchasingList);
		return user;
	}

	@Override
	public void insertUser(UserVO vo) {
		//��ȣȭ �۾�
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
			//���� ��ü�� ���� ���� ����ֱ�
			setMyFavoriteList(user);
		}
		List<BuyItemVO> purchasingList = user.getPurchasingList();
		Collections.sort(purchasingList, new RecentCompare());
		user.setPurchasingList(purchasingList);
		return user;
	}
	
	@Override
	public void deleteUser(UserVO vo) {
		//���� ���� �� ������ ������ �ִ� ���� ���� ����
		favoriteServiceImpl.deleteMyFavorite(vo.getId());
		userDAO.deleteUser(vo);
	}
	
	@Override
	public void updateInfo(UserVO vo) {
		//���� ���� �� ������ ������ �ִ� ���� ���� ����
		favoriteServiceImpl.deleteMyFavorite(vo.getId());
		Integer[] myFavoriteList = vo.getMyFavoriteId();
		if (myFavoriteList != null) {
			for (int myFavorite : myFavoriteList) {
				FavoriteCategoryVO fcVO = new FavoriteCategoryVO(vo.getId(), myFavorite);
				favoriteServiceImpl.insertMyFavorite(fcVO);	//������ ���� ���� ���
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
	
	//��� ��ȣ ��ȣȭ �۾�
	public void securityPassword(UserVO vo) {
		SecurityUtil security = new SecurityUtil();
		String password = security.encryptSHA256(vo.getLoginPassword());
		vo.setLoginPassword(password);
	}
	
	//���� ��ü�� ���� ���� ����ֱ�
	public void setMyFavoriteList(UserVO vo) {
		//������ ���� ���� ��������
		List<FavoriteCategoryVO> myFavoriteList = favoriteServiceImpl.getMyFavorite(vo);
		//����� ��ü�� favoriteList�� ����ش�
		vo.setMyFavoriteList(myFavoriteList);			
	}

	//������ ���ɻ�ǰ ���
	public UserVO addMyFavoriteList(UserVO vo, int itemIcId) {
		FavoriteCategoryVO fcVO = new FavoriteCategoryVO(vo.getId(), itemIcId);
		//�� ��ǰ�� ���� ��ǰ �� �ߺ� �˻�(�ߺ��̶�� 1 �ƴϸ� 0)
		//�ߺ��� �ƴ϶�� ���� ��ǰ ���
		if (favoriteServiceImpl.checkMyFavorite(fcVO) == 0) {
			favoriteServiceImpl.insertMyFavorite(fcVO);		//������ ���� ���� ���
		}
		return getMyInfo(vo);
	}
	
	//�ֽż� ����
	class RecentCompare implements Comparator<BuyItemVO> {
		@Override
		public int compare(BuyItemVO obj1, BuyItemVO obj2) {
			return obj2.getBuyTime().compareTo(obj1.getBuyTime());
		}
	}
}
