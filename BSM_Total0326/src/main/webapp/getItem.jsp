<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>


<link rel="stylesheet" href="./resources/css/getItem.css">
<script src="//code.jquery.com/jquery.min.js"></script>
<script src = "./resources/js/total.js"></script>

</head>
<body>

<div id="wrap" style="width:1260px; margin: 0 auto;">
	<div id="top" >
	</div>
	<form id="buyForm" action="buyItem.do" method="POST" >
		<input type="hidden" id="id" name="id" value="${itemVO.id}">
		<input type="hidden" id="stockQuantity" name="stockQuantity" value="${itemVO.stockQuantity}">
		<div id="middle">
			<div id="sidebar">
				<table cellpadding="0" cellspacing="0">
					<tr><td align="center" width="400" height="490">
						<img src="getItemImg.do?id=${itemVO.id}" width="100%" height="100%">
					</td></tr>
				</table>
			</div>
			
			<div id="content2">
				<table cellpadding="0" cellspacing="0" style="border-style:none" width="300" height="490">
					<tr>
						<td colspan="2" style="height:100px">
							<h2 style="font-size:26; font-family:돋음 ">${itemVO.name}</h2>
						</td>
					</tr>
					<tr>
						<td colspan="2" style="height:100px">
							<h2 style="font-size:26; font-family:돋음 "><input type=hidden name="price" value="${itemVO.price}">${itemVO.price}원</h2>
						</td>
					</tr>
					<tr>
						<td colspan="2" style="height:30px">
							<span style="font-weight:bold; font-family:돋음 ">수량</span><span style="color:gray; font-weight:bold; font-family:돋음 ">(남은 수량 : ${itemVO.stockQuantity})</span>
						</td>
					</tr>
					<tr>
						<td colspan="2" style="height:30px">
						<span class="yesIpt ipt_wSizeF"><input id="salesQty2" name="salesQuantity" type="text" value ="1" style="width:70px; height:35px"></span>
						<button type="button" onclick ="updateCount(-1)" style="height:40px"><span class="text">-</span></button>
						<button type="button" onclick ="updateCount(1)" style="height:40px"><span class="text">+</span></button>
						</td>
					</tr>
					<tr>
						<td colspan="2" style="height:30px">
	               			<button class="button1" type="button" onclick="putCart(${itemVO.stockQuantity}, ${itemVO.id})" style="height:45px"><center>Add Cart</center></button>
	               		</td>
	       			</tr>
	       			<tr>
						<td colspan="2" style="height:30px">
	               			<button class="button2" type="button" onclick="formCheck(${itemVO.stockQuantity}, ${itemVO.price})" style="height:45px"><center>Buy</center></button>
	               		</td>
	       			</tr>
				</table>
			</div>
		</div>
		<div id="low" style="width:785px">
		<hr>
		<table>
		<tr>
			<td width=300px>PRODUCT INFO<hr></td>
			<td></td>
		</table>
			<div id="footer" style="overflow-y:scroll; width:100%; height:300px;">
				<pre width="100%" style="white-space: pre-wrap; word-wrap: break-word;">${itemVO.comment}</pre>
			</div>
		</div>		
	</form>
</div>

</body>
</html>