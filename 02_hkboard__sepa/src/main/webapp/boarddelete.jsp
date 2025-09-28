<%@page import="com.hk.board.dtos.HkDto"%>
<%@page import="com.hk.board.daos.HkDao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%request.setCharacterEncoding("UTF-8"); %>
<%response.setContentType("text/html;charset=UTF-8"); %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<%
	// 전송된 파라미터를 받기
	String sseq = request.getParameter("seq");
	int seq = Integer.parseInt(sseq);
	String title = request.getParameter("title");
	String content = request.getParameter("content");
	
	HkDao dao = new HkDao();
	boolean isS = dao.deleteBoard(seq);
	
	if(isS) {
		%>
			<script type="text/javascript">
				alert("글을 삭제했습니다.");
				location.href = "boardlist.jsp";
			</script>
		<%
	}else {
		%>
		<script type="text/javascript">
			alert("글 삭제 실패.");
			location.href = "error.jsp";
		</script>
	<%
	}
%>
<body>

</body>
</html>