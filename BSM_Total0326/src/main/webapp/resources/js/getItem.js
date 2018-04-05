	function putCart() {
		var stockQuantity = parseInt($("#stockQuantity").val());
		var salesQuantity = $('#salesQuantity').val();
		var id = $('#id').val();
		if (stockQuantity < salesQuantity) {
			alert("재고 수량을 넘기셨습니다. 다시 설정해주세요.");
		} else if (salesQuantity <= 0) {
			alert("수량을 다시 설정해주세요.");
			$("#salesQuantity").val(1);
		} else {
			$.ajax({
				type : 'POST',
				url : 'putCart.do',
				data : { "salesQuantity" : salesQuantity, "id" : id},
				success : function(data) {
					alert("카트에 고히 넣어두었습니다. 언제든 구매 가능 합니다.");
				}
			});
		}
	}
	
	//상품 사기전 점검
	function formCheck() {
		var stockQuantity = parseInt($("#stockQuantity").val());
		var salesQuantity = parseInt($("#salesQuantity").val());
		if ((stockQuantity >= salesQuantity)) {
			$("#buyForm").submit();
			return true;
		} else {
			alert("구매 수량이 재고 수량을 넘기셨습니다. 다시 설정해주세요.")
			$("#salesQuantity").val(1);
		}
	}
	
	//수량 변경
	function updateCount(count) {
		var salesQuantity = parseInt($("#salesQuantity").val());
		var count = parseInt(count);
		if (salesQuantity + count > 0 ) {
			$("#salesQuantity").val(salesQuantity + count);
		} else {
			alert("최소 수량입니다.");
			$("#salesQuantity").val(1);
		}
	}