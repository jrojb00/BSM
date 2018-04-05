<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>글 수정</title>
</head>
<body>
	${boardCategory.name}
		<center>		
			<hr>
		<form action="updateBoard.do" method="post">
			<input name="id" type="hidden" value="${board.id}" />
			<input name="bcId" type="hidden" value="${board.bcId}" />
			<table border="0" cellpadding="0" cellspacing="0">
				<tr>
					<td width="70">제목</td>
					<td align="left"><input name="title" type="text" value="${board.title}" size="30" maxlength="20"/> (최대 20자 까지만 가능합니다.)</td>
				</tr>
				<tr>
					<td><br>작성자</td>
					<td align="left"><br>
						<input type="hidden" name="writer" size="10" value="${userVO.name}" readonly/>${userVO.name}</td>					
				</tr>
				<tr>
					<td><br>내용</td>
					<td align="left"><br><textarea name="content" cols="70" rows="20">${board.content}</textarea></td>
				</tr>
				<tr>
					<td></td>
					<td align="center"><input type="submit"
						value="글 수정" /></td>				
				</tr>			
			</table>		
		</form>
			<hr>		
		</center>
</body>
</html>