package com.mrhi.bsm.model.keyword;

import java.util.List;

import com.mrhi.bsm.model.item.ItemPageVO;
import com.mrhi.bsm.model.item.ItemVO;

public interface KeywordService {
	//DB�� ��ġ�� �ʴ� Ű���带 ���� ����ϰ� ���� ���� ���� ���Ḹ
	public void addKeywordList(ItemVO vo);
	//Ű����� ���õ� ItemVO �˻�
	public List<ItemVO> searchKeyword(KeywordVO keyword);
	//�α� �˻��� ����Ʈ
	public List<KeywordVO> hitKeyword();	
	//��ǰ �����Ҷ� Ű���� ���� ���� ����
	public void deleteKeywordMapping(ItemVO vo);
	public ItemPageVO getSearchItemPage(KeywordVO vo);
	public ItemPageVO next();
	public ItemPageVO prev();
}
