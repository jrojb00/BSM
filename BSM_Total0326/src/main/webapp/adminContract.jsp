<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>BSM 관리자모드/회원관리</title>
<link rel="stylesheet" href="./resources/css/adminAnalysis.css">
<!-- jQuery  -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<!-- bootstrap JS -->
<script src="https://netdna.bootstrapcdn.com/bootstrap/3.0.0/js/bootstrap.min.js"></script>
<!-- bootstrap CSS -->
<link href="https://netdna.bootstrapcdn.com/bootstrap/3.0.0/css/bootstrap.min.css" rel="stylesheet">
<link rel="stylesheet" href="./resources/css/adminAnalysis.css">   
<script src="http://code.jquery.com/jquery-1.11.0.min.js"></script>	
<script src = "./resources/js/resetFormElement.js"></script> 
<script src = "./resources/js/total.js"></script>
</head>
<body>
	<a href="adminModeIndex.jsp"><b>&lt;&lt;뒤로가기</b></a>
		<br><br>
	<b>관리자모드 / 거래관리</b>
		<br>
	<hr align="left" style="width: 80%;">
	<table>
		<tr>
			<td><p>Total Order : ${fn:length(buyItemList)}</p>
			</td>
			<td><!-- 회원정렬 선택창 -->
				<p align="right">
					<select name="type" style="width:120px">
						<option value="pNum">번호</option>
						<option value="name">아이디</option>
						<option value="price">이름</option>
						<option value="is_Admin">회원등급</option>
						<option value="stockQuantity">성별</option>
						<option value="purchasingQuantity">이메일</option>
						<option value="totalPrice">주소</option>
						<option value="totalPrice">전화번호</option>
					</select>
			</td>
		</tr>
		<tr>
			<td colspan='2'>
				<!-- 주문 리스트 테이블 -->
				<table border="1" cellpadding="0" cellspacing="0" style="border-collapse: collapse">
					<tr>
						<th><center>주문번호</center></th>
						<th><center>주문일시</center></th>
						<th width=100px><center>주문자명</center></th>
						<th width=100px><center>회원등급</center></th>
						<th width=100px><center>전화번호</center></th>
						<th width=100px><center>주소</center></th>
		     			<th><center>상품정보</center></th>
						<th><center>결제금액</center></th>
						<th><center>주문상태</center></th>
						<th width=120px><center>관리</center></th>
					</tr>
				<c:forEach items="${buyItemList}" var="buyItem">
					<tr>
						<td><center>${buyItem.id}</center></td>
						<td><center><fmt:formatDate value="${buyItem.buyTime}" pattern="yyyy-MM-dd"/></center></td>
						<td><center>${buyItem.buyer.name}</center></td>
						<td><center><c:if test="${buyItem.buyer.isAdmin == '0'}">일반회원</c:if><c:if test="${buyItem.buyer.isAdmin == '1'}">운영자</c:if></center></td>
						<td><center>${buyItem.buyer.phoneNumber}</center>
						<td>${buyItem.buyer.address}</td>
						<td><center>${buyItem.item.name}<br>(구매수량 : ${buyItem.salesQuantity})</center></td>
						<td><center>${buyItem.salesQuantity * buyItem.item.price}</center></td>
						<td><center>미발송</center></td>
						<td width=50px><center><a class="checkBtn" style="cursor:pointer">발송처리</a></center></td>
					</tr>
				</c:forEach>
				</table>
			</td>
		</tr>
		<tr>
			<td colspan='2'>
				<p>
					<!-- 검색 시작 -->
					<form id="buyItemListForm" action="getDetailedBuyItemList.do" method="post" align="right">
					<input type="button" onclick="getAllBuyItemList()" value="전체목록">
						<select id="buyItemSearchCondition" name="searchCondition" onchange="buyItemSelected(this.value)">
								<option value="id">주문번호</option>
								<option value="buyTime">주문일시 </option>
								<option value="buyerName">주문자명 </option>
								<option value="buyerAddress">주소 </option>
								<option value="itemName">상품명 </option>
								<option value="state">주문상태 </option>
						</select>
						<input id="buyItemSearchKeyword" onkeydown="return isNumberKey(event)" onkeyup='removeChar(event)' name="searchKeyword" type="text"/>
						<input id="buyItemBuyTime" name="searchBuyTime" type="date"/>
						<input class="state" id="stateTrue" name="stateParam" type="radio" value="true"><label class="state" for="stateTrue">발송</label>
						<input class="state" id="statefalse" name="stateParam" type="radio" value="false"><label class="state" for="statefalse">미발송</label>
						<input type="submit" onclick="checkedBuyItemForm()" value="검색" />
					</form>
				</p>
			</td>
		</tr>	
	</table>	
</body>
</html>