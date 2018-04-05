<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>나의 정보 수정</title>

<link rel="stylesheet" href="./resources/css/updateMyInfo.css">
<script src="//code.jquery.com/jquery-1.11.0.min.js"></script>
<script src="//code.jquery.com/jquery.min.js"></script>
<script src="//ajax.googleapis.com/ajax/libs/jquery/1/jquery.min.js"></script>
<script src="//d1p7wdleee1q2z.cloudfront.net/post/search.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.2.0/js/bootstrap.min.js"></script>
<script src = "./resources/js/total.js"></script>

</head>
<body>
	<form id="updateForm" action="updateInfo.do" onSubmit="updateInfo(); return false" method="post">
		<table width="1000" cellpadding="0" cellspacing="0">
			<tr>
				<th>이름</th>
				<td><input type="hidden" name="id" id="id" value="${userVO2.id}">
					<input id="name" type="text" value="${userVO2.name}" readonly>아이디(${userVO2.loginId})
				</td>
			</tr>
			
			<tr>
				<th>이메일</th>
				<td><input type="hidden" name="email" id="email">
					<input type="text" size="15" id="firstEmail">@
					<input type="text" size="15" id="lastEmail">
					<select id="emailSelect">
						<option value="" selected>선택</option>
						<option value="naver.com">naver.com</option>
						<option value="nate.com" >nate.com</option>
						<option value="daum.net">daum.net</option>
						<option value="gmail.com">gmail.com</option>
						<option value="hotmail.com">hotmail.com</option>
						<option value="bondingedu.com">bondingedu.com</option>	
						<option value="hanmir.com">hanmir.com</option>
						<option value="empas.com">empas.com</option>
						<option value="dreamwiz.com">dreamwiz.com</option>
						<option value="korea.com">korea.com</option>
						<option value="lycos.co.kr">lycos.co.kr</option>
						<option value="freechal.com">freechal.com</option>
						<option value="1">::직접입력::</option>
					</select>
					<br>아이디 비밀번호 찾기시 사용됩니다. 정확히 입력해 주세요.
				</td>
			</tr>
			
			<tr>
				<th>연락처</th>
				<td><input type="hidden" name="phoneNumber" id="phoneNumber">
					<select id="firstNum" style="width:60px; height:23px;">
						<option value="" selected>선택</option>
						<option value="010">010</option>
						<option value="011">011</option>
						<option value="016">016</option>
						<option value="017">017</option>
						<option value="018">018</option>
						<option value="019">019</option>
					</select> -
					<input id="middleNum" type="text" onkeydown="return isNumberKey(event)" onkeyup='removeChar(event)' style="ime-mode: disabled;" size="6"  maxlength="4"> - 
					<input id="lastNum" type="text" onkeydown="return isNumberKey(event)" onkeyup='removeChar(event)' style="ime-mode: disabled;" size="6" maxlength="4">
				</td>
			</tr>
			
			<tr>
			<th>주소</th>
				<td><input type="hidden" name="address" id="address">
					<input type="text" size="15" class="postcodify_postcode5">
					<input type="button" id="searchPost" value="우편번호"> 
					<input id="firstAddress" type="text" size="30" class="postcodify_address">
				</td>
			</tr>
			
			<tr>
				<th>상세주소</th>
				<td><input id="lastAddress" type="text" size="58" class="postcodify_details"></td>
			</tr>
			
			<tr>
				<th>관심 사항 등록</th>
				<td>
					<c:forEach items="${allCategory}" var="category">
						<input type="checkbox" name="myFavoriteId" value="${category.id}">${category.type}
					</c:forEach>
				</td>
			</tr>
		</table>
		<div class="btn" style="width:1000px">
			<button type="submit">정보 수정</button>
		</div>
	</form>
</body>
</html>