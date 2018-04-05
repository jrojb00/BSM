package com.mrhi.bsm.model.item.impl;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mrhi.bsm.model.board.BoardPageVO;
import com.mrhi.bsm.model.board.BoardVO;
import com.mrhi.bsm.model.item.BuyItemVO;
import com.mrhi.bsm.model.item.ItemCategoryVO;
import com.mrhi.bsm.model.item.ItemPageVO;
import com.mrhi.bsm.model.item.ItemService;
import com.mrhi.bsm.model.item.ItemVO;
import com.mrhi.bsm.model.item.TendencyAnalysisVO;
import com.mrhi.bsm.model.keyword.impl.KeywordServiceImpl;
import com.mrhi.bsm.model.user.UserVO;
import com.mrhi.bsm.model.user.impl.UserServiceImpl;

@Service("itemService")
public class ItemServiceImpl implements ItemService {
	@Autowired
	private ItemDAO ItemDAO;
	@Autowired
	private KeywordServiceImpl keywordService;
	@Autowired
	private UserServiceImpl userService;
	@Autowired	
	private ItemPageVO itemPage;
	
	public ItemServiceImpl() {		
	}
	
	@Override
	public void insertItem(ItemVO vo) throws SQLException, IOException {
		ItemDAO.insertItem(vo);
		vo.setType(getType(vo));
		keywordService.addKeywordList(vo);
	}

	@Override
	public void updateItem(ItemVO vo) {
		ItemDAO.updateItem(vo);
		vo.setType(getType(vo));
		keywordService.deleteKeywordMapping(vo);	//기존의 키워드와 mapping 된 모든 것을 삭제
		keywordService.addKeywordList(vo);			//새로운 키워드 등록 및 mapping 연결
	}

	@Override
	public void deleteItem(ItemVO vo) {
		//상품 삭제 여부와 상관 없이 키워드 매핑을 삭제
		keywordService.deleteKeywordMapping(vo);
		//상품의 판매 이력이 있다면 삭제는 불가능 alive만 false
		//없다면 삭제
		if (ItemDAO.isSoldItem(vo) > 0) {
			vo.setAlive(false);
			ItemDAO.updateAlive(vo);		
		} else {
			ItemDAO.deleteItem(vo);			
		}
	}

	@Override
	public ItemVO getItem(ItemVO vo) {
		return ItemDAO.getItem(vo);
	}
	
	@Override
	public int maxId() {
		return ItemDAO.maxId();
	}

	@Override
	public List<ItemVO> getItemList(ItemVO vo) {
		return ItemDAO.getItemList(vo);
	}
	
	@Override
	public List<ItemVO> getNewItemList() {
		ItemVO vo = new ItemVO();
		return ItemDAO.getNewItemList(vo);
	}
	
	@Override
	public List<ItemCategoryVO> allCategory() {
		List<ItemCategoryVO> allCategory = ItemDAO.allCategory();
		List<ItemCategoryVO> newAllCategory = new ArrayList<>();
		for (ItemCategoryVO category : allCategory) {
			category.setType(category.getType().toUpperCase());
			newAllCategory.add(category);
		}
		return newAllCategory;
	}
	
	@Override
	public List<ItemVO> getHitItem() {
		ItemVO vo = new ItemVO();
		return ItemDAO.getHitItem(vo);
	}
	
	@Override
	public UserVO buyItem(ItemVO buyItem, UserVO user) {
		//유저가 산 상품 등록
		BuyItemVO vo = new BuyItemVO(user.getId(), buyItem.getId(), buyItem.getSalesQuantity());
		ItemDAO.insertBuyItem(vo);
		//관심 상품 등록
		return userService.addMyFavoriteList(user, getItem(buyItem).getIcId());
	}
	
	@Override
	public List<BuyItemVO> getDetailedBuyItemList(BuyItemVO vo) {
		return ItemDAO.getDetailedBuyItemList(vo);
	}
	
	@Override
	public String getType(ItemVO vo) {
		String itemType = ItemDAO.getType(vo);
		return itemType;
	}
	
	@Override
	public List<ItemVO> getItemListByType(ItemVO vo, UserVO user) {
		if ("new".equals(vo.getType()))
			return ItemDAO.getNewItemList(vo);
		else if ("best".equals(vo.getType()))
			return ItemDAO.getHitItem(vo);
		else if ("favorite".equals(vo.getType())) {
			return ItemDAO.getMyFavoriteItemList(user);
		} else
			return ItemDAO.getItemListByType(vo);			
	}
	
	@Override
	public ItemPageVO getItemPage(ItemVO vo, UserVO user) {
		int pageNumber = vo.getPageNumber();
		int	itemListSize;
		if ("new".equals(vo.getType())) {
			itemListSize = ItemDAO.itemListCount();			
		} else if ("best".equals(vo.getType())) {
			itemListSize = ItemDAO.hitItemCount();
		} else if ("favorite".equals(vo.getType())) {
			user.setPageNumber(vo.getPageNumber());
			itemListSize = ItemDAO.favoriteItemListCount(user);
		} else {
			itemListSize = ItemDAO.getItemListByTypeCount(vo);
		}
		itemPage.init(pageNumber, itemListSize);
		return itemPage;
	}
	
	@Override
	public ItemPageVO next() {
		itemPage.nextPage();
		return itemPage;
	}
	
	@Override
	public ItemPageVO prev() {
		itemPage.prevPage();
		return itemPage;
	}
	
	@Override
	public List<ItemVO> getMyFavorite(UserVO user) {
		ItemVO vo = new ItemVO();
		List<ItemVO> newItemList = ItemDAO.getNewItemList(vo);
		List<ItemVO> myFavorite =  ItemDAO.getMyFavoriteItemList(user);
		if(user.getMyFavoriteList() == null) {
			return newItemList;
		}
		for(ItemVO hitItem : newItemList) {
			if(myFavorite.size() < 5) {
				myFavorite.add(hitItem);
			}else break;
		}
		return myFavorite;
	}
	
	@Override
	public String tendencyAnalysis(TendencyAnalysisVO tend) {
		String categorize = tend.getCategorize();
		if (categorize.equals("birth")) {					//나이별 분석(막대 혹은 타원그래프)
			System.out.println("나이별 분석");
			List<BuyItemVO> ageAnalysis = ItemDAO.ageAnalysis(tend);
			return stickAnalysis(ageAnalysis, "나이");
		} else if (categorize.equals("gender")) {			//성별별 분석(막대 혹은 타원그래프)
			System.out.println("성별별 분석");
			List<BuyItemVO> genderAnalysis = ItemDAO.genderAnalysis(tend);
			return stickAnalysis(genderAnalysis, "성별");
		} else if (categorize.equals("type")) {				//타입별 분석(막대 혹은 타원그래프)
			System.out.println("타입별 분석");
			List<BuyItemVO> typeAnalysis = ItemDAO.typeAnalysis(tend);
			return stickAnalysis(typeAnalysis, "타입");
		} else if (categorize.equals("days")) {				//일별 분석(꺾은선 그래프)
			System.out.println("일별 분석");
			List<BuyItemVO> daysAnalysis = ItemDAO.daysAnalysis(tend);
			return lineAnalysis(daysAnalysis, "일");
		} else if (categorize.equals("months")) {			//월별 분석(꺾은선 그래프)
			List<BuyItemVO> monthsAnalysis = ItemDAO.monthsAnalysis(tend);
			return lineAnalysis(monthsAnalysis, "월");
		} else {											//연별 분석(꺾은선 그래프)
			List<BuyItemVO> yearsAnalysis = ItemDAO.yearsAnalysis(tend);
			return lineAnalysis(yearsAnalysis, "년");
		}
	}
	
	/* 분석 데이터(막대, 타원 그래프)
	 * 막대 그래프 데이터 형식
	 * [['나이대별',''나이'],
	 * ['20대', 43],
	 * ['30대', 15],
	 * ['40대', 35]]
	 **/
	public String stickAnalysis(List<BuyItemVO> analysis, String analysisType) {
		StringBuilder strB = new StringBuilder();
		String[] colors = {"skyblue", "hotpink", "navy", "red", "blue"};
		int i = 0;
		if (analysisType == "타입") {
			strB.append("[['" + analysisType + "별 매출 분석', '" + analysisType + "']");		//타입일때 Heading 설정
		} else {
			strB.append("[['" + analysisType + "별 매출 분석', '" + analysisType + "', {\"role\":'style'}]");		//타입이 아닐때 Heading 설정			
		}
		for (BuyItemVO buyItem : analysis) {
			if (analysisType == "나이") {
				strB.append(", ['" + buyItem.getBirth() + "', " + buyItem.getSalesQuantity() + 
						", '" + colors[i] + "']");				
			} else if (analysisType == "성별") {
				strB.append(", ['" + buyItem.getGender() + "', " + buyItem.getSalesQuantity() + 
						", '" + colors[i] + "']");
			} else if (analysisType == "타입") {
				strB.append(", ['" + buyItem.getType() + "', " + buyItem.getSalesQuantity() + "]");
			}
			i++;
		}
		strB.append("]");
		return strB.toString();
	}
	
	/* 분석 데이터(꺾은선 그래프)
	 * 꺾은선 그래프 데이터 형식
	 * [['구매일자', '1975', '1985', '1991', '2000'], 
	 * ['2018-02-04', 0, 1, 0, 6],
	 * ['2018-02-05', 3, 0, 0, 0], 
	 * ['2018-02-09', 0, 0, 9, 0],
	 * ['2018-02-12', 3, 2, 2, 6]]  
	 **/
	public String lineAnalysis(List<BuyItemVO> analysis, String analysisType) {
		StringBuilder strB = new StringBuilder();
		strB.append("[['구매일자', 'users']");		//Heading 설정
		for (BuyItemVO buyItem : analysis) {
			if (analysisType == "일") {
				strB.append(", ['" + buyItem.getDay() + "', " + buyItem.getSalesQuantity() + "]");
			} else if (analysisType == "월") {
				strB.append(", ['" + buyItem.getMonth() + analysisType + "', " + buyItem.getSalesQuantity() + "]");
			} else if (analysisType == "년") {
				strB.append(", ['" + buyItem.getYear() + analysisType + "', " + buyItem.getSalesQuantity() + "]");
			}
		}
		strB.append("]");
		return strB.toString();
	}
}