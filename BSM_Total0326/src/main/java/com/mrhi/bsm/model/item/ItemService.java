package com.mrhi.bsm.model.item;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import com.mrhi.bsm.model.user.UserVO;

public interface ItemService {
	public void insertItem(ItemVO vo) throws SQLException, IOException;
	public void updateItem(ItemVO vo);
	public void deleteItem(ItemVO vo);
	public ItemVO getItem(ItemVO vo);
	public List<ItemVO> getItemList(ItemVO vo);
	public UserVO buyItem(ItemVO buyItem, UserVO user);
	public String tendencyAnalysis(TendencyAnalysisVO tend) ;
	public int maxId();
	public List<ItemVO> getHitItem();
	public List<ItemVO> getNewItemList();
	public List<ItemCategoryVO> allCategory();
	public String getType(ItemVO vo);
	public List<BuyItemVO> getDetailedBuyItemList(BuyItemVO vo);
	public List<ItemVO> getItemListByType(ItemVO vo, UserVO user);
	public List<ItemVO> getMyFavorite(UserVO user);
	public ItemPageVO getItemPage(ItemVO vo, UserVO user);
	public ItemPageVO next();
	public ItemPageVO prev();
}
