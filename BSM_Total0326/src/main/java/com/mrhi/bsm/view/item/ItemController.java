package com.mrhi.bsm.view.item;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.rowset.serial.SerialBlob;
import javax.sql.rowset.serial.SerialException;

import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mrhi.bsm.model.item.BuyItemVO;
import com.mrhi.bsm.model.item.CartVO;
import com.mrhi.bsm.model.item.ItemCategoryVO;
import com.mrhi.bsm.model.item.ItemPageVO;
import com.mrhi.bsm.model.item.ItemService;
import com.mrhi.bsm.model.item.ItemVO;
import com.mrhi.bsm.model.item.TendencyAnalysisVO;
import com.mrhi.bsm.model.user.UserVO;

@Controller
public class ItemController {
	@Autowired
	ItemService itemService;
	
	@RequestMapping(value="/insertItem.do")
	public String insertItem(ItemVO vo) throws IOException, SQLException {
		System.out.println("상품 등록 처리");
		checkImg(vo);
		itemService.insertItem(vo);
		System.out.println("상품 등록 완료");
		return "redirect:getItemList.do";
	}

	@RequestMapping(value="/updateItem.do")
	public String updateItem(ItemVO vo) throws IOException, SQLException {
		System.out.println("상품 정보 변경 전");
		checkImg(vo);
		itemService.updateItem(vo);
		System.out.println("상품 정보 변경 성공");
		return "redirect:getItemList.do";
	}		
	
	@RequestMapping(value="/deleteItem.do")
	public String deleteItem(ItemVO vo) {
		System.out.println("상품 삭제 전");
		itemService.deleteItem(vo);				
		System.out.println("상품 삭제 성공");
		return "redirect:getItemList.do";
	}	
	
	//관리자 모드에서 테이블의 각 아이템을 Ajax를 통해 전송함
	@RequestMapping(value="/getItem4Admin.do")
	@ResponseBody
	public ItemVO getItem4Admin(ItemVO vo) {
		return itemService.getItem(vo);		
	}
	
	@RequestMapping(value="/getItem.do")
	public String getItem(ItemVO vo, Model model, HttpSession session) throws IOException {
		System.out.println("상품 정보 보기 전");
		ItemVO itemVO = itemService.getItem(vo);
		model.addAttribute("itemVO", itemVO);
		System.out.println("상품 정보 보기 성공");
		return "getItem.jsp";
	}
	
	@RequestMapping(value="/getItemImg.do")
	public void getItemImg(HttpServletResponse res, ItemVO vo) throws IOException, SerialException, SQLException {
		ItemVO itemVO = itemService.getItem(vo);
		res.setHeader("Content_Disposition", "inline;filename=\"" + itemVO.getImgPath() + "\"");
		ServletOutputStream outputStream = res.getOutputStream();
		res.setContentType(itemVO.getContentType());
		SerialBlob blob = new SerialBlob(itemVO.getImg());
		IOUtils.copy(blob.getBinaryStream(), outputStream);
		outputStream.flush();
		outputStream.close();
	}
	
	//관리자모드 검색 조건 목록 설정
	@ModelAttribute("conditionMap")
	public Map<String, String> searchConditionMap() {
		Map<String, String> conditionMap = new HashMap<String, String>();
		conditionMap.put("상품명", "NAME");
		conditionMap.put("내용", "CONTENT");
		return conditionMap;
	}	
	
	//모든 상품 리스트(admin을 위한 리스트)
	@RequestMapping(value="/getItemList.do")
	public String getItemList(ItemVO vo, Model model) {
		System.out.println("상품 목록 검색 처리");
		System.out.println("상품 리스트 보기 전");
		List<ItemVO> beforeList = itemService.getItemList(vo);
		List<ItemVO> itemList = new ArrayList<>();
		for (ItemVO vo1 : beforeList) {
			String type = itemService.getType(vo1);
			vo1.setTotalSalesAmount(vo1.getPrice() * vo1.getSalesQuantity());
			vo1.setType(type);
			itemList.add(vo1);
		}
		model.addAttribute("itemList", itemList);
		System.out.println("상품 리스트 보기 성공");
		return "adminItem.jsp";
	}
	
	@RequestMapping(value="/getDetailedBuyItemList.do")
	public String getDetailedBuyItemList(BuyItemVO vo, Model model) {
		System.out.println("모든 상품구매리스트 보기 전");
		if ("buyTime".equals(vo.getSearchCondition())) {
			vo.setSearchKeyword(vo.getSearchBuyTime());
		} else if ("state".equals(vo.getSearchCondition())) {
			vo.setSearchKeyword(vo.getStateParam());
		}
		List<BuyItemVO> detailedBuyItemList = itemService.getDetailedBuyItemList(vo);
		model.addAttribute("buyItemList", detailedBuyItemList);
		System.out.println("모든 상품구매리스트 보기 성공");
		return "adminContract.jsp";
	}
	
	@RequestMapping(value="/buyItem.do")
	public String buyItem(ItemVO vo, Model model, HttpSession session) {
		System.out.println("상품 리스트 보기 전");
		UserVO user = (UserVO) session.getAttribute("userVO2");
		session.setAttribute("userVO2", itemService.buyItem(vo, user));
		model.addAttribute("itemVO", itemService.getItem(vo));
		System.out.println("상품 리스트 보기 성공");
		return "redirect:mainContents.jsp";
	}
	
	@RequestMapping(value="/putCart.do")
	@ResponseBody
	public String putCart(ItemVO vo, HttpSession session) {
		//구매할 수량
		int salesQuantity = vo.getSalesQuantity();
		ItemVO itemToPurchase = itemService.getItem(vo);
		itemToPurchase.setSalesQuantity(salesQuantity);
		System.out.println("카트 넣기 전");
		if (session.getAttribute("cart") == null) {
			CartVO cart = new CartVO();
			session.setAttribute("cart", cart);
		}
		CartVO cart = (CartVO) session.getAttribute("cart");
		cart.getCart().add(itemToPurchase);
		session.setAttribute("cart", cart);
		System.out.println("카트 넣기 성공");
		return "";
	}
	
	@RequestMapping(value="/deleteCartItem.do")
	public String deleteCartItem(CartVO vo, HttpSession session) {
		CartVO cart = (CartVO) session.getAttribute("cart");
		cart.getCart().remove(vo.getCartNumber());
		session.setAttribute("cart", cart);
		return "redirect:cartList.jsp";
	}
	
	@RequestMapping(value="/buyCartList.do")
	public String buyCartList(CartVO vo, HttpSession session) {
		UserVO user = (UserVO) session.getAttribute("userVO2");
		CartVO cart = (CartVO) session.getAttribute("cart");
		List<ItemVO> buyTarget = new ArrayList<>();
		for (int index : vo.getBuyCartNumber()) {
			buyTarget.add(cart.getCart().get(index));
		}
		for (ItemVO item : buyTarget) {
			session.setAttribute("userVO2", itemService.buyItem(item, user));
		}
		cart.getCart().removeAll(buyTarget);
		session.setAttribute("cart", cart);
		return "redirect:cartList.jsp";
	}
	
	@RequestMapping(value="/tendencyAnalysis.do", produces="application/text; charset=utf8")
	@ResponseBody
	public String tendencyAnalysis(TendencyAnalysisVO tend) {
		System.out.println("판매 추이 분석 전");
		String result = itemService.tendencyAnalysis(tend);
		System.out.println("판매 추이 분석 성공");
		return result;
	}
	
	@RequestMapping(value="/itemMenuCategory.do")
	public String itemMenuCategory(ItemCategoryVO vo, Model model) {
		model.addAttribute("itemMenuCategory", vo.getType());
		return "itemMenuForm.jsp";
	}
	
	//타입별 상품 리스트(유저를 위한 리스트)
	@RequestMapping(value="/itemListByType.do")
	public String itemListByType(ItemVO vo, Model model, HttpSession session) {
		UserVO user = (UserVO) session.getAttribute("userVO2");
		if ("true".equals(vo.getNext())) {
			ItemPageVO itemPage = itemService.next();
			model.addAttribute("itemPage", itemPage);
			vo.setPageNumber(itemPage.getStartPage());
		} else if ("true".equals(vo.getPrev())) {
			ItemPageVO itemPage = itemService.prev();
			model.addAttribute("itemPage", itemPage);
			vo.setPageNumber(itemPage.getStartPage());
		} else {
			model.addAttribute("itemPage", itemService.getItemPage(vo, user));
		}
		if ("special".equals(vo.getType())) {
			if (user != null) {
				vo.setType("favorite");
				List<ItemVO> favoriteList = itemService.getItemListByType(vo, user);
				model.addAttribute("favoriteList", favoriteList);				
			}
			return "special.jsp";
		} else {
			if (vo.getIcId() == 0) {
				String itemType = vo.getType();
				model.addAttribute("itemType", itemType.toUpperCase());
			} else {
				model.addAttribute("itemType", vo.getType());			
			}
			List<ItemVO> itemList = itemService.getItemListByType(vo, user);
			model.addAttribute("itemList", itemList);
			model.addAttribute("icId", vo.getIcId());
			return "productList.jsp";
		}
	}
	
	@RequestMapping(value="/initItem.do")
	public String initItem(ItemCategoryVO vo, HttpSession session, Model model) {
		UserVO user = (UserVO) session.getAttribute("userVO2");
		session.setAttribute("allCategory", itemService.allCategory());
		session.setAttribute("newItemList", itemService.getNewItemList());
		session.setAttribute("hitItemList", itemService.getHitItem());
		if (user != null) {
			session.setAttribute("slideItemList", itemService.getMyFavorite(user));
		}else
			session.setAttribute("slideItemList", itemService.getNewItemList());
		return "mainContents.jsp";
	}
	
	private void checkImg(ItemVO vo) throws IOException {
		if (vo.getImgFile().equals(null)) {
			ItemVO item = itemService.getItem(vo);
			vo.setImg(item.getImg());
			vo.setImgPath(item.getImgPath());
		} else if (vo.getImgFile().isEmpty()) {
			ItemVO item = itemService.getItem(vo);
			vo.setImg(item.getImg());
			vo.setImgPath(item.getImgPath());
		} else {
			vo.updateImg();
		}
	}
}
