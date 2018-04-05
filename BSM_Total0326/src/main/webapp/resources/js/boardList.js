//getBoardList.jsp
//다음 게시물 블록 보기
function nextBoard() {
	$('#boardNext').val('true');
	$('#boardListForm').submit();
}

function prevBoard() {
	$('#boardPrev').val('true');
	$('#boardListForm').submit();
}