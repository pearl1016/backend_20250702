<%@ include file="header.jsp" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% request.setCharacterEncoding("utf-8"); %>
<% response.setContentType("text/html;charset=UTF-8"); %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원상세조회</title>
</head>
<body>
<div id="container">
	<div class="main">
		<div>
			<span>
			${sessionScope.ldto.tid}[${sessionScope.ldto.trole}]
			님이 로그인 하였습니다.
			</span>
			<span>
				<a href="userController.jsp?command=userinfo&id=${sessionScope.ldto.tid}">나의 정보</a>
			</span>
			<span>
				<a href="userController.jsp?command=logout">로그아웃</a>
			</span>
		</div>
	</div>
	<div class="content">
		<h1>사용자 페이지</h1>
		<div id="myinfo">
			<form action="userController.jsp" method="post">
				<input type="hidden" name="command" value="userupdate"/>
				<input type="hidden" name="id" value="${requestScope.dto.tid}"/>
				<table border="1" class="table table-striped table-hover">
					<tr>
						<th>아이디</th>
						<td>${requestScope.dto.tid}</td>
					</tr>
					<tr>
						<th>이름</th>
						<td>${requestScope.dto.tname}</td>
					</tr>
					<tr>
						<th>등급</th>
						<td>${requestScope.dto.trole}</td>
					</tr>
					<tr>
						<th>주소</th>
						<td><input class="form-control" type="text" name="address" value="${requestScope.dto.taddress}"/></td>
					</tr>
					<tr>
						<th>이메일</th>
						<td><input class="form-control" type="email" name="email" value="${requestScope.dto.temail}"/></td>
					</tr>
					<tr>
						<td colspan="2">
							<button class="btn btn-primary" type="submit">수정</button>
							<button class="btn btn-primary" type="button" 
							   onclick="delUser('${requestScope.dto.tid}')">탈퇴</button>
						</td>
					</tr>
				</table>
			</form>
		</div>
	</div>
</div>
<script type="text/javascript">
	function delUser(id){
		if(confirm("정말 탈퇴하시겠습니까?")){
			location.href="userController.jsp?command=deluser&id="+id;
		}
	}
</script>
</body>
</html>
<%@ include file="footer.jsp" %>