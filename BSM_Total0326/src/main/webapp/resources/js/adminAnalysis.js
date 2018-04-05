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
	    //막대 그래프 표일대 data
	    //[['동물다리', '다리요', {role:'annotation'}, {role:'style'}],
	    //['달팽이', 1, '달팽이 1(?)', 'hotpink'],
	    //['메뚜기', 6, '메뚜기 6', '#8000FF'],
	    //['드래곤', 4, 'Dragon 4', 'red'],
	    //['지렁이', 0, '없음', '#8000FF'],
	    //['문어', 8, '문어 8', 'rgb(150,150,150)']]
	    //그래프 표일때 data
	    //[['구매일자', '1975', '1985', '1991', '2000'], 
	    //['2018-02-04', 0, 1, 0, 6], 
	    //['2018-02-05', 3, 0, 0, 0],
	    //['2018-02-09', 0, 0, 9, 0],
	    //['2018-02-12', 3, 2, 2, 6]]