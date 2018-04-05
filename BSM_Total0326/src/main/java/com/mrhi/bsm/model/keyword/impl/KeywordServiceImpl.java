package com.mrhi.bsm.model.keyword.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mrhi.bsm.model.item.ItemPageVO;
import com.mrhi.bsm.model.item.ItemVO;
import com.mrhi.bsm.model.keyword.KeywordService;
import com.mrhi.bsm.model.keyword.KeywordVO;
import com.mrhi.bsm.model.keyword.MappingKeywordVO;
import com.mrhi.bsm.model.keyword.impl.KeywordServiceImpl.AccuracyCompare;
import com.mrhi.bsm.model.keyword.impl.KeywordServiceImpl.RecentCompare;
import com.mrhi.bsm.model.user.UserVO;

import kr.co.shineware.nlp.komoran.core.Komoran;
import kr.co.shineware.nlp.komoran.model.KomoranResult;
import kr.co.shineware.util.common.model.Pair;

@Service("keywordService")
public class KeywordServiceImpl implements KeywordService {
	@Autowired
	private KeywordDAO keywordDAO;
	@Autowired	
	private ItemPageVO searchItemPage;
	
	//형태소 분석기
	private Komoran komoran = new Komoran("D:\\komoran\\models_full");

	public void addKeywordList(ItemVO vo) {
		//vo에서 추출한 키워드리스트
		List<KeywordVO> keywordList = extractKeyWord(vo);
		//DB에서 겹치는 키워드리스트
		if (keywordList.size() != 0) {
			List<KeywordVO> listKeptKeyword = keywordDAO.getList(keywordList);
			//기존의 키워드리스트에서 겹치는 키워드 제거
			keywordList.removeAll(listKeptKeyword);
			//새로운 키워드를 등록 하고 매핑해줌
			for(KeywordVO key : keywordList) {
				keywordDAO.insertKeyword(key);
				MappingKeywordVO mapKey = new MappingKeywordVO(keywordDAO.lastItemId(), keywordDAO.lastKeywordId());
				keywordDAO.insertMappingKeyword(mapKey);
			}	
			//기존에 있는 키워드는 매핑만 해줌
			for(KeywordVO key : listKeptKeyword) {
				key = keywordDAO.matchingKeyword(key);
				MappingKeywordVO mapKey = new MappingKeywordVO(keywordDAO.lastItemId(), key.getId());
				keywordDAO.insertMappingKeyword(mapKey);
			}
		} 		
	}

	//검색
	@Override
	public List<ItemVO> searchKeyword(KeywordVO vo) {
		//검색할 검색 키워드 리스트
		List<KeywordVO> keywordList = new ArrayList<>();

		//중복 제거 Set 리스트
		Set<String> setKeywordList = new TreeSet<>(); 

		//검색한 단어
		String searchingWord = vo.getWord();

		//형태소 분석
		KomoranResult komoranResult = komoran.analyze(searchingWord.toLowerCase());
		List<Pair<String,String>> komoranAnalysis = komoranResult.getList();
		for (Pair<String,String> splitWord : komoranAnalysis) {
			if (splitWord.getSecond().equals("NNG") || splitWord.getSecond().equals("NNP") || splitWord.getSecond().equals("NNB") 
					|| splitWord.getSecond().equals("SL") || splitWord.getSecond().equals("MAG")) {
				setKeywordList.add(splitWord.getFirst());	//겹치는 단어가 생기기 때문에 Set에 넣어줌(중복 제거)
			}
			System.out.println(splitWord);
		}

		//중복이 제거된 키워드 객체를 키워드 리스트에 담아줌
		for (String word : setKeywordList) {
			KeywordVO keyword = new KeywordVO(word);
			keywordList.add(keyword);
		}
		keywordList.get(0).setPageNumber(vo.getPageNumber());
		List<ItemVO> before = keywordDAO.searchKeyword(keywordList);
		List<ItemVO> result = new ArrayList<>();
		for (ItemVO item : before) {
			float seachKeywordListSize = keywordList.size();
			float keywordListSize = item.getKeywordList().size();
			item.setAccuracy((int)(keywordListSize / seachKeywordListSize * 100));
			System.out.println("정확성" + item.getAccuracy() + ", 제목 = " + item.getName() + ", 리스트 :" + item.getKeywordList().size() + ", " + keywordList.size());
			result.add(item);
		}
		if (vo.getSortType().equals("accuracyCompare")) {
			Collections.sort(result, new AccuracyCompare());
		} else {
			Collections.sort(result, new RecentCompare());			
		}
		searchItemPage.init(vo.getPageNumber(), keywordDAO.searchItemListCount(keywordList));
		if (result.size() <= vo.getPageNumber() * searchItemPage.getContentNum())
			return result.subList((vo.getPageNumber() - 1) * searchItemPage.getContentNum(), result.size());			
		 else 
			return result.subList((vo.getPageNumber() - 1) * searchItemPage.getContentNum(), vo.getPageNumber() * searchItemPage.getContentNum());
	}
	
	@Override
	public ItemPageVO getSearchItemPage(KeywordVO vo) {
		return searchItemPage;
	}
	
	@Override
	public ItemPageVO next() {
		searchItemPage.nextPage();
		return searchItemPage;
	}
	
	@Override
	public ItemPageVO prev() {
		searchItemPage.prevPage();
		return searchItemPage;
	}

	@Override
	public List<KeywordVO> hitKeyword() {
		return keywordDAO.hitKeyword();
	}

	//ItemVO에서 키워드를 추출
	public ArrayList<KeywordVO> extractKeyWord(ItemVO vo) {
		//키워드를 넣어 반환 할 키워드 리스트
		ArrayList<KeywordVO> keywordList = new ArrayList<>();

		//키워드를 추출 하기 위한 단어
		StringBuilder keywordBuilder = new StringBuilder();

		//중복 제거 Set 리스트
		Set<String> setKeywordList = new TreeSet<>(); 

		keywordBuilder.append(vo.getName());				//제품 이름
		keywordBuilder.append(vo.getType());				//제품 타입
		keywordBuilder.append(vo.getComment());				//제품 설명
		
		//형태소 분석
		KomoranResult komoranResult = komoran.analyze(keywordBuilder.toString().toLowerCase());
		List<Pair<String,String>> komoranAnalysis = komoranResult.getList();
		for (Pair<String,String> splitWord : komoranAnalysis) {
			if (splitWord.getSecond().equals("NNG") || splitWord.getSecond().equals("NNP") || splitWord.getSecond().equals("NNB") 
					|| splitWord.getSecond().equals("SL") || splitWord.getSecond().equals("MAG")) {
				setKeywordList.add(splitWord.getFirst());
			}
			System.out.println(splitWord);
		}

		//중복이 제거된 키워드 객체를 키워드 리스트에 담아줌
		for (String word : setKeywordList) {
			KeywordVO keyword = new KeywordVO(word);
			keywordList.add(keyword);
		}
		return keywordList;
	}

	//상품삭제시 키워드매핑 삭제
	@Override
	public void deleteKeywordMapping(ItemVO vo) {
		keywordDAO.deleteKeywordMapping(vo);		
	}
	
	//정확도순 정렬
	class AccuracyCompare implements Comparator<ItemVO> {
		@Override
		public int compare(ItemVO obj1, ItemVO obj2) {
			int compare = ((Integer) obj2.getAccuracy()).compareTo(obj1.getAccuracy());
			if (compare == 0) {
				compare = obj2.getRegisterDate().compareTo(obj1.getRegisterDate());
			}
			return compare;
		}
	}

	//최신순 정렬
	class RecentCompare implements Comparator<ItemVO> {
		@Override
		public int compare(ItemVO obj1, ItemVO obj2) {
			return obj2.getRegisterDate().compareTo(obj1.getRegisterDate());
		}
	}
}
