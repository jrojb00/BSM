//getItemList들 모두
//다음 게시물 블록 보기
function nextItem() {
	$('#itemNext').val('true');
	$('#itemListForm').submit();
}

function prevItem() {
	$('#itemPrev').val('true');
	$('#itemListForm').submit();
}