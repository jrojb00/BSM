package com.mrhi.bsm.model.keyword.impl;

import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.mrhi.bsm.model.item.ItemVO;
import com.mrhi.bsm.model.keyword.KeywordVO;
import com.mrhi.bsm.model.keyword.MappingKeywordVO;

@Repository
public class KeywordDAO {
	@Autowired
	private SqlSessionTemplate mybatis;

	public void insertKeyword(KeywordVO vo) {
		mybatis.insert("KeywordDAO.insertKeyword", vo);
	}

	public void insertMappingKeyword(MappingKeywordVO vo) {
		mybatis.insert("KeywordDAO.insertMappingKeyword", vo);
	}

	public int lastItemId() {
		return mybatis.selectOne("KeywordDAO.lastItemId");
	}

	public int lastKeywordId() {
		return mybatis.selectOne("KeywordDAO.lastKeywordId");
	}

	public KeywordVO matchingKeyword(KeywordVO vo) {
		return mybatis.selectOne("KeywordDAO.matchingKeyword", vo);
	}

	//������ Ű����� ��ġ�� �� �ִ��� �˻�
	public List<KeywordVO> getList(List<KeywordVO> vo) {
		return mybatis.selectList("KeywordDAO.findKeptKeyword", vo);
	}

	//Ű���带 ������ ItemVO �˻�
	public List<ItemVO> searchKeyword(List<KeywordVO> keywordList) {
		return mybatis.selectList("KeywordDAO.searchLikeKeyword", keywordList);
	}
	
	//�˻� ����� ����
	public int searchItemListCount(List<KeywordVO> keywordList) {
		return mybatis.selectOne("KeywordDAO.searchItemListCount", keywordList);
	}

	//�α� �˻���
	public List<KeywordVO> hitKeyword() {
		return mybatis.selectList("KeywordDAO.hitKeyword");
	}

	//��ǰ ������ Ű������� ����
	public void deleteKeywordMapping(ItemVO vo) {
		mybatis.delete("KeywordDAO.deleteKeywordMapping", vo);
	}	
}

