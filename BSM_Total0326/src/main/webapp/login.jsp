<%@ page contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>

<link rel="stylesheet" href="./resources/css/login.css">
<script src="//code.jquery.com/jquery.min.js"></script>
<script src = "./resources/js/total.js"></script>

</head>
<body>

<form class="form-2">
    <h1><span class="log-in">Log in</span></h1>
    <p class="float">
        <label for="login"><i class="icon-user"></i>UserID</label>
        <input type="text" id="loginId" placeholder="UserID">
    </p>
    <p class="float">
        <label for="password"><i class="icon-lock"></i>Password</label>
        <input type="password" id="loginPassword" placeholder="Password" class="showpassword"> 
    </p>
    <p class="clearfix"> 
        <input type="button" value="Log in" onclick="login()">
    </p>       
</form>

</body>
</html>