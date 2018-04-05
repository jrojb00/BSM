<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>	
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>Insert title here</title>

<link rel="stylesheet" href="./resources/css/special.css">

</head>
<body>
	<div id="wrapper">
		<div id="productname">
		NEW
		<br>
		<hr>
		</div>
		<div id="productlist">
		<c:forEach items="${newItemList}" var="item" begin="0" end="2">
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
		<div id="productname">
		BEST
		<br>
		<hr>
		</div>
		<div id="productlist">
		<c:forEach items="${hitItemList}" var="item" begin="0" end="2">
			<div id="product">
				<div id="item">
					<a target="iframe1" href="getItem.do?id=${item.id}">
						<img src="getItemImg.do?id=${item.id}" style="width:100%; height:100%">
					</a>
				</div>
				<div id="name">
					<center><center>${item.name}</center></center>
				</div>
				<div id="price">
					<center>${item.price}원</center>
				</div>
			</div>
		</c:forEach>
		</div>
		FAVORITE
		<br>
		<hr>
		<div id="productlist">
		<c:if test="${userVO2 == null}">
			로그인 후 사용 가능한 기능입니다.
		</c:if>
		<c:if test="${userVO2 != null}">
			<c:forEach items="${favoriteList}" var="favoriteItem" begin="0" end="2">
				<div id="product">
					<div id="item">
						<a target="iframe1" href="getItem.do?id=${favoriteItem.id}">
							<img src="getItemImg.do?id=${favoriteItem.id}" style="width:100%; height:100%">
						</a>
					</div>
					<div id="name">
						<center>${favoriteItem.name}</center>
					</div>
					<div id="price">
						<center>${favoriteItem.price}원</center>
					</div>
				</div>
			</c:forEach>
		</c:if>
		</div>
	</div>
</body>
</html>