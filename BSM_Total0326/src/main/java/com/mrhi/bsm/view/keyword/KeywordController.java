package com.mrhi.bsm.view.keyword;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.rowset.serial.SerialBlob;
import javax.sql.rowset.serial.SerialException;

import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.mrhi.bsm.model.item.ItemPageVO;
import com.mrhi.bsm.model.item.ItemService;
import com.mrhi.bsm.model.item.ItemVO;
import com.mrhi.bsm.model.keyword.KeywordService;
import com.mrhi.bsm.model.keyword.KeywordVO;

@Controller
public class KeywordController {
	@Autowired
	KeywordService keywordService;
	
	@RequestMapping(value="/search.do")
	public String searchKeyword(KeywordVO vo, Model model) throws IOException, SQLException {
		System.out.println("검색 시작");
		if ("true".equals(vo.getNext())) {
			ItemPageVO itemPage = keywordService.next();
			model.addAttribute("itemPage", itemPage);
			vo.setPageNumber(itemPage.getStartPage());
		} else if ("true".equals(vo.getPrev())) {
			ItemPageVO itemPage = keywordService.prev();
			model.addAttribute("itemPage", itemPage);
			vo.setPageNumber(itemPage.getStartPage());
		} else {
			model.addAttribute("itemPage", keywordService.getSearchItemPage(vo));
		}
		List<ItemVO> searchList = keywordService.searchKeyword(vo);
		model.addAttribute("keyword", vo);
		model.addAttribute("searchList", searchList);
		System.out.println("검색 완료");
		return "searchList.jsp";
	}	
}

