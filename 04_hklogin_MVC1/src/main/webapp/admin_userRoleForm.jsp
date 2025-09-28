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
<body>
<div id="container">
	<div class="main">
		<div>
			<span>
			${sessionScope.ldto.id}[${sessionScope.ldto.role}]
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
		<h1>관리자페이지</h1>

		<div class="userRole">
			<form action="adminController.jsp" method="post" >
				<input type="hidden" name="command" value="updaterole"/>
				<input type="hidden" name="id" value="${dto.id}"/>
				<table border="1" class="table table-striped table-hover">
					<tr>
						<th>아이디</th>
						<td>${requestScope.dto.id}</td>
					</tr>
					<tr>
						<th>이름</th>
						<td>${requestScope.dto.name}</td>
					</tr>
					<tr>
						<th>등급</th>
						<td>
							<select name="role" class="form-select">
								<option value="ADMIN" ${dto.role eq 'ADMIN'?'selected':''}>관리자</option>
								<option value="MANAGER" ${dto.role eq 'MANAGER'?'selected':''}>정회원</option>
								<option value="USER" ${dto.role eq 'USER'?'selected':''}>일반회원</option>
							</select>
						</td>
					</tr>
					<tr>
						<td colspan="2">
							<button class="btn btn-primary" type="submit">수정</button>
						</td>
					</tr>
				</table>
			</form>
		</div>
	</div>
</div>
</body>
</html>
<%@ include file="footer.jsp" %>