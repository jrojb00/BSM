<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>

<link rel="stylesheet" href="./resources/css/cartList.css">

<script src = "./resources/js/total.js"></script>
<script src="//code.jquery.com/jquery.min.js"></script>
</head>
<body>
<div id="wrap" style="width:800px; margin: 0 auto;">
	<form action="buyCartList.do" method="POST" >
		<input type="hidden" id="id" name="id" value="${itemVO.id}">
		<div id="middle">
			<div id="sidebar">
				<table cellpadding="0" cellspacing="0" style="width:810px">
					<tr>
						<td colspan="3" style="width:700px"><h3 style="color:#353535">MyCart(${fn:length(cart.cart)})</h3></td>
						<td style="width:70px; vertical-align:bottom; text-align:center; color:#353535">Price</td>
						<td style="width:70px; vertical-align:bottom; text-align:center; color:#353535">Qty</td>
						<td style="width:70px; vertical-align:bottom; text-align:center; color:#353535">Total</td>
						<td style="width:70px; vertical-align:bottom; color:#353535"></td>
					</tr>
				</table>
				<hr>
			</div>
			
			<div id="content2">
				<c:set var="eachTotalPrice" value = "0" />
				<c:set var="TotalPrice" value = "0" />
				<c:forEach items="${cart.cart}" var="item" varStatus="status">
					<table cellpadding="0" cellspacing="0" style="width:810px;padding-bottom:20px">
						<tr>
							<td style="width:40px; vertical-align:middle;"><input type="checkBox" name="buyCartNumber" style="width:20px; height:20px;" value="${status.index}"></td>
							<td style="width:150px;"><img src="getItemImg.do?id=${item.id}" width="130" height="150"></td>
							<td style="width:450px; vertical-align:middle">${item.name}</td>
							<td style="width:70px; vertical-align:middle">${item.price}원</td>
							<td style="width:70px; vertical-align:middle"><input name="salesQuantity" type="text" style="width:30px" value="${item.salesQuantity}"></td>
							<td style="width:70px; vertical-align:middle"><c:set var= "eachTotalPrice" value="${item.price * item.salesQuantity}"/><c:out value="${eachTotalPrice}원"/></td>
							<td style="width:70px; vertical-align:middle"><a href="deleteCartItem.do?cartNumber=${status.index}">✖</a></td>
						</tr>
						<c:set var="TotalPrice" value="${TotalPrice + eachTotalPrice}"/>	
					</table>
				</c:forEach>
				<hr>
			</div>
			
			<div id="content3">
				<table  cellpadding="0" cellspacing="0">
					<tr>
						<td style=" text-align:left"><h4 style="font-size:32; font-family:돋음; color:#353535">Total</h4></td>
						<td style=" vertical-align:middle; text-align:center"><b style="color:#353535">${TotalPrice}원</b></td>
					</tr>
					<tr>
						<td colspan="2"><button class="button2" type="submit" style="height:45px"><center>Buy</center></button></td>
					</tr>
				</table>
			</div>
		</div>
	</form>
</div>

</body>
</html>