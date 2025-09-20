<%@page import="com.hk.board.daos.board_daos"%>
<%@page import="com.hk.board.dtos.board_dtos"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%request.setCharacterEncoding("utf-8"); %>
<%response.setContentType("text/html;charset=UTF-8"); %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시판 글 수정 폼</title>
</head>
<%
	// controller.jsp에서 구한 DTO객체를 requestScope에서 가져옵니다.
	board_dtos dto=(board_dtos)request.getAttribute("dto");
%>
<body>
<h1>게시판 수정하기</h1>
<form action="boardController.jsp" method="post">
<input type="hidden" name="command" value="boardupdate">
<input type="hidden" name="seq" value="<%=dto.getTseq()%>"/>
<table border="1">
	<tr>
		<th>작성자(ID)</th>
		<td><%=dto.getTid()%></td>
	</tr>
	<tr>
		<th>글제목</th>
		<td><input type="text" name="title" 
							   value="<%=dto.getTtitle()%>"/></td>
	</tr>
	<tr>
		<th>글내용</th>
		<td>
			<textarea rows="10" cols="60" name="content" ><%=dto.getTcontent()%></textarea>
		</td>
	</tr>
	<tr>
		<td colspan="2">
			<input type="submit" value="수정완료" />
			<input type="button" value="글목록" 
			  onclick="location.href='boardController.jsp?command=boardlist'"/>
		</td>
	</tr>
</table>
</form>
</body>
</html>