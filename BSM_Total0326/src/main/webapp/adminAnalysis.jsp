<%@ page contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>BSM 관리자모드/매출분석</title>
<!-- bootstrap CSS -->
<link href="https://netdna.bootstrapcdn.com/bootstrap/3.0.0/css/bootstrap.min.css" rel="stylesheet">
<link rel="stylesheet" href="./resources/css/adminAnalysis.css">
<!-- jQuery  -->
<script src="http://code.jquery.com/jquery-1.11.0.min.js"></script>	
<script src="//code.jquery.com/jquery.min.js"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<!-- bootstrap JS -->
<script src="https://netdna.bootstrapcdn.com/bootstrap/3.0.0/js/bootstrap.min.js"></script>
<script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>
<script src = "./resources/js/adminAnalysis.js"></script>
<script src = "./resources/js/total.js"></script>
<script type="text/javascript">
var isFirst = true;
google.charts.load('current', {packages:['corechart']});
google.charts.setOnLoadCallback(drawChart);
</script>
</head>
<body>
	<% System.out.println("판매 추이 분석 창 생성"); %>
	<a href="adminModeIndex.jsp"><b>&lt;&lt;뒤로가기</b></a>
	<br><br><b>관리자모드 / 매출분석</b>
	<br>
	<hr align="left" style="width: 80%;">
	<b><font color="#428BCA">Tip!</font>&nbsp;기간과 분류를 선택하고 분석시작을 눌러주세요<br>IE환경에서는 0000-00-00(예 2018-02-01) 같은 형식으로 작성하여 분석하여주세요.</b>
	<br><br>
	<div>
		<table border="1" cellpadding="0">
			<tr>
				<td width="100">
					<center><b>기간설정</b></center>
				</td>
				<td width="320">
					<center><input type="date" id="minDate"> <b>~</b> <input type="date" id="maxDate"></center>
				</td>
				<td rowspan="2">
					<input type="button" value="분석시작" onclick="drawChart()" style="WIDTH: 60pt; HEIGHT: 40pt;">	
				</td>
			</tr>
			<tr>
				<td>
					<center><b>분류설정</b></center>
				</td>
				<td>					
					<select id="categorize">
						<option value="none">분류 선택</option>
						<option value="birth">나이</option>
						<option value="gender">성별</option>
						<option value="type">타입</option>
						<option value="days">일별</option>
						<option value="months">월별</option>
						<option value="years">년별</option>
					</select>					
				</td>
			</tr>								
		</table>			
	</div>
	<br>	
	<hr align="left" style="width: 80%;">
	
	<div id="chart_div" style="width:900px; height:500px;">
	</div>
	<label id="dateLabel"></label>
</body>
</html>