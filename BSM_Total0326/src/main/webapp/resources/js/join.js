	var checkID = false;
	var checkPassword = false;
	
	//아이디 중복 검사
	function checkId() {
		var loginId = $('#loginId').val().replace(/\s/gi, '');
		if (loginId == "") {
			alert("아이디를 입력해주세요");
			$("#Confirm").css("color", "red");
			$("#Confirm").text("사용 할 수 없는 아이디");
		} else {
			$.ajax({
				type : 'POST',
				url : 'checkID.do',
				data : { "loginId" : loginId},
				success : function(data) {
					if (data != 0) {
						alert("이미 가입된 아이디 입니다.");
						$("#Confirm").css("color", "red");
						$("#Confirm").text("이미 가입된 아이디");
						checkID = false;
					} else {
						alert("사용 할 수 있는 아이디 입니다.");
						$("#Confirm").css("color", "blue");
						$("#Confirm").text("사용 할 수 있는 아이디");
						checkID = true;
					}
				}
			});
		}
	}
	
	//패스워드 동일 검사	
	$(function(){
		$('#loginPassword').keyup(function() {
			$('font[name=check]').text('');
		});
		$('#checkPassword').keyup(function() {
			var checkText = $('#checkFont'); 
			if ($('#loginPassword').val()!=$('#checkPassword').val()) {
				checkPassword = false;
				checkText.text('');
				checkText.css('color', 'red');
				checkText.html("패스워드가 다릅니다");
			} else {
				checkPassword = true;
				checkText.text('');
				checkText.css('color', 'blue');
				checkText.html("패스워드가 동일합니다");
			}
		});
	});
	
	//이메일 주소
	$(function(){
		$('#email_select').change(function(){
			if($('#email_select').val() == "1"){
				$("#last_email").val(""); //값 초기화
				$("#last_email").prop("readonly",false); //활성화
			} else if($('#email_select').val() == ""){
				$("#last_email").val(""); //값 초기화
				$("#last_email").prop("readonly",true); //textBox 비활성화
			} else {
				$("#last_email").val($('#email_select').val()); //선택값 입력
				$("#last_email").prop("readonly",true); //비활성화
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
	
	//아이디 온포커스
	function idOnFocus() {
		checkID = false;
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
	
	//마지막 회원가입 버튼 이벤트
	function doJoin() {
		var loginId = $('#loginId').val();
		var loginPassword = $('#loginPassword').val();
		var name = $('#name').val();
		var email = $('#first_email').val() + "@" + $('#last_email').val();
		var address = $('#firstAddress').val() + " " + $('#lastAddress').val();
		var gender = $(':radio[name="gender"]:checked').val();
		var phoneNumber = $("#firstNum").find(":selected").val() + "-" + $("#middleNum").val() + "-" + $("#lastNum").val();
		var securityNumber = $("#firstSecuNum").val() + "-" + $("#lastSecuNum").val();
		if (!checkID) {							//아이디 중복검사가 안됐을 시
			alert('중복 확인을 하여주세요.')
		} else if (!checkPassword) {			//패스워드 확인이 틀렸을 시
			alert('비밀 번호를 확인하여 주세요.')
		} else if (name == "" || loginId == "" || loginPassword == "" || email == "" || address == "" || phoneNumber == "" || gender == "" || securityNumber == "") {
			alert('빈 칸이 있습니다. 제대로 입력 바랍니다.')	//빈 칸이 있을 시
		} else if (!phoneFomatter(phoneNumber)) {	//전화 번호 포맷이 틀렸을때
			alert('전화 번호를 확인 바랍니다.')
		} else if (!securityNumFomatter(securityNumber)) {	//주민 번호 포맷이 틀렸을때
			alert('주민 번호를 확인 입력 바랍니다.')	
		} else {
			$.ajax({
				type : 'POST',
				url : 'join.do',
				data : {"loginId" : loginId, "loginPassword" : loginPassword, "name" : name, "gender" : gender, "securityNumber" : securityNumber, "email" : email, "address" : address, "phoneNumber" : phoneNumber},
				success : function(data) {
					alert('가입 되었습니다.')
					 $.ajax({
						type : 'POST',
						url : 'login.do',
						data : { "loginId" : loginId, "loginPassword" : loginPassword},
						success : function(data) {
							parent.document.location.reload();
						    parent.opener.location.reload();
						}
					});
				}
			});
		}
	}