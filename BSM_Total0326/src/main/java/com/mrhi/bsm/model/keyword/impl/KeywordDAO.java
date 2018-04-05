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

	//기존의 키워드와 겹치는 게 있는지 검색
	public List<KeywordVO> getList(List<KeywordVO> vo) {
		return mybatis.selectList("KeywordDAO.findKeptKeyword", vo);
	}

	//키워드를 가지고 ItemVO 검색
	public List<ItemVO> searchKeyword(List<KeywordVO> keywordList) {
		return mybatis.selectList("KeywordDAO.searchLikeKeyword", keywordList);
	}
	
	//검색 결과의 갯수
	public int searchItemListCount(List<KeywordVO> keywordList) {
		return mybatis.selectOne("KeywordDAO.searchItemListCount", keywordList);
	}

	//인기 검색어
	public List<KeywordVO> hitKeyword() {
		return mybatis.selectList("KeywordDAO.hitKeyword");
	}

	//상품 삭제시 키워드매핑 삭제
	public void deleteKeywordMapping(ItemVO vo) {
		mybatis.delete("KeywordDAO.deleteKeywordMapping", vo);
	}	
}

