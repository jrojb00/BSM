<%@ page language="java" contentType="text/html;charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>글 목록</title>
<link rel="stylesheet" href="./resources/css/aTagStyle.css">

<style>
.a
{
    border:1px solid white;
    text-decoration:none;
    color:black;
    background:white;
    font-size:11pt;
    font
}
</style>
<script src = "./resources/js/boardList.js"></script>
<script src="//code.jquery.com/jquery.min.js"></script>
</head>
<body>
	<center><h3>${boardCategory.name}</h3></center>
<!-- 		<h3> -->
<%-- 		<c:if test="${empty userVO.name}"> --%>
<!-- 			로그인하세요; -->
<%-- 		</c:if> --%>
<%-- 		${userVO.name}님! 게시판에 오신걸 환영합니다...<a href="logout.do">Log-out</a> --%>
<%-- <%-- 			<%= user.getName() %> 님 환영합니다 ♥<a href="logout.do"></a> --%>
<!-- 		</h3> -->

	<center>		
		<!-- 검색종료 -->
		<table border="0" cellpadding="0" cellspacing="0" width="80%">
			<tr>
				<th align="center" bgcolor="#F9F9F9" width="100"></th>
				<th align="center" bgcolor="#F9F9F9" width="200">제목</th>
				<th align="center" bgcolor="#F9F9F9" width="150">작성자</th>
				<th align="center" bgcolor="#F9F9F9" width="150">작성일</th>
				<th align="center" bgcolor="#F9F9F9" width="100">조회</th>
			</tr>
			<c:forEach items="${boardList}" var="boardVO">			
			<tr>
				<td align="left">${boardVO.id}</td>
				<td align="left"><a
					href="getBoard.do?id=${boardVO.id}&isModify=false"> ${boardVO.title}</a></td>
				<td align="center">${boardVO.writer}</td>
				<td align="center">${boardVO.regDate}</td>
				<td align="center">${boardVO.count}</td>
			</tr>			
			</c:forEach>
			<tr>
			<td colspan="5"><hr></td>
			</tr>	
			<tr>
			<td></td>
			<td></td>
			<td></td>
			<td></td>
			<td align="center"><a href="insertBoard.do?id=${boardCategory.id}">글쓰기</a></td>
			</tr>
			<tr>
				<td colspan="5">
					<form id="boardListForm" action="getBoardList.do" method="post">
						<center>
							<input type="hidden" name="bcId" value="${boardCategory.id}"/>
							<c:if test="${boardPage.prev}">
								<input type="hidden" id="boardPrev" name="prev" value="false">
								<input type="submit" onclick="prevBorad()" class="a" value="&laquo"/>
							</c:if>
							<c:forEach var="pageNumber" begin="${boardPage.startPage}" end="${boardPage.endPage}" step="1">
									<input type="submit" class="a" name="pageNumber" value="${pageNumber}" style="cursor:pointer"/>
							</c:forEach>
							<c:if test="${boardPage.next}">
								<input type="hidden" id="boardNext" name="next" value="false">
								<input type="button" onclick="nextBoard()" class="a" value="&raquo"/>
							</c:if>
						</center>
					</form>
				</td>
			</tr>
			<tr>
				<td colspan="5">
				<!-- 검색 시작 -->
					<center>
						<form action="getBoardList.do" method="post">
							<input name="bcId" type="hidden" value="${boardCategory.id}"/>
							<select name="searchCondition">					
									<option value="TITLE">제목</option>
									<option value="CONTENT">내용</option>							
							</select> <input name="searchKeyword" type="text" /> 					
							<input type="submit" value="검색" />	
						</form>
					</center>
				</td>			
			</tr>
		</table>		
		<br> 		
	</center>		
</body>
</html>