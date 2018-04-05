<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>댓글 수정</title>
</head>
<body>	
	${boardCategory.name}
		<center>		
			<hr>
		<form action="updateReply.do" method="post">
			<input name="id" type="hidden" value="${reply.id}" />
			<input name="parentId" type="hidden" value="${reply.parentId}" />
			<table border="0" cellpadding="0" cellspacing="0">	
				<tr>
				<td colspan='2'><b>댓글수정</b></td>
				</tr>
				<tr>
					<td width="70"><br>작성자</td>
					<td align="left"><br>
						<input type="hidden" name="replyer" size="10" value="${reply.replyer}" readonly/>${reply.replyer}</td>					
				</tr>
				<tr>
					<td><br>내용</td>
					<td align="left"><br><textarea name="replytext" cols="70" rows="20">${reply.replytext}</textarea></td>
				</tr>
				<tr>
					<td></td>
					<td align="center"><input type="submit" value="글 수정" /></td>				
				</tr>			
			</table>		
		</form>			
			<hr>		
		</center>
</body>
</html>