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
// 		alert(event.data);
	}

	function send() {
		var inputMessage = $("#inputMessage");
		var inputName = $("#inputName");
		var today = new Date();
		buf = ""
// 		buf += (today.getYear()+1900) + "-" + (today.getMonth() + 1) + "-" + today.getDate() + " ";
		
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