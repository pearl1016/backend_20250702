<%@page import="com.hk.board.dtos.HkDto"%>
<%@page import="java.util.List"%>
<%@page import="com.hk.board.daos.HkDao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
request.setCharacterEncoding("utf-8");
%>
<%
response.setContentType("text/html;charset=UTF-8");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>글목록 조회</title>
<script type="text/javascript">
	function insertBoardForm(){
		location.href="insertboardform.jsp";
	}
</script>
</head>
<%
HkDao dao=new HkDao();
	List<HkDto> list=dao.getAllList();//list[dto,dto,dto...]
%>
<body>
<h1>게시판</h1>
<h2>글목록</h2>
<table border="1">
	<col width="50px"/>
	<col width="100px"/>
	<col width="300px"/>
	<col width="200px"/>
	<tr>
		<th>번호</th><th>작성자</th><th>제목</th><th>작성일</th>
	</tr>
	<%
	for(HkDto dto:list){
	%>
			<tr>
				<td><%=dto.getSeq()%></td>
				<td><%=dto.getId()%></td>
				<td><a href="boarddetail.jsp?seq=<%=dto.getSeq()%>"><%=dto.getTitle()%></a></td>
				<td><%=dto.getRegDate()%></td>
			</tr>
			<%
		}
	%>
	<tr>
		<td colspan="4">
			<button onclick="insertBoardForm()">글추가</button>		
		</td>
	</tr>
</table>
</body>
</html>