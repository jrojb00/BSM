<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>	
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>BSM Shop</title>

<link rel="stylesheet" href="./resources/css/community.css">
<link rel="stylesheet" href="./resources/css/aTagStyle.css">
</head>
<body>
	<div id="wrapper">
		<c:if test="${itemMenuCategory == 'shop'}">
			<div id="nav">	
				<br><br>		
				&nbsp;<a target="iframe2" class="menuLink" href="itemListByType.do?icId=0&type=new">SHOP</a>
				<hr align="left" style="width: 80%;"><br>
				<c:forEach items="${allCategory}" var="category">
					&nbsp;<a target="iframe2" class="menuLink" href="itemListByType.do?icId=${category.id}&type=${category.type}">${category.type}</a><br><br>			
				</c:forEach>
			</div>
			<div id="content">
				<div id="menu">
					<iframe name="iframe2" id="myframe"  src="itemListByType.do?icId=0&type=new" frameborder="0" width=870px height=870px scrolling="no" marginwidth="0" marginheight="0" style=onLoad:setFrameSize()></iframe>
				</div>
			</div>
		</c:if>
		
		<c:if test="${itemMenuCategory == 'special'}">
			<div id="nav">
				<br><br>					
				&nbsp;<a target="iframe2" class="menuLink" href="itemListByType.do?type=special">SPECIAL</a>
				<hr align="left" style="width: 80%;"><br>
				&nbsp;<a target="iframe2" class="menuLink" href="itemListByType.do?icId=0&type=new">NEW</a><br><br>
				&nbsp;<a target="iframe2" class="menuLink" href="itemListByType.do?icId=0&type=best">BEST</a><br><br>
				<c:if test="${userVO2 != null}">
					&nbsp;<a target="iframe2" class="menuLink" href="itemListByType.do?icId=0&type=favorite">MY FAVORITE</a><br><br>
				</c:if>
			</div>
			
			<div id="content">
				<div id="menu">
					<iframe name="iframe2" id="myframe"  src="itemListByType.do?type=special" frameborder="0" width=870px height=870px scrolling="no" marginwidth="0" marginheight="0" style=onLoad:setFrameSize()></iframe>
 				</div>
	 		</div>
 		</c:if>
	</div>
</body>
</html>