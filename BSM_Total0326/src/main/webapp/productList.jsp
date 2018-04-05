<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>	
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>Insert title here</title>
<link rel="stylesheet" href="./resources/css/productList.css">
<script src = "./resources/js/itemList.js"></script>
<script src="//code.jquery.com/jquery.min.js"></script>
</head>
<body>
	<div id="wrapper">
		${itemType}상품
		<br>
		<hr>
		<c:forEach items="${itemList}" var="item">
			<div id="product">
				<div id="item">
					<a target="iframe1" href="getItem.do?id=${item.id}">
						<img src="getItemImg.do?id=${item.id}" style="width:100%; height:100%">
					</a>
				</div>
				<div id="name">
					<center>${item.name}</center>
				</div>
				<div id="price">
					<center>${item.price}원</center>
				</div>
			</div>
		</c:forEach>
	</div>
	<form id="itemListForm" action="itemListByType.do" method="post">
		<center>
			<input type="hidden" name="icId" value="${icId}"/>
			<input type="hidden" name="type" value="${itemType.toLowerCase()}"/>
			<c:if test="${itemPage.prev}">
				<input type="hidden" id="itemPrev" name="prev" value="false">
				<input type="submit" onclick="prevItem()" class="a" value="&laquo"/>
			</c:if>
			<c:forEach var="pageNumber" begin="${itemPage.startPage}" end="${itemPage.endPage}" step="1">
					<input type="submit" class="a" name="pageNumber" value="${pageNumber}" style="cursor:pointer"/>
			</c:forEach>
			<c:if test="${itemPage.next}">
				<input type="hidden" id="itemNext" name="next" value="false">
				<input type="button" onclick="nextItem()" class="a" value="&raquo"/>
			</c:if>
		</center>
	</form>
</body>
</html>