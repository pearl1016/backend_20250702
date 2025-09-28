<%@page import="com.hk.board.daos.UserDao"%>
<%@page import="com.hk.board.dtos.UserDto"%>

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
<title>회원정보수정폼</title>
</head>
<%
String userId=request.getParameter("userId");
	UserDao dao=new UserDao();
	UserDto dto=dao.getUser(userId);
	// 쿼리 실행: select * from usertbl where userId=userId;
%>
<body>
<h1>회원정보수정폼</h1>
<form action ="UserUpdateForm.jsp" method="post">
<input type="text" name="userId" value="<%=dto.getUserID() %>"/>
	<table border="1">
		<tr>
			<th>아이디</th>
			<td><%=dto.getUserID()%></td>
		</tr>
		<tr>
			<th>이름</th>
			<td><input type="text" name="name" value="<%=dto.getName()%>"/></td>
		</tr>	
		<tr>
			<th>출생년도</th>
			<td><input type="text" name="birthYear" value="<%=dto.getBirthYear()%>"/></td>
		</tr>
		<tr>
			<th>지역</th>
			<td><input type="text" name="addr" value="<%=dto.getAddr()%>"/></td>
		</tr>
		<tr>
			<th>휴대폰국번</th>
			<td><input type="text" name="mobile1" value="<%=dto.getMobile1()%>"/></td>
		</tr>
		<tr>
			<th>휴대폰번호</th>
			<td><input type="text" name="mobile2" value="<%=dto.getMobile2()%>"/></td>
		</tr>
		<tr>
			
			<td colspan="2">
				<input type="submit" value="회원수정"/>
			</td>
		</tr>
	</table>

</form>
</body>
</html>