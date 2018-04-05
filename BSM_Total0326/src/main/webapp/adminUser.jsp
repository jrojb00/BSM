<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>BSM 관리자모드/회원관리</title>
	
<!-- bootstrap CSS -->
<link href="https://netdna.bootstrapcdn.com/bootstrap/3.0.0/css/bootstrap.min.css" rel="stylesheet">
<link rel="stylesheet" href="./resources/css/adminAnalysis.css">
<!-- jQuery  -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<!-- bootstrap JS -->
<script src="https://netdna.bootstrapcdn.com/bootstrap/3.0.0/js/bootstrap.min.js"></script>
<script src = "./resources/js/resetFormElement.js"></script> 
<script src="http://code.jquery.com/jquery-1.11.0.min.js"></script>	
<script src = "./resources/js/total.js"></script>	
</head>
<body>		
	<a href="adminModeIndex.jsp"><b>&lt;&lt;뒤로가기</b></a>
		<br><br>
	<b>관리자모드 / 회원관리</b>
		<br>
	<hr align="left" style="width: 80%;">	
	<table>
		<tr>
			<td><p>Total Member : ${fn:length(userList)}</p>
			</td>
			<td><!-- 회원정렬 선택창 -->
				<p align="right">
					<select name="type" style="width:120px">
						<option value="pNum">회원번호</option>
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
				<!-- 회원 진열테이블 -->
				<table width="1000" border="1" cellpadding="0" cellspacing="0" style="border-collapse: collapse">
					<tr>
						<th>회원번호</th>				
						<th width=100px><center>아이디</center></th>
						<th width=100px><center>이름</center></th>
						<th width=100px><center>회원등급</center></th>
		     			<th><center>성별</center></th>
						<th><center>이메일</center></th>
						<th><center>주소</center></th>
						<th width=120px><center>전화번호</center></th>
						<th><center></center></th>
					</tr>
				<c:forEach items="${userList}" var="user">
					<tr>
						<td><center>${user.id}</center></td>				
						<td><center>${user.loginId}</center></td>
						<td><center>${user.name}</center></td>
						<td><center><c:if test="${user.isAdmin == '0'}">일반회원</c:if><c:if test="${user.isAdmin == '1'}">운영자</c:if></center></td>
						<td><center><c:if test="${user.gender == 'female'}">여</c:if><c:if test="${user.gender == 'male'}">남</c:if></center>
						<td><center>${user.email}</center></td>
						<td>${user.address}</td>
						<td><center>${user.phoneNumber}</center></td>
						<td width=50px><center><a class="checkBtn" style="cursor:pointer">삭제</a></center></td>
					</tr>		
				</c:forEach>
				</table>		
	
			</td>	
		</tr>
		<tr>
			<td colspan='2'>
				<p>
				<!-- 검색 시작 -->					
					<form id="userListForm" action="getUserList.do" method="post" align="right">
					<input type="button" onclick="getAllUserList()" value="전체목록">
						<select id="userSearchCondition" name="searchCondition" onchange="userSelected(this.value)">
								<option value="id">회원번호</option>
								<option value="loginId">아이디 </option>
								<option value="name">이름 </option>	
								<option value="address">주소 </option>															
						</select>
						<input id="userSearchKeyword" onkeydown="return isNumberKey(event)" onkeyup='removeChar(event)' name="searchKeyword" type="text"/> 					
						<input type="submit" value="검색" />	
					</form>
					</p>
			</td>
		</tr>	
	</table>
	
    <script>       
    // 버튼 클릭시 Row 값 가져오기    
        $(".checkBtn").click(function(){ 
        var checkBtn = $(this);
        
        // checkBtn.parent() : checkBtn의 부모는 <td>이다.
        // checkBtn.parent().parent() : <td>의 부모이므로 <tr>이다.
        var tr = checkBtn.parent().parent();
        var td = tr.children();
        
        console.log("클릭한 Row의 모든 데이터 : "+tr.text());
        
        var id = td.eq(0).text();
        var name = td.eq(1).text();
        var type = td.eq(2).text();
        var price = td.eq(3).text();
        var stockQuantity = td.eq(4).text();
        var icId = td.eq(7).text();
        var comment = td.eq(8).text();
        $("#newId").val(id);
        $("#newIcId").val(icId);
        $("#newComment").val(comment);
        $("#newName").val(name);
        $("#newPrice").val(price);
        $("#newStockQuantity").val(stockQuantity);
    });
	</script>
</body>
</html>