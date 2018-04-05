package com.mrhi.bsm.model.item.impl;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.mrhi.bsm.model.item.BuyItemVO;
import com.mrhi.bsm.model.item.ItemCategoryVO;
import com.mrhi.bsm.model.item.ItemVO;
import com.mrhi.bsm.model.item.TendencyAnalysisVO;
import com.mrhi.bsm.model.user.UserVO;

@Repository
public class ItemDAO {
	@Autowired
	private SqlSessionTemplate mybatis;
	
	public void insertItem(ItemVO vo) {
		mybatis.insert("ItemDAO.insertItem", vo);
	}
	
	public void updateItem(ItemVO vo) {
		mybatis.update("ItemDAO.updateItem", vo);
	}
	
	public void deleteItem(ItemVO vo) {
		mybatis.delete("ItemDAO.deleteItem", vo);
	}
	
	public String getType(ItemVO vo) {
		return mybatis.selectOne("ItemDAO.getType", vo);
	}
	
	public int maxId() {
		return mybatis.selectOne("ItemDAO.maxId");
	}
		
	public ItemVO getItem(ItemVO vo) {
		return mybatis.selectOne("ItemDAO.getItem", vo);
	}
	
	public List<ItemVO> getItemList(ItemVO vo) {
		return mybatis.selectList("ItemDAO.getItemList", vo);
	}
	
	public List<ItemCategoryVO> allCategory() {
		return mybatis.selectList("ItemDAO.allCategory");
	}
	
	public void insertBuyItem(BuyItemVO vo) {
		//구매 상품 등록
		mybatis.insert("ItemDAO.insertBuyItem", vo);
		//구매한 상품 수량 변경
		mybatis.update("ItemDAO.buyItem", vo);
	}
	
	public List<ItemVO> getNewItemList(ItemVO vo) {
		return mybatis.selectList("ItemDAO.getNewItemList", vo);
	}
	
	public List<ItemVO> getHitItem(ItemVO vo) {
		return mybatis.selectList("ItemDAO.getHitItem", vo);
	}
	
	public List<BuyItemVO> genderAnalysis(TendencyAnalysisVO vo) {
		return mybatis.selectList("ItemDAO.genderAnalysis", vo);
	}
	
	public List<BuyItemVO> typeAnalysis(TendencyAnalysisVO vo) {
		return mybatis.selectList("ItemDAO.typeAnalysis", vo);
	}
	
	public List<BuyItemVO> ageAnalysis(TendencyAnalysisVO vo) {
		return mybatis.selectList("ItemDAO.ageAnalysis", vo);
	}

	public List<BuyItemVO> daysAnalysis(TendencyAnalysisVO vo) {
		return mybatis.selectList("ItemDAO.daysAnalysis", vo);
	}
	
	public List<BuyItemVO> monthsAnalysis(TendencyAnalysisVO vo) {
		return mybatis.selectList("ItemDAO.monthsAnalysis", vo);
	}
	
	public List<BuyItemVO> yearsAnalysis(TendencyAnalysisVO vo) {
		return mybatis.selectList("ItemDAO.yearsAnalysis", vo);
	}
	
	public List<BuyItemVO> getDetailedBuyItemList(BuyItemVO vo) {
		return mybatis.selectList("ItemDAO.getDetailedBuyItemList", vo);
	}

	public List<ItemVO> getItemListByType(ItemVO vo) {
		return mybatis.selectList("ItemDAO.getItemListByType", vo);
	}
	
	public List<ItemVO> getMyFavoriteItemList(UserVO vo) {
		return mybatis.selectList("ItemDAO.getMyFavoriteItemList", vo);
	}
	
	public void updateAlive(ItemVO vo) {
		mybatis.update("ItemDAO.updateAlive", vo);		
	}
	
	public int itemListCount() {
		return 	mybatis.selectOne("ItemDAO.itemListCount");
	}

	public int hitItemCount() {
		return 	mybatis.selectOne("ItemDAO.hitItemCount");
	}
	
	public int favoriteItemListCount(UserVO vo) {
		return 	mybatis.selectOne("ItemDAO.favoriteItemListCount", vo);
	}
	
	public int getItemListByTypeCount(ItemVO vo) {
		return 	mybatis.selectOne("ItemDAO.getItemListByTypeCount", vo);
	}
	
	public int isSoldItem(ItemVO vo) {
		return 	mybatis.selectOne("ItemDAO.isSoldItem", vo);
	}
}