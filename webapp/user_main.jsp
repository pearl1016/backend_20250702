<%@page import="com.hk.board.dtos.user_dtos"%>
<%@ include file="header.jsp" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%request.setCharacterEncoding("utf-8"); %>
<%response.setContentType("text/html;charset=UTF-8"); %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">
	.main{
		padding:10px;
		font-size: 15pt;
	}
	.main a{
		text-decoration: none;
	}    
</style>
</head>
<body>
<%
    // 이 코드를 추가하여 ldto 변수를 세션에서 가져옵니다.
    user_dtos ldto = (user_dtos)session.getAttribute("ldto");
%>
<div id="container">
	<div class="main">
		<div>
			<span>
			<%=ldto.getTid()%>[<%=ldto.getTrole()%>]
			님이 로그인 하였습니다.
			</span>
			<span>
				<a href="userController.jsp?command=userinfo&id=<%=ldto.getTid()%>">나의 정보</a>
			</span>
			<span>
				<a href="userController.jsp?command=logout">로그아웃</a>
			</span>
		</div>
	</div>
</div>
</body>
</html>
<%@ include file="footer.jsp" %>