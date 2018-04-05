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