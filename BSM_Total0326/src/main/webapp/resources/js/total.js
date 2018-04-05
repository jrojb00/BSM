//main
function chatting(){
	var option="width=600, height=830, resizable=no, status=no;";
	window.open("chat.jsp", "", option);
}

function setFrameSize(){
	var bodyheight = 0;
	if(window.innerWidth){    // IE가 아닐 때
		bodyheight = window.innerHeight;
	}else{
		bodyheight = document.body.clientHeight;
	}
	document.getElementById("resize").height = bodyheight;
}

function search() {
	var searchWord = $('#searchWord').val().replace(/\^/gi, '');
	if (searchWord == "") {
		alert("검색어를 입력해주세요");
	} else {
		$("iframe[name=iframe1]").attr('src', 'search.do?word='+searchWord);
	}
}

function logout() {
	$.ajax({
		type : 'POST',
		url : 'logout.do',
		data : {},
		success : function(data) {
			parent.document.location.reload();
			parent.opener.location.reload();
		}
	});
}

function autoResize(i)
{
	var iframeHeight=
		(i).contentWindow.document.body.scrollHeight;
	(i).height=iframeHeight+20;
}


//adminAnalysis
function drawChart() {
	if (isFirst) {
		isFirst = false;	
	} else {
		var minDate = $('#minDate').val();
		var maxDate = $('#maxDate').val();
		var categorize = $("#categorize").find(":selected").val();
		if (maxDate <= minDate) {
			alert('기간을 다시 정해주십시오.');
		} else if (categorize == "none") {
			alert('분석 타입 설정을 해주십시오.');
		} else {
			$.ajax({
				type : 'POST',
				url : 'tendencyAnalysis.do',
				data : { "minDate" : minDate, "maxDate" : maxDate, "categorize" : categorize},
				success : function(result) {
					result = result.replace(/'/g, '"');
					var result = JSON.parse(result);
					var data = google.visualization.arrayToDataTable(result);
					var options = {
							title: '판매 추이 분석',
					};
					if (categorize == "type") {
						var chart = new google.visualization.PieChart(document.getElementById('chart_div'));
					} else if (categorize == "birth" || categorize == "gender"){
						var chart = new google.visualization.ColumnChart(document.getElementById('chart_div'));
					} else {
						var day = 1000 * 60 * 60 * 24;
						var max = new Date(maxDate);
						var min = new Date(minDate);
						if (categorize == "days") {
							if (parseInt((max - min)/day) > 31) {
								alert('일별 분석은 최대 31일을 기준으로 한합니다. 기간을 다시 정해주세요.');
							} else {
								var chart = new google.visualization.LineChart(document.getElementById('chart_div'));
							}
						} else if (categorize == "months") {
							if (parseInt((max - min)/day) > 366) {
								alert('월별 분석은 최대 365일을 기준으로 한합니다. 기간을 다시 정해주세요.');
							} else {
								var chart = new google.visualization.LineChart(document.getElementById('chart_div'));
							}
						} else {
							var chart = new google.visualization.LineChart(document.getElementById('chart_div'));
						}
					}
					chart.draw(data, options);
					$("#dateLabel").text(minDate + "에서 " + maxDate + "까지 데이터입니다.");
				}
			});
		}
	}
}

//adminItem
var isFirstItem = true;

//버튼 클릭시 모든 item 값 가져오기 ajax
function detailClick(id){ 
	console.log(event);
	$.ajax({
		type : 'post',
		url : 'getItem4Admin.do',
		data : {"id": id},
		dataType: "json",
		success : function(data){
			setMainItem(data);    			
		}
	});
}  

//ajax로 받은값 텍스트창에 뿌리기
function setMainItem(data){ 
	$("#newId").val(data.id);
	$("#newIcId").val(data.icId);
	$("#newComment").val(data.comment);
	$("#newName").val(data.name);
	$("#newPrice").val(data.price);
	$("#newStockQuantity").val(data.stockQuantity);	
	$("#newAlive").val(data.alive);
	resetFormElement($('#imgFile'));
	$('#image_preview img').attr('src', 'getItemImg.do?id=' + data.id);
	$('#image_preview').slideDown(); //업로드한 이미지 미리보기 

	checkAlive(data.alive);		
};

// 상품등록, 수정 ,삭제 onclick버튼 함수
function mySubmit(index) {
	if (isFirstItem) { 
		if (index == 'addBtn') {
			document.myForm.action='insertItem.do';
		} else if (index == 'updateBtn') {
			document.myForm.action='updateItem.do';
		} else if (index == 'deleteBtn') {
			document.myForm.action='deleteItem.do';
		}
		isFirstItem = false;
		document.myForm.submit();
	}
}

function checkAlive(result) {  		
	if(result){
		document.getElementById('aliveLabel').innerHTML = "<font color='blue'>활성상태 상품입니다</font>";
	} else 
		document.getElementById('aliveLabel').innerHTML = "<font color='red'>비활성상태 상품입니다</font>";
}  	

//chat

var buf = "";
var webSocket = new WebSocket('ws://192.168.0.31:8080/bsm/broadcasting');
webSocket.onerror = function(event) {
	onError(event)
};
webSocket.onopen = function(event) {
	onOpen(event)
};
webSocket.onmessage = function(event) {
	onMessage(event)
};
function onMessage(event) {
	var parsed = JSON.parse(event.data);
	var text = parsed.name + " : " + parsed.message;
	$('#chatList').append(
			'<div class="row">'
			+ '<div class="col-lg-12">'
			+ '<div class="media">'
			+ '<a class="pull-left" href="#">'
			+ '<img class="media-object img-circle" src="resources/image/icon.png" alt="">'
			+ '</a>' + '<div class="media-body">'
			+ '<h4 class="media-heading">' + parsed.name
			+ '<span class="small pull-right">' + parsed.time
			+ '</span>' + '</h4>' + '<pre>' + parsed.message
			+ '</pre>' + '</div>' + '</div>' + '</div>'
			+ '</div>' + '<hr>');
	$('#chatList').scrollTop($('#chatList')[0].scrollHeight);
}

function onOpen(event) {
}

function onError(event) {
//	alert(event.data);
}

function send() {
	var inputMessage = $("#inputMessage");
	var inputName = $("#inputName");
	var today = new Date();
	buf = ""
//		buf += (today.getYear()+1900) + "-" + (today.getMonth() + 1) + "-" + today.getDate() + " ";

		buf += (today.getYear()+1900) + "-";

	if((today.getMonth()+1)<10) {			
		buf += "0" + (today.getMonth() + 1) + "-";

	} else {			
		buf += (today.getMonth() + 1) + "-";
	}

	if(today.getDate()<10) {			
		buf += "0" + today.getDate() + " ";
	} else {			
		buf += today.getDate() + " ";
	}

	if(today.getHours()>=12) {
		if ((today.getHours()-12) < 10)
			buf += "오후 0" + (today.getHours()-12) + ":";
		else
			buf += "오후 " + (today.getHours()-12) + ":";	
	} else {
		if (today.getHours() < 10)
			buf += "오전 0" + today.getHours() + ":";
		else
			buf += "오전 " + today.getHours() + ":";
	}
	if (today.getMinutes() < 10) {
		buf += "0" + today.getMinutes();
	} else 
		buf += today.getMinutes();
	if (inputMessage.val()=="") {
		alert("이름과 메세지를 확인해주세요.")
	} else {
		$('#chatList').append(
				'<div class="row">'
				+ '<div class="col-lg-12">'
				+ '<div class="media">'
				+ '<a class="pull-left" href="#">'
				+ '<img class="media-object img-circle" src="resources/image/icon.png" alt="">'
				+ '</a>' + '<div class="media-body">'
				+ '<h4 class="media-heading">' + "나" 
				+ '<span class="small pull-right">' + buf
				+ '</span>' + '</h4>' + '<pre>' + inputMessage.val()
				+ '</pre>' + '</div>' + '</div>' + '</div>'
				+ '</div>' + '<hr>');
		$('#chatList').scrollTop($('#chatList')[0].scrollHeight);	
		sendData = {
				'name' : inputName.val(),
				'message' : inputMessage.val(),
				'time' : buf
		};
		var sendText = JSON.stringify(sendData);
		webSocket.send(sendText);
		$("#inputMessage").val("");
	}
}

//getItem

function putCart(stockQuantity, id) {
	var stockQuantity = parseInt(stockQuantity);
	var salesQuantity = parseInt($("#salesQty2").val());
	var id = id;
	if (stockQuantity < salesQuantity) {
		alert("재고 수량을 넘기셨습니다. 다시 설정해주세요.");
	} else if (salesQuantity <= 0) {
		alert("수량을 다시 설정해주세요.");
		$("#salesQty2").val(1);
	} else {
		$.ajax({
			type : 'POST',
			url : 'putCart.do',
			data : {"salesQuantity" : salesQuantity, "id" : id},
			success : function(data) {
				alert("카트에 " + salesQuantity +"개 넣어두었습니다. 언제든 구매 가능 합니다.");
			},
			error : function(data) {
				alert("카트에 넣기에 실패 하였습니다. 나중에 다시 시도해주세요.");
			}
		});
	}
}

//상품 사기전 점검
function formCheck(stockQuantity, price) {
	var stockQuantity = parseInt(stockQuantity);
	var salesQuantity = parseInt($("#salesQty2").val());
	var price = parseInt(price);
	var totalPrice = salesQuantity * price;
	if (stockQuantity >= salesQuantity && salesQuantity > 0) {
		var result = confirm(totalPrice + "원 구매하시겠습니까?")
		if(result){
			alert("구매가 완료되었습니다.")
			$("#buyForm").submit();
		}else{
			alert("구매가 취소되었습니다.")
		}
	} else {
		alert("구매 수량이 재고 수량을 넘기셨습니다. 다시 설정해주세요.")
		$("#salesQty2").val(1);
	}
}

//수량 변경
function updateCount(count) {
	var salesQuantity = parseInt($("#salesQty2").val());
	var count = parseInt(count);
	if (salesQuantity + count > 0 ) {
		$("#salesQty2").val(salesQuantity + count);
	} else {
		alert("최소 수량입니다.");
		$("#salesQty2").val(1);
	}
}

//join
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

//login

function login(){
	var loginId = $("#loginId").val();
	var loginPassword = $("#loginPassword").val();
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

//terms
function chk(){
	var req = document.form.req.checked;
	var num = 0;
	if(req == true){
		num = 1;
	}
	if(num==1){
		document.form.submit();
	}else{
		alert("개인정보 약관에 동의하셔야 합니다.");
	}
}

//updateMyInfo
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

//adminItem.jsp
//관리자모드 상품전체목록 가져오기
function getAllItemList() {
	$("#itemSearchCondition").val(null);
	$("#itemSearchKeyword").val(null);
	$("#itemListForm").submit();
}

function itemSelected(selectValue) {
	var selectValue = selectValue;
	if (selectValue == "id") {
		$("#itemSearchKeyword").val("");
		$("#itemSearchKeyword").attr('onkeydown','return isNumberKey(event)');
		$("#itemSearchKeyword").attr('onkeyup','removeChar(event)');
	} else {
		$("#itemSearchKeyword").val("");
		$("#itemSearchKeyword").attr('onkeydown','return true');
		$("#itemSearchKeyword").attr('onkeyup','');
	}
}

//adminUser.jsp
//관리자모드 유저전체목록 가져오기
function getAllUserList() {
	$("#userSearchCondition").val(null);
	$("#userSearchKeyword").val(null);
	$("#userListForm").submit();
}

function userSelected(selectValue) {
	var selectValue = selectValue;
	if (selectValue == "id") {
		$("#userSearchKeyword").val("");
		$("#userSearchKeyword").attr('onkeydown','return isNumberKey(event)');
		$("#userSearchKeyword").attr('onkeyup','removeChar(event)');
	} else {
		$("#userSearchKeyword").val("");
		$("#userSearchKeyword").attr('onkeydown','return true');
		$("#userSearchKeyword").attr('onkeyup','');
	}
}

//adminContract.jsp
//관리자모드 거래상품전체목록 가져오기
function getAllBuyItemList() {
	console.log("-------");
	$("#buyItemSearchCondition").val(null);
	$("#buyItemSearchKeyword").val(null);
	$("#buyItemListForm").submit();
}

function buyItemSelected(selectValue) {
	var selectValue = selectValue;
	if (selectValue == "buyTime") {
		$("#buyItemBuyTime").val("");
		$("#buyItemSearchKeyword").hide();
		$("#buyItemBuyTime").show();
		$(".state").hide();
	} else if (selectValue == "state") {
		$("#buyItemSearchKeyword").hide();
		$("#buyItemBuyTime").hide();
		$(".state").show();
	} else if (selectValue == "id") {
		$("#buyItemSearchKeyword").val("");
		$("#buyItemSearchKeyword").attr('onkeydown','return isNumberKey(event)');
		$("#buyItemSearchKeyword").attr('onkeyup','removeChar(event)');
		$("#buyItemSearchKeyword").show();
		$("#buyItemBuyTime").hide();
		$(".state").hide();
	} else {
		$("#buyItemSearchKeyword").val("");
		$("#buyItemSearchKeyword").attr('onkeydown','return true');
		$("#buyItemSearchKeyword").attr('onkeyup','');
		$("#buyItemSearchKeyword").show();
		$("#buyItemBuyTime").hide();
		$(".state").hide();
	}
}

//getBoardList.jsp
//다음 게시물 블록 보기
function next() {
	$("#boardNext").val("true");
	$("#boardListForm").submit();
}