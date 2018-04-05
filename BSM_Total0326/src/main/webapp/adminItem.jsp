<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>BSM 관리자모드/상품관리</title>
	
<!-- jQuery  -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<!-- bootstrap JS -->
<script src="https://netdna.bootstrapcdn.com/bootstrap/3.0.0/js/bootstrap.min.js"></script>
<!-- bootstrap CSS -->
<link href="https://netdna.bootstrapcdn.com/bootstrap/3.0.0/css/bootstrap.min.css" rel="stylesheet">
<link rel="stylesheet" href="./resources/css/adminAnalysis.css">
<script src="http://code.jquery.com/jquery-1.11.0.min.js"></script>
<script src = "./resources/js/resetFormElement.js"></script> 
<script src = "./resources/js/total.js"></script>
</head>
<body>
	<form action="buyItem.do" method="POST" >
<%-- 		<input type="hidden" id="id" name="id" value="${itemVO.id}"> --%>
		<a href="adminModeIndex.jsp"><b>&lt;&lt;뒤로가기</b></a>
		<br>
		<br>
			<b>관리자모드 / 상품관리</b>
		<br>
		<hr align="left" style="width: 80%;">
	</form>
	<!-- 상품등록테이블 시작 -->
	<form name="myForm" enctype="multipart/form-data" method="POST">	
		<table border="1">
			<tr>
	<!-- 이미지 디폴트 설정 -->
				<td width=250px>	
					<div id="image_preview" align=center>
     					<img src="#" width="245px" height="360px"/><br>
     					<a href="#">Remove</a>
 					</div>
				</td>
				
				<td>
					<table>
						<tr>
							<td colspan='2' align="right"><label id="aliveLabel"></label></td>	
						</tr>					
						<tr>
							<td>&nbsp;상품명&nbsp;</td>
							<td><input type="text" id="newName" name="name" style="width:303px"/>
								<input type="hidden" id="newId" name="id" style="width:150px" value="0"/></td>				
						</tr>
						
						<tr>
							<td>&nbsp;종류&nbsp;</td>
							<td><select id="newIcId" name="icId" style="width:155px">
								<option value="1"> 자바 </option>
								<option value="8"> C/C++ </option>
								<option value="2"> 데이터베이스 </option>
								<option value="3"> 스프링 </option>
								<option value="4"> HTML</option>
								<option value="5"> Servlet/JSP</option>
								<option value="6"> AndroidAPP</option>
								<option value="7"> 기타</option>	
								</select>
							</td>
						</tr>
						
						<tr>
							<td>&nbsp;가격</td>
							<td><input id="newPrice" type="text" name="price" style="width:150px"/></td>
						</tr>
						
						<tr>
							<td>&nbsp;수량</td>
							<td><input id="newStockQuantity" type="text" name="stockQuantity" style="width:150px"/></td>
						</tr>
						
						<tr>
							<td>&nbsp;설명</td>
							<td><textarea id="newComment" cols="40" rows="10" name="comment"></textarea></td>
						</tr>
						
	  						<tr>
	    						<td>&nbsp;이미지</td>
	    						<td><input type="file" name="imgFile" id="imgFile" /></td>
	    					</tr>
	    					     	
						<tr>
							<td colspan="2"><p align="right">
								<input type="button" value="수정" onclick="mySubmit('updateBtn')"/>
								<input type="button" value="삭제" onclick="mySubmit('deleteBtn')"/>
								<input type="button" value="등록" onclick="mySubmit('addBtn')"/><p></td>
						</tr> 
					</table>
				</td>
			</tr>
		</table>
	</form>
	
	<br>
	
	<table>
		<tr>
			<td><p>Total Item : ${fn:length(itemList)}</p></td>
			<td><!-- 상품정렬 선택창 -->
				<p align="right">상품정렬 
				<select name="type" style="width:120px">
					<option value="id">제품번호</option>
					<option value="alive">상품상태</option>
					<option value="name">품명</option>
					<option value="price">가격</option>
					<option value="stockQuantity">재고수량</option>
					<option value="salesQuantity">판매수량</option>
					<option value="totalSalesAmount">총판매액수</option>						
				</select>
			</td>
		</tr>
	
		<tr>
			<td colspan='2'>
			<!-- 상품 진열테이블 -->	
				<table width="800" border="1" cellpadding="0" cellspacing="0" style="border-collapse: collapse, table-layout: fixed">
					<tr>
						<th><center>제품번호</center></th>
						<th><center>상태</center></th>
						<th><center>품명</center></th>
						<th><center>종류</center></th>
     					<th><center>가격</center></th>
						<th><center>재고수량</center></th>
						<th><center>판매수량</center></th>
						<th><center>총판매액수</center></th>						
					</tr>
					
					<c:forEach items="${itemList}" var="item">
					<tr>
						<td><center>${item.id}</center></td>
						<td style="text-align: right;"><center><c:if test="${item.alive == 'true'}">활성</c:if><c:if test="${item.alive == 'false'}"><font color="red">비활성</font></c:if></center></td>	
						<td><a onclick="detailClick(${item.id})" style="cursor:pointer">${item.name}</a></td>
						<td><center>${item.type}</center></td>
						<td style="text-align: right;">${item.price}</td>
						<td style="text-align: right;">${item.stockQuantity}</td>
						<td style="text-align: right;">${item.salesQuantity}</td>
						<td style="text-align: right;">${item.totalSalesAmount}</td>						
<%-- 						<input type="hidden" id="id" name="id" value="${item.imgPath}">			 --%>
						<%-- 				<td>${item.icId}</td> --%>
					</tr>		
					</c:forEach>
				</table>	
			</td>
		</tr>
			
		<tr>
			<td colspan='2'>	
				<p>
				<!-- 검색 시작 -->					
					<form id="itemListForm" action="getItemList.do" method="post" align="right">
					<input type="button" onclick="getAllItemList()" value="전체목록">
						<select id="itemSearchCondition" name="searchCondition" onchange="itemSelected(this.value)">
								<option value="id">제품번호</option>
								<option value="name">상품명</option>
								<option value="type">종류</option>
						</select>
						<input id="itemSearchKeyword" onkeydown="return isNumberKey(event)" onkeyup='removeChar(event)' name="searchKeyword" type="text"/> 					
						<input type="submit" value="검색" />	
					</form>				
				</p>
			</td>			
		</tr>
			
	</table>
	<script type="text/javascript">
	$('#imgFile').on('change', function() {

		ext = $(this).val().split('.').pop().toLowerCase(); //확장자를 찾아서 소문자로 변환해 줌

		//배열에 추출한 확장자가 존재하는지 체크
		if($.inArray(ext, ['gif', 'png', 'jpg', 'jpeg']) == -1) {
			resetFormElement($(this)); //폼 초기화
			window.alert('이미지 파일이 아닙니다! (gif, png, jpg, jpeg 만 업로드 가능)');
		} else {
			file = $('#imgFile').prop("files")[0];
			blobURL = window.URL.createObjectURL(file);
			$('#image_preview img').attr('src', blobURL);

			$('#image_preview').slideDown(); //업로드한 이미지 미리보기 
			//$("tr").append($('#image_preview').slideDown());
			//$(this).slideUp(); //파일 양식 감춤
		}
	});

	/**
	onclick event handler for the delete button.
	It removes the image, clears and unhides the file input field.
	 */
	$('#image_preview a').bind('click', function() {
		resetFormElement($('#imgFile')); //전달한 양식 초기화
		$('#imgFile').slideDown(); //파일 양식 보여줌
		$(this).parent().slideUp(); //미리 보기 영역 감춤
		return false; //기본 이벤트 막음
	});
	</script>
</body>
</html>