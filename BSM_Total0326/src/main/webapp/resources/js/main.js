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