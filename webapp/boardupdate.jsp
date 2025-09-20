<%@page import="com.hk.board.daos.board_daos"%>
<%@page import="com.hk.board.dtos.board_dtos"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% request.setCharacterEncoding("UTF-8"); %>
<% response.setContentType("text/html;charset=UTF-8"); %>

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
	
	// DAO 객체를 올바르게 생성합니다.
	board_daos dao = new board_daos();
	
	// updateBoard 메서드는 DTO 객체를 매개변수로 받습니다.
	boolean isS = dao.updateBoard(new board_dtos(seq, title, content));
	
	if(isS) {
%>
		<script type="text/javascript">
			alert("글을 수정했습니다.");
			location.href = "boarddetail.jsp?seq=<%=seq%>";
		</script>
<%
	} else {
%>
		<script type="text/javascript">
			alert("글 수정 실패.");
			location.href = "error.jsp";
		</script>
<%
	}
%>
<body>

</body>
</html>