<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>글 상세</title>
<link rel="stylesheet" href="./resources/css/aTagStyle.css">
</head>
<body>	
	<hr>
		<form action="insertReply.do" method="post">
			<input type="hidden" name="parentId" value="${board.id}">	
			<input type="hidden" name="replyerId" value="${userVO2.id}">
			<input type="hidden" name="count" value="${board.count}">
			<input type="hidden" name="id" value="${board.id}">
		<table cellpadding="0" cellspacing="0" style="width:100%; height:100%;">
			<tr>
				<td colspan='3'>&nbsp;<b>${board.title}</b> | ${boardCategory.name}</td>
				<td colspan='2'><p align="right">${board.regDate}</p></td>				
			</tr>
			<tr>
			<td colspan='5'><hr></td>
			</tr>			
			<tr>
				<td colspan='5'>${board.writer}</td>				
			</tr>
			
			<tr>				
				<td colspan='5' align="left" style="word-break:break-all; width:200px;">
				<pre width="100%" style="white-space: pre-wrap; word-wrap: break-word;">${board.content}</pre>
				<br>
				<br>
				<br>
				<br>
				</td>				
			</tr>
			<tr>
				<td colspan='5' bgcolor="white">댓글 조회수 ${board.count} 좋아요<br><br></td>
			</tr>
			</table>
			<table style="width:100%;">
				<tr>
					<th width="5%"></th>
					<th width="10%"></th>
					<th width="50%"></th>
					<th width="20%"></th>
					<th width="10%"></th>
					<th width="5%"></th>
				</tr>	
			<c:forEach items="${replylist}" var="reply">				
				<tr>
					<td></td>
					<td bgcolor="#F9F9F9" bordercolor=#F9F9F9><center>${reply.replyer}</center></td>
					<td bgcolor="#F9F9F9" bordercolor=#F9F9F9 style="word-break:break-all; width:200px;"><pre width="100%" style="white-space: pre-wrap; word-wrap: break-word;">${reply.replytext}</pre></td>
					<td bgcolor="#F9F9F9" bordercolor=#F9F9F9><center><fmt:formatDate value="${reply.regDate}" pattern="yy-MM-dd HH:mm"/></center></td>
						<td>
							<c:choose>
								<c:when test="${reply.replyerId == userVO2.id}">
								<a href="getReply.do?id=${reply.id}">✎</a>
								<a href="deleteReply.do?id=${reply.id}&bcId=${board.bcId}&parentId=${reply.parentId}">✖</a>
								</c:when>
								<c:when test="${userVO2.isAdmin == '1'}">
									<a href="deleteReply.do?id=${reply.id}&bcId=${board.bcId}&parentId=${reply.parentId}">✖</a>
								</c:when>							
								<c:otherwise>           			
         						</c:otherwise>
						</c:choose>						
						</td>
					<td></td>
				</tr>					                
			</c:forEach>

			<tr>
				<td></td>
				<td></td>
				<td><center><textarea name="replytext" cols="80" rows="3" maxlength="200"></textarea></center></td>
				<td><center><input type="submit" style="width:90px; height:52px; background:white" value="등록" /></center></td>
				<td></td>
				<td></td>
			</tr>					
		</table>	
		</form>	
	<hr>
	<p align="right"><a href="getBoardCategory.do?bcId=${board.bcId}">글쓰기</a>&nbsp;&nbsp;&nbsp;
   
    <!-- 글이 쓴 자신이거나 이면 수정 삭제버튼 생성 관리자이면 삭제버튼 무조건 생성-->    
    <c:if test="${board.writerId == userVO2.id}">
        <a href="deleteBoard.do?id=${board.id}">글삭제</a>&nbsp;&nbsp;&nbsp;
        <a href="getBoard.do?id=${board.id}&modify=true">글수정</a>&nbsp;&nbsp;&nbsp;
    </c:if>
    <c:if test="${userVO2.isAdmin =='1'}">
	<a href="deleteBoard.do?id=${board.id}">글삭제</a>&nbsp;&nbsp;&nbsp;
	</c:if>	
	<a href="getBoardList.do?bcId=${board.bcId}">글목록</a>&nbsp;&nbsp;&nbsp;
	</p>
</body>
</html>