<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Join</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.2.0/css/bootstrap.min.css">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.2.0/css/bootstrap-theme.min.css">
<link rel="stylesheet" href="./resources/css/join.css">
<script src="//code.jquery.com/jquery-1.11.0.min.js"></script>
<script src="//code.jquery.com/jquery.min.js"></script>
<script src="//ajax.googleapis.com/ajax/libs/jquery/1/jquery.min.js"></script>
<script src="//d1p7wdleee1q2z.cloudfront.net/post/search.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.2.0/js/bootstrap.min.js"></script>
<script src = "./resources/js/total.js"></script>
</head>
<body>
	<form>
		<table width="1000" cellpadding="0" cellspacing="0">
			
			<tr><th>아이디</th>
				<td><input type="text" id="loginId" onfocus="idOnFocus()"><input type="button" value="중복 확인" onclick="checkId()"><label id="Confirm">회원정보 수정시 변경불가</label></td>
			</tr>
			
			<tr><th>비밀번호</th>
				<td><input type="password" id="loginPassword">8~12자의 영문 대소문자,숫자 특수문자를 조합해서 사용하실수 있습니다.</td>
			</tr>
			
			<tr><th>비밀번호 확인</th>
				<td><input type="password" id="checkPassword"><font id="checkFont"></font></td>
			</tr>
			
		</table>
		
		<br>
		
		<table width="1000" cellpadding="0" cellspacing="0">
			
			<tr>
				<th>이름</th>
				<td><input id="name" type="text"></td>
			</tr>
			
			<tr>
				<th>주민번호</th>
				<td>
					<input id="firstSecuNum" type="text" onkeydown="return isNumberKey(event)" onkeyup='removeChar(event)' style="ime-mode: disabled;" maxlength="6"> - 
					<input id="lastSecuNum" type="password" onkeydown="return isNumberKey(event)" onkeyup='removeChar(event)' style="ime-mode: disabled;" maxlength="7"> 
				</td>
			</tr>
			
			<tr>
				<th>이메일</th>
				<td><input type="text" size="15" id="first_email">@
					<input type="text" size="15" id="last_email">
					<select id="email_select">
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
				<td>
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
				<td>
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
				<th>성별</th>
				<td>
					<div class="btn-group" data-toggle="buttons">
						<label class="btn btn-primary active" style="width:150px; height:30px">
							<input type="radio" name="gender" id="option1" autocomplete="off" checked value="male"> 남자
						</label>
						<label class="btn btn-primary" style="width:150px; height:30px">
							<input type="radio" name="gender" id="option2" autocomplete="off" value="female"> 여자
						</label>
					</div>
				</td>
			</tr>
		</table>
	</form>
	<div class="btn" style="width:1000px">
		<button onclick="doJoin()">회원가입</button>
	</div>
</body>
</html>