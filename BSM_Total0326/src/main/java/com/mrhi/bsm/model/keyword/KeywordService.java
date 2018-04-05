package com.mrhi.bsm.model.keyword;

import java.util.List;

import com.mrhi.bsm.model.item.ItemPageVO;
import com.mrhi.bsm.model.item.ItemVO;

public interface KeywordService {
	//DB에 겹치지 않는 키워드를 새로 등록하고 같은 것은 매핑 연결만
	public void addKeywordList(ItemVO vo);
	//키워드와 관련된 ItemVO 검색
	public List<ItemVO> searchKeyword(KeywordVO keyword);
	//인기 검색어 리스트
	public List<KeywordVO> hitKeyword();	
	//상품 삭제할때 키워드 맵핑 동시 삭제
	public void deleteKeywordMapping(ItemVO vo);
	public ItemPageVO getSearchItemPage(KeywordVO vo);
	public ItemPageVO next();
	public ItemPageVO prev();
}
