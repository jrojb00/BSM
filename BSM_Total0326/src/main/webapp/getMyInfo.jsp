<%@ page contentType="text/html; charset=UTF-8" import="com.mrhi.bsm.model.user.UserVO"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>내 정보 보기</title>

<link rel="stylesheet" href="./resources/css/getMyInfo.css">
</head>
<body>
	<h3>내 정보 보기</h3>
	<hr>
	<div id="info">
		<form class="form-2">
			<table cellpadding="0" cellspacing="0">
				<tr>
					<td class="account">이름</td>
					<td class="content" align="right" width= 230px;><center>${userVO2.name}</center></td>
				</tr>
				<tr>
					<td class="account">이메일</td>
					<td align="right" width= 230px;><center>${userVO2.email}</center></td>
				</tr>
				<tr>
					<td class="account">주소</td>
					<td align="right" width= 230px;><center>${userVO2.address}</center></td>
				</tr>
				<tr>
					<td class="account">전화 번호</td>
					<td align="right" width= 230px;><center>${userVO2.phoneNumber}</center></td>
				</tr>
				<tr>
					<td class="account">관심 사항</td>
					<td>
						<center>
							<c:forEach items="${userVO2.myFavoriteList}" var="category">
								${category.type}
							</c:forEach>
						</center>
					</td>
				</tr>
				<tr>
					<td colspan="2" align="center">
						<a href="updateInfo.do"><button class="button1" type="button" style="height:45px"><center>회원 수정</center></button></a>
						<a href="deleteUser.do?id${userVO2.id}"><button class="button2" type="submit" style="height:45px"><center>회원 탈퇴</center></button></a>
					</td>
				</tr>
			</table>
		</form>
	</div>
		<h3>내 구매 내역</h3>
		<hr>
		<div id="a">
			<center>
			<table cellpadding="0" cellspacing="0" style="width:850px">
				<tr>
					<th style="width:65px; text-align:center; color:#353535">주문번호</th>
					<th colspan="2" style="text-align:center; color:#353535">상품정보</th>
					<th style="width:105px; text-align:center; color:#353535">주문일시</th>
					<th style="width:85px; text-align:center; color:#353535">결제금액</th>
					<th style="width:80px; text-align:center; color:#353535">주문상태</th>
					<th style="width:10px; text-align:center; color:#353535"></th>
				</tr>
				
			</table>
			</center>
		</div>
		<hr>
		<div id="a" style="overflow-y:scroll; width:100%; height:600px;">
			<center>
			<table cellpadding="0" cellspacing="0" style="width:850px">
				<c:forEach items="${userVO2.purchasingList}" var="purchasingItem">
					<tr>
						<td style="width:65px; text-align:center; color:#353535">${purchasingItem.id}</td>
						<td style="width:150px; text-align:center; color:#353535"><img src="getItemImg.do?id=${purchasingItem.item.id}" style="width:130px; height:150px;"></td>
						<td style=" text-align:center; color:#353535">${purchasingItem.item.name}</td>
						<td style="width:105px; text-align:center; color:#353535"><fmt:formatDate value="${purchasingItem.buyTime}" pattern="yyyy-MM-dd HH:mm"/></td>
						<td style="width:85px; text-align:center; color:#353535">${purchasingItem.salesQuantity * purchasingItem.item.price}</td>
						<td style="width:80px; text-align:center; color:#353535"><c:if test="${purchasingItem.state == false}">배송준비중</c:if><c:if test="${purchasingItem.state == true}">배송중</c:if></td>
					</tr>
				</c:forEach>
			</table>
			</center>
		</div>
		
</body>
</html>