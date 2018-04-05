package com.mrhi.bsm.model.user;

import java.util.List;

import com.mrhi.bsm.model.item.BuyItemVO;

public interface UserService {
	//CRUD ����� �޼ҵ� ����
	//ȸ�� ���
	public void insertUser(UserVO vo);
	//ID �ߺ��˻�
	public int checkID(UserVO vo);
	//ȸ�� �α���
	public UserVO LogIn(UserVO vo);
	//ȸ�� ���� ����
	public UserVO getMyInfo(UserVO vo);
	//ȸ�� ���� ����
	public void updateInfo(UserVO vo);
	//ȸ�� Ż��
	public void deleteUser(UserVO vo);
	//ȸ�� ���� ����Ʈ �޾ƿ���
	public List<UserVO> getUserList(UserVO vo);
	//���� ����
	public List<BuyItemVO> purchasingList(UserVO vo);
}
