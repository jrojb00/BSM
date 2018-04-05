<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>새글등록</title>
<link rel="stylesheet" href="./resources/css/aTagStyle.css">
</head>
<body>
	${boardCategory.name}
	<center>
		<hr>
		<form action="insertBoard.do" method="POST">
			<input type="hidden" name="bcId" value="${boardCategory.id}">	
			<input type="hidden" name="writerId" value="${userVO2.id}">						
			<table border="0" cellpadding="0" cellspacing="0">
				<tr>
					<td width="70">제목</td>
					<td align="left"><input type="text" name="title" size="30" maxlength="20"/> (최대 20자 까지만 가능합니다.)</td>
				</tr>
				<tr>
					<td><br>작성자</td>
					<td align="left"><br>
					<input type="hidden" name="writer" size="10" value="${userVO2.name}" readonly/>${userVO2.name}</td>					
				</tr>
				<tr>
					<td><br>내용</td>
					<td align="left"><br><textarea name="content" cols="70" rows="20"></textarea></td>
				</tr>
				<tr> 
					<td colspan="2" align="center">
						<p align="right"><input type="submit" value="등록" /></p>
					</td>
				</tr>
			</table>
		</form>
		<hr>		
	</center>
	<p align="right"><a href="getBoardList.jsp">글목록</a>&nbsp;&nbsp;&nbsp;</p>
</body>
</html>