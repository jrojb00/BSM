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
	
	//���¼� �м���
	private Komoran komoran = new Komoran("D:\\komoran\\models_full");

	public void addKeywordList(ItemVO vo) {
		//vo���� ������ Ű���帮��Ʈ
		List<KeywordVO> keywordList = extractKeyWord(vo);
		//DB���� ��ġ�� Ű���帮��Ʈ
		if (keywordList.size() != 0) {
			List<KeywordVO> listKeptKeyword = keywordDAO.getList(keywordList);
			//������ Ű���帮��Ʈ���� ��ġ�� Ű���� ����
			keywordList.removeAll(listKeptKeyword);
			//���ο� Ű���带 ��� �ϰ� ��������
			for(KeywordVO key : keywordList) {
				keywordDAO.insertKeyword(key);
				MappingKeywordVO mapKey = new MappingKeywordVO(keywordDAO.lastItemId(), keywordDAO.lastKeywordId());
				keywordDAO.insertMappingKeyword(mapKey);
			}	
			//������ �ִ� Ű����� ���θ� ����
			for(KeywordVO key : listKeptKeyword) {
				key = keywordDAO.matchingKeyword(key);
				MappingKeywordVO mapKey = new MappingKeywordVO(keywordDAO.lastItemId(), key.getId());
				keywordDAO.insertMappingKeyword(mapKey);
			}
		} 		
	}

	//�˻�
	@Override
	public List<ItemVO> searchKeyword(KeywordVO vo) {
		//�˻��� �˻� Ű���� ����Ʈ
		List<KeywordVO> keywordList = new ArrayList<>();

		//�ߺ� ���� Set ����Ʈ
		Set<String> setKeywordList = new TreeSet<>(); 

		//�˻��� �ܾ�
		String searchingWord = vo.getWord();

		//���¼� �м�
		KomoranResult komoranResult = komoran.analyze(searchingWord.toLowerCase());
		List<Pair<String,String>> komoranAnalysis = komoranResult.getList();
		for (Pair<String,String> splitWord : komoranAnalysis) {
			if (splitWord.getSecond().equals("NNG") || splitWord.getSecond().equals("NNP") || splitWord.getSecond().equals("NNB") 
					|| splitWord.getSecond().equals("SL") || splitWord.getSecond().equals("MAG")) {
				setKeywordList.add(splitWord.getFirst());	//��ġ�� �ܾ ����� ������ Set�� �־���(�ߺ� ����)
			}
			System.out.println(splitWord);
		}

		//�ߺ��� ���ŵ� Ű���� ��ü�� Ű���� ����Ʈ�� �����
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
			System.out.println("��Ȯ��" + item.getAccuracy() + ", ���� = " + item.getName() + ", ����Ʈ :" + item.getKeywordList().size() + ", " + keywordList.size());
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

	//ItemVO���� Ű���带 ����
	public ArrayList<KeywordVO> extractKeyWord(ItemVO vo) {
		//Ű���带 �־� ��ȯ �� Ű���� ����Ʈ
		ArrayList<KeywordVO> keywordList = new ArrayList<>();

		//Ű���带 ���� �ϱ� ���� �ܾ�
		StringBuilder keywordBuilder = new StringBuilder();

		//�ߺ� ���� Set ����Ʈ
		Set<String> setKeywordList = new TreeSet<>(); 

		keywordBuilder.append(vo.getName());				//��ǰ �̸�
		keywordBuilder.append(vo.getType());				//��ǰ Ÿ��
		keywordBuilder.append(vo.getComment());				//��ǰ ����
		
		//���¼� �м�
		KomoranResult komoranResult = komoran.analyze(keywordBuilder.toString().toLowerCase());
		List<Pair<String,String>> komoranAnalysis = komoranResult.getList();
		for (Pair<String,String> splitWord : komoranAnalysis) {
			if (splitWord.getSecond().equals("NNG") || splitWord.getSecond().equals("NNP") || splitWord.getSecond().equals("NNB") 
					|| splitWord.getSecond().equals("SL") || splitWord.getSecond().equals("MAG")) {
				setKeywordList.add(splitWord.getFirst());
			}
			System.out.println(splitWord);
		}

		//�ߺ��� ���ŵ� Ű���� ��ü�� Ű���� ����Ʈ�� �����
		for (String word : setKeywordList) {
			KeywordVO keyword = new KeywordVO(word);
			keywordList.add(keyword);
		}
		return keywordList;
	}

	//��ǰ������ Ű������� ����
	@Override
	public void deleteKeywordMapping(ItemVO vo) {
		keywordDAO.deleteKeywordMapping(vo);		
	}
	
	//��Ȯ���� ����
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

	//�ֽż� ����
	class RecentCompare implements Comparator<ItemVO> {
		@Override
		public int compare(ItemVO obj1, ItemVO obj2) {
			return obj2.getRegisterDate().compareTo(obj1.getRegisterDate());
		}
	}
}
