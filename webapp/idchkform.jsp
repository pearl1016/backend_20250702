<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%request.setCharacterEncoding("utf-8"); %>
<%response.setContentType("text/html;charset=UTF-8"); %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<%  // ID 존재하는지 조회한 결과를 받는다. null:사용가능 
	String resultId=(String)request.getAttribute("resultId"); 
%>
<body>
	<div>
		<span><%=resultId==null?
				"사용 가능한 아이디입니다.":"중복된 아이디입니다." %></span>
		<span><button onclick="confirmId('<%=resultId%>')">확인</button></span>
	</div>
<script type="text/javascript">
	function confirmId(resultId){
		if(resultId=='null'){
			//ID체크했다는 상태 저장
			localStorage.setItem("idchk","y");
			//ID체크후 다음 단계로 이동을 유도
			opener.document.getElementsByName("name")[0].focus();
		}else{
			localStorage.setItem("idchk","n");
			opener.document.getElementsByName("id")[0].focus();
		}
		
		self.close();//현재 창을 닫는다.
	}
</script>
</body>
</html>