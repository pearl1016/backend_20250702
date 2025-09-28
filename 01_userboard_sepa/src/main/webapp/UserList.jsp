<%@page import="com.hk.board.dtos.UserDto"%>
<%@page import="com.hk.board.daos.UserDao"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원 목록 조회</title>
</head>
<%
UserDao dao = new UserDao();
	List<UserDto>list=dao.getAllUser();
%>
<body>
<h1>회원 조회 결과</h1>
<table border="1">
	<tr>
		<th>아이디</th>
		<th>이름</th>
		<th>가입일</th>
		<th>수정</th>
		<th>삭제</th>
	</tr>
	<%
		for(int i=0; i<list.size(); i++) {
			UserDto dto = list.get(i);
		%>
		<tr>
			<td><%=dto.getUserID()%></td>
			<td><%=dto.getName()%></td>
			<td><%=dto.getmDate()%></td>
			<td><a href="UserUpdateForm.jsp?userId=<%=dto.getUserID()%>">수정</a></td>
				
			<td><a href="#" onclick="deleteUser('<%= dto.getUserID()%>')">삭제</a></td>
				
		</tr>
		<%
		}
	%>
	<tr>
		<td colspan ="5">
			<a href="index.jsp">메인화면</a>
		</td>
	
	</tr>
</table>
<script type="text/javascript">
	function deleteUser(userId) {
		if(confirm("정말 삭제하시겠습니까?")) {
			 location.href="UserDelete.jsp?userId="+userId; 
		}
	}
</script>
</body>
</html>