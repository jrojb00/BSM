package com.mrhi.bsm.model.item;

import java.util.Date;

import com.mrhi.bsm.model.user.UserVO;

public class BuyItemVO {
	private int id;
	private int uId;				//������
	private int iId;				//���� ��ǰ
	private int salesQuantity;		//���� ����
	private Date buyTime;			//���� �ð�
	private String type;			//���� ��ǰ�� Ÿ��
	
	private boolean state = false;  //�߼ۻ���
	
	private String year;			//���� �⵵
	private String month;			//���� ��
	private String day;				//���� ��

	//�Ʒ��� �ǹ��Դϴ�. by ���ؼ�
	private String birth;			//�������� ����
	private String gender;			//�������� ����
	
	//CRM fields
	//�����ڸ�� �ŷ������� �� property���� �����ϱ� ���� ��������
	private ItemVO item;
	private UserVO buyer;	
	
	//�����ڸ�� like�˻� ����
	private String stateParam;
	private String searchCondition;
	private String searchKeyword;
	private String searchBuyTime;
	
	public BuyItemVO() {
	}
	
	public BuyItemVO(int uId, int iId, int salesQuantity) {
		this.uId = uId;
		this.iId = iId;
		this.salesQuantity = salesQuantity;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getuId() {
		return uId;
	}

	public void setuId(int uId) {
		this.uId = uId;
	}

	public int getiId() {
		return iId;
	}

	public void setiId(int iId) {
		this.iId = iId;
	}

	public int getSalesQuantity() {
		return salesQuantity;
	}

	public void setSalesQuantity(int salesQuantity) {
		this.salesQuantity = salesQuantity;
	}

	public Date getBuyTime() {
		return buyTime;
	}

	public void setBuyTime(Date buyTime) {
		this.buyTime = buyTime;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getBirth() {
		return birth;
	}

	public void setBirth(String birth) {
		this.birth = birth;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	
	//CRM fields Getter & Setter
	public ItemVO getItem() {
		return item;
	}

	public void setItem(ItemVO item) {
		this.item = item;
	}

	public UserVO getBuyer() {
		return buyer;
	}

	public void setBuyer(UserVO buyer) {
		this.buyer = buyer;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public String getMonth() {
		return month;
	}

	public void setMonth(String month) {
		this.month = month;
	}

	public String getDay() {
		return day;
	}

	public void setDay(String day) {
		this.day = day;
	}

	public boolean isState() {
		return state;
	}

	public void setState(boolean state) {
		this.state = state;
	}

	public String getSearchCondition() {
		return searchCondition;
	}

	public void setSearchCondition(String searchCondition) {
		this.searchCondition = searchCondition;
	}

	public String getSearchKeyword() {
		return searchKeyword;
	}

	public void setSearchKeyword(String searchKeyword) {
		this.searchKeyword = searchKeyword;
	}

	public String getStateParam() {
		return stateParam;
	}

	public void setStateParam(String stateParam) {
		this.stateParam = stateParam;
	}

	public String getSearchBuyTime() {
		return searchBuyTime;
	}

	public void setSearchBuyTime(String searchBuyTime) {
		this.searchBuyTime = searchBuyTime;
	}

	@Override
	public String toString() {
		return "BuyItemVO [uId=" + uId + ", iId=" + iId + ", salesQuantity=" + salesQuantity + ", buyTime="
				+ buyTime + ", type=" + type + ", birth=" + birth + ", gender=" + gender + "]";
	}
}
