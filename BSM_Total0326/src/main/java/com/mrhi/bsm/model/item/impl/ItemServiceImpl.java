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
		keywordService.deleteKeywordMapping(vo);	//������ Ű����� mapping �� ��� ���� ����
		keywordService.addKeywordList(vo);			//���ο� Ű���� ��� �� mapping ����
	}

	@Override
	public void deleteItem(ItemVO vo) {
		//��ǰ ���� ���ο� ��� ���� Ű���� ������ ����
		keywordService.deleteKeywordMapping(vo);
		//��ǰ�� �Ǹ� �̷��� �ִٸ� ������ �Ұ��� alive�� false
		//���ٸ� ����
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
		//������ �� ��ǰ ���
		BuyItemVO vo = new BuyItemVO(user.getId(), buyItem.getId(), buyItem.getSalesQuantity());
		ItemDAO.insertBuyItem(vo);
		//���� ��ǰ ���
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
		if (categorize.equals("birth")) {					//���̺� �м�(���� Ȥ�� Ÿ���׷���)
			System.out.println("���̺� �м�");
			List<BuyItemVO> ageAnalysis = ItemDAO.ageAnalysis(tend);
			return stickAnalysis(ageAnalysis, "����");
		} else if (categorize.equals("gender")) {			//������ �м�(���� Ȥ�� Ÿ���׷���)
			System.out.println("������ �м�");
			List<BuyItemVO> genderAnalysis = ItemDAO.genderAnalysis(tend);
			return stickAnalysis(genderAnalysis, "����");
		} else if (categorize.equals("type")) {				//Ÿ�Ժ� �м�(���� Ȥ�� Ÿ���׷���)
			System.out.println("Ÿ�Ժ� �м�");
			List<BuyItemVO> typeAnalysis = ItemDAO.typeAnalysis(tend);
			return stickAnalysis(typeAnalysis, "Ÿ��");
		} else if (categorize.equals("days")) {				//�Ϻ� �м�(������ �׷���)
			System.out.println("�Ϻ� �м�");
			List<BuyItemVO> daysAnalysis = ItemDAO.daysAnalysis(tend);
			return lineAnalysis(daysAnalysis, "��");
		} else if (categorize.equals("months")) {			//���� �м�(������ �׷���)
			List<BuyItemVO> monthsAnalysis = ItemDAO.monthsAnalysis(tend);
			return lineAnalysis(monthsAnalysis, "��");
		} else {											//���� �м�(������ �׷���)
			List<BuyItemVO> yearsAnalysis = ItemDAO.yearsAnalysis(tend);
			return lineAnalysis(yearsAnalysis, "��");
		}
	}
	
	/* �м� ������(����, Ÿ�� �׷���)
	 * ���� �׷��� ������ ����
	 * [['���̴뺰',''����'],
	 * ['20��', 43],
	 * ['30��', 15],
	 * ['40��', 35]]
	 **/
	public String stickAnalysis(List<BuyItemVO> analysis, String analysisType) {
		StringBuilder strB = new StringBuilder();
		String[] colors = {"skyblue", "hotpink", "navy", "red", "blue"};
		int i = 0;
		if (analysisType == "Ÿ��") {
			strB.append("[['" + analysisType + "�� ���� �м�', '" + analysisType + "']");		//Ÿ���϶� Heading ����
		} else {
			strB.append("[['" + analysisType + "�� ���� �м�', '" + analysisType + "', {\"role\":'style'}]");		//Ÿ���� �ƴҶ� Heading ����			
		}
		for (BuyItemVO buyItem : analysis) {
			if (analysisType == "����") {
				strB.append(", ['" + buyItem.getBirth() + "', " + buyItem.getSalesQuantity() + 
						", '" + colors[i] + "']");				
			} else if (analysisType == "����") {
				strB.append(", ['" + buyItem.getGender() + "', " + buyItem.getSalesQuantity() + 
						", '" + colors[i] + "']");
			} else if (analysisType == "Ÿ��") {
				strB.append(", ['" + buyItem.getType() + "', " + buyItem.getSalesQuantity() + "]");
			}
			i++;
		}
		strB.append("]");
		return strB.toString();
	}
	
	/* �м� ������(������ �׷���)
	 * ������ �׷��� ������ ����
	 * [['��������', '1975', '1985', '1991', '2000'], 
	 * ['2018-02-04', 0, 1, 0, 6],
	 * ['2018-02-05', 3, 0, 0, 0], 
	 * ['2018-02-09', 0, 0, 9, 0],
	 * ['2018-02-12', 3, 2, 2, 6]]  
	 **/
	public String lineAnalysis(List<BuyItemVO> analysis, String analysisType) {
		StringBuilder strB = new StringBuilder();
		strB.append("[['��������', 'users']");		//Heading ����
		for (BuyItemVO buyItem : analysis) {
			if (analysisType == "��") {
				strB.append(", ['" + buyItem.getDay() + "', " + buyItem.getSalesQuantity() + "]");
			} else if (analysisType == "��") {
				strB.append(", ['" + buyItem.getMonth() + analysisType + "', " + buyItem.getSalesQuantity() + "]");
			} else if (analysisType == "��") {
				strB.append(", ['" + buyItem.getYear() + analysisType + "', " + buyItem.getSalesQuantity() + "]");
			}
		}
		strB.append("]");
		return strB.toString();
	}
}