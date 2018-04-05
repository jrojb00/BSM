package com.mrhi.bsm.model.user.impl;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.mrhi.bsm.model.item.BuyItemVO;
import com.mrhi.bsm.model.user.FavoriteCategoryVO;
import com.mrhi.bsm.model.user.UserVO;


//������� ȸ���������� ȸ�������� �����ͺ��̽��� �Ѿ��
//�����ڰ� �̸� �����ϴ� Ŭ����
@Repository
public class UserDAO {
	@Autowired
	private SqlSessionTemplate mybatis;
	
	//ȸ�� ����
	public void insertUser(UserVO vo) {
		mybatis.insert("UserDAO.insertUser", vo);
	}
	
	//ȸ�� ���� ����
	public void updateInfo(UserVO vo) {
		mybatis.update("UserDAO.updateInfo", vo);
	}

	//ȸ�� ���� ȸ�� ����
	public void deleteUser(UserVO vo) {	
		mybatis.delete("UserDAO.deleteUser", vo);	
	}

	//ID�ߺ��˻� Ȯ�� �޼ҵ�
	public int checkID(UserVO vo) {
		return mybatis.selectOne("UserDAO.checkID", vo);	
	}
	
	//�α��� Ȯ��
	public UserVO LogIn(UserVO vo) {
		return mybatis.selectOne("UserDAO.LogIn", vo);
	}
	
	//ȸ�� ���� ����Ʈ ��������
	public List<UserVO> getUserList(UserVO vo) {
		return mybatis.selectList("UserDAO.getUserList", vo);		
	}

	//ȸ�� ���� ����
	public UserVO getMyInfo(UserVO vo) {
		return mybatis.selectOne("UserDAO.getMyInfo", vo);
	}
	
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
	
	//���̵� ã��
	public static String FindID(UserVO vo) {
		String id = null;
		String seleteSql = "select * from \"T_Member\" where name = ? and phonenumber = ?;";	
		return id;
	}
	
	//��� ��ȣ ã��
	public static boolean FindPassword(UserVO vo) {
		boolean logCK = false;
		String seleteSql = "select * from \"T_Member\" where id = ? and phonenumber = ?;";
		return logCK;
	}

	//��й�ȣ ����
	public static void updatePassword(String id, String password) {
		String updateSql = "update \"T_Member\" set password = ? where id = ?;";
	}
	
	//���� ���� ����
	public List<BuyItemVO> purchasingList(UserVO vo) {
		return mybatis.selectList("getPurchasingList", vo);
	}
}
