 <%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>	
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>MainView</title>

<link rel="stylesheet" href="./resources/css/mainContents.css">

</head>
<body>
New / My Favorite 상품
<div id="wrap" class="back" style="width:1260px; margin: 0 auto;">
	<div id="slide" style="width:870px; height:810px;">
			<div class="slider" style="width:870px; height:430px;">
				<figure>
					<c:forEach items="${slideItemList}" var="slideItem">		
						<div class="clide" >
							<div id="product_pic">
								<a href="getItem.do?id=${slideItem.id}">
									<img src="getItemImg.do?id=${slideItem.id}" style="width:100%; height:100%">
								</a>
							</div>
							<div id="product_detail">
								<table id="product_tbll" style="width:350px; height:400px">
									<br>
									<tr>
										<td class="product_title"  style="height:50px">&nbsp;&nbsp;&nbsp;${slideItem.name}</td>
									</tr>
									
									<tr>
										<td class="product_comment" style="height:250px">
											<c:choose>
												<c:when test="${fn:length(slideItem.comment) > 160}">
													<c:out value="${fn:substring(slideItem.comment,0,159)}"/>....
												</c:when>
												<c:otherwise>
													<c:out value="${data.nm}"/>
												</c:otherwise> 
											</c:choose>
										</td>
									</tr>
									
									<tr>
										<td class="product_title" style="height:50px">&nbsp;&nbsp;&nbsp;${slideItem.price}(원)</td>
									</tr>
								</table>
							</div>
						</div>
					</c:forEach>
				</figure>
			</div>
			
			<div id="productmenu" style="width:870px; height:380px;">
					<br>
					<br>
					BEST
					<br>
					<hr>
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
	</div>
</div>
</body>
</html>