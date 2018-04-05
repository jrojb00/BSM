<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>	
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>MainView</title>
<link rel="stylesheet" href="./resources/css/main.css">

<script src = "./resources/js/total.js"></script>
<script src="//code.jquery.com/jquery.min.js"></script>
</head>
<body>

<div id="wrap" class="back" style="width:1260px; margin: 0 auto;">
	<div id="top"  style="width:870px;">
		<div id="login" style="width:auto; height:30px;">
			<table>
				<tr>
				<c:if test="${userVO2 == null}">
					<td width= 50px;><a target="iframe1" href="login.jsp" class="sidemenu">Login</a></td>
					<td width= 70px;><a target="iframe1" href="terms.jsp" class="sidemenu">회원가입</a></td>
				</c:if>
				<c:if test="${userVO2 != null}">
					<td width= 70px;>
<!-- 						<form action="logout.do" method="post"> -->
						<a class="sidemenu" onclick="logout()" style="cursor:pointer">Logout</a>
<!-- 						</form> -->
					</td>
					<td width= 90px;><a target="iframe1" href="getMyInfo.do" class="sidemenu">내정보</a></td>
					<td width= 70px;><a target="iframe1" href="cartList.jsp" class="sidemenu">장바구니</a></td>
					<td width= 70px;><a class="sidemenu" onclick="chatting()" style="cursor:pointer">채팅방</a></td>
				</c:if>
				<!-- 관리자모드 설정 (관리자 = 1 일반사용자  = 0) -->
				<c:if test="${userVO2.isAdmin == 1}">
					<td width= 90px;><a href="adminModeIndex.jsp" onClick="window.open(this.href, '', 'location=no,status=yes,scrollbars=1,resizable=1,width=1200,height=800,left=0,top=0'); return false;" class="sidemenu"><font color="red"><b>관리자모드</b></font></a></td>
					</c:if>
				</tr>
			</table>
		</div>
	</div>
	
	<div id="middle" style="width:870px; height:1200px; ">
		<div id="rogo" style="width:305px; height:105px;">
   			<table>
   				<tr>
   					<td><a onclick="window.location.reload()"><input type="image" src="resources/image/logo2.png" alt="로고" width="300px" height="120px"></a></td>
				</tr>
			</table>
		</div>		
		<div id="serch" style="width:650px; height:45px;">
			<table>
   				<tr>
   					<td>
						<input type="text" id="searchWord" placeholder="찾으실 상품명을 입력하세요." style="width:450px; height:35px; font-size:15px" />
						<button style="border: 2px #4ABFD3; background:white; width:60px; height:40px; background-color:black;" onclick="search()"><font color="white">검색</font></button>
					</td>
				</tr>
			</table>
		</div>
		
		<div id="menu" style="width:870px; height:80px; display:inline-block;">
		<hr>
			<div id="menu1" style="width:870px; height:35px;">
			<ul>
				<li class="topMenuLi" style="width:100px; height:30px;"><a target="iframe1" target="iframe1" class="menuLink" href="mainContents.jsp">HOME</a>
				</li>
				<li class="topMenuLi" style="width:100px; height:30px;"><a target="iframe1" class="menuLink" href="itemMenuCategory.do?type=special">SPECIAL</a>
				</li>
				<li class="topMenuLi" style="width:100px; height:30px;"><a target="iframe1" class="menuLink" href="itemMenuCategory.do?type=shop">SHOP</a>
				</li>
				<li class="topMenuLi" style="width:100px; height:30px;"><a target="iframe1" class="menuLink" href="FAQ.jsp">FAQ</a>
				</li>
				<li class="topMenuLi" style="width:100px; height:30px;"><a target="iframe1" class="menuLink" href="community.jsp">Community</a>
				</li>		
			</ul>
			</div>
		<hr>
		</div>
		<div id="slide" style="width:870px; height:850px;">
			<iframe name="iframe1" src="initItem.do" frameborder="0" width=870px height=1100px scrolling="no" marginwidth="0" marginheight="0" style=onLoad:setFrameSize()></iframe>
		</div>
	</div>
</div>
</body>
</html>