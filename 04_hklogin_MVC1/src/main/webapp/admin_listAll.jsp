<%@page import="com.hk.board.dtos.UserDto"%>
<%@page import="java.util.List"%>
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
</head>
<%
	List<UserDto> list=(List<UserDto>)request.getAttribute("list");
%>
<body>
<div id="container">
	<div class="main">
		<div>
			<span>
			<%=ldto.getId()%>[<%=ldto.getRole()%>]
			님이 로그인 하였습니다.
			</span>
			<span>
				<a href="adminController.jsp?command=userlistall">회원전체조회</a>
			</span>|
			<span>
				<a href="adminController.jsp?command=userlist">회원정보[등급]수정</a>
			</span>|
			<span>
				<a href="userController.jsp?command=logout">로그아웃</a>
			</span>
		</div>
	</div>
	<div class="admin_content">
		<h1>관리자 페이지</h1>
		<div class="userlist">
			<table border="1" class="table  table-striped table-hover">
				<tr class="table-primary">                  
					<th>회원번호</th>
					<th>아이디</th>
					<th>이름</th>
					<th>주소</th>
					<th>이메일</th>
					<th>회원등급</th>
					<th>탈퇴여부</th>
					<th>가입일</th>
				</tr>
				<%
					for(UserDto dto:list){
						%>
						<tr>
							<td><%=dto.getSeq()%></td>
							<td><%=dto.getId()%></td>
							<td><%=dto.getName()%></td>
							<td><%=dto.getAddress()%></td>
							<td><%=dto.getEmail()%></td>
							<td><%=dto.getRole()%></td>
							<td><%=dto.getEnabled().equals("Y")?"가입중":"탈퇴"%></td>
							<td><%=dto.getRegDate()%></td>
						</tr>						
						<%
					}
				%>
			</table>
		</div>
	</div>
</div>
</body>
</html>
<%@ include file="footer.jsp" %>