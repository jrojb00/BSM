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