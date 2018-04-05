	//이메일 주소
	$(function(){
		$('#emailSelect').change(function(){
			if($('#emailSelect').val() == "1"){
				$("#lastEmail").val(""); //값 초기화
				$("#lastEmail").prop("readonly",false); //활성화
			} else if($('#emailSelect').val() == ""){
				$("#lastEmail").val(""); //값 초기화
				$("#lastEmail").prop("readonly",true); //textBox 비활성화
			} else {
				$("#lastEmail").val($('#emailSelect').val()); //선택값 입력
				$("#lastEmail").prop("readonly",true); //비활성화
			}
		});
	});
	
	//전화번호 포맷 설정 정규식
	function phoneFomatter(phoneNumber) {
		var regExp = /^01([0|1|6|7|8|9]?)-?([0-9]{3,4})-?([0-9]{4})$/;
		if (!regExp.test(phoneNumber)) {
			return false;
		} else {
			return true;
		}
	}
	
	//주민번호 포맷 설정 정규식
	function securityNumFomatter(securityNumber) {
		var regExp = /^(?:[0-9]{2}(?:0[1-9]|1[0-2])(?:0[1-9]|[1,2][0-9]|3[0,1]))-[1-4][0-9]{6}$/;
		if (!regExp.test(securityNumber)) {
			return false;
		} else {
			return true;
		}
	}
	
	//주소 검색
	$(function() { 
		$("#searchPost").postcodifyPopUp(); 
	});
	
	//키코드가 숫자가 아니면 입력이 되지 않게
	function isNumberKey(event){
		event = event || window.event;
		var keyCode = event.keyCode;
		//48~57 96~105까지는 숫자키, 8=backspace, 46=delete, 37=왼쪽, 39=오른쪽, 9=tap 
		if( (keyCode >=48 && keyCode <= 57) || (keyCode >=96 && keyCode <= 105) || (keyCode == 8) 
				|| (keyCode == 46) || (keyCode == 37) || (keyCode == 39) || (keyCode == 9)) {
			return true;
		} else {
			return false;
		}
	}
	
	//한글이 입력되지 않도록 함
	function removeChar(event) {
		event = event || window.event;
		var keyID = (event.which) ? event.which : event.keyCode;
		if ( keyID == 8 || keyID == 46 || keyID == 37 || keyID == 39 ) 
			return;
		else
			event.target.value = event.target.value.replace(/[^0-9]/g, "");
	}
	
	//마지막 회원정보 수정 버튼 이벤트
	function updateInfo() {
		var id = $('#id').val()
		var email = $('#firstEmail').val() + "@" + $('#lastEmail').val();
		var address = $('#firstAddress').val() + " " + $('#lastAddress').val();
		var phoneNumber = $("#firstNum").find(":selected").val() + "-" + $("#middleNum").val() + "-" + $("#lastNum").val();
		$('#email').val(email);
		$('#address').val(address);
		$('#phoneNumber').val(phoneNumber);
		if ($('#firstEmail').val() == "" || $('#lastEmail').val() == "" || address == " ") {
			alert('빈 칸이 있습니다. 제대로 입력 바랍니다.')	//빈 칸이 있을 시
		} else if (!phoneFomatter(phoneNumber)) {	//전화 번호 포맷이 틀렸을때
			alert('전화 번호를 확인 바랍니다.')
		} else {
			$("#updateForm").submit(); 
			return true;
		}
	}