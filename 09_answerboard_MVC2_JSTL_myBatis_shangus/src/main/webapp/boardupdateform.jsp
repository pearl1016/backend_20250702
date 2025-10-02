<jsp:include page="header.jsp" />
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
<body>
<h1>게시판 수정하기</h1>
<form action="boardupdate.board" method="post">
<input type="hidden" name="seq" value="${dto.seq}"/>
<table class="table table-striped" border="1">
	<tr>
		<th>작성자(ID)</th>
		<td>${dto.id}</td>
	</tr>
	<tr>
		<th>글제목</th>
		<td><input class="form-control" type="text" name="title" 
							   value="${dto.title}"/></td>
	</tr>
	<tr>
		<th>글내용</th>
		<td>
			<textarea class="form-control"  rows="10" cols="60" name="content" >${dto.content}</textarea>
		</td>
	</tr>
	<tr>
		<td colspan="2">
			<input class="btn btn-primary" type="submit" value="수정완료" />
			<input class="btn btn-primary" type="button" value="글목록" 
			  onclick="location.href='boardlist.board'"/>
		</td>
	</tr>
</table>
</form>
</body>
</html>
<jsp:include page="footer.jsp" />