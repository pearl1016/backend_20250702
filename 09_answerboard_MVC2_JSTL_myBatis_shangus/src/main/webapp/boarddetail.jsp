<jsp:include page="header.jsp"/>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%request.setCharacterEncoding("utf-8"); %>
<%response.setContentType("text/html;charset=UTF-8"); %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시판 상세보기</title>
<style type="text/css">
	#replyForm{display: none;}
</style>
</head>
<body>
<h1>게시판 상세보기</h1>
<table class="table table-striped" border="1">
	<tr>
		<th>작성자(ID)</th>
		<td>${dto.id}</td>
	</tr>
	<tr>
		<th>글제목</th>
		<td>${dto.title}</td>
	</tr>
	<tr>
		<th>글내용</th>
		<td>
			<textarea class="form-control" rows="10" cols="60" name="content" 
			  readonly="readonly">${dto.content}</textarea>
		</td>
	</tr>
	<tr>
		<td colspan="2">
			<input class="btn btn-primary" type="button" value="답글"
								 onclick="replyForm()"/>
			<input class="btn btn-primary" type="button" value="수정폼이동"
								 onclick="updateForm('${dto.seq}')"/>
								 
			<input class="btn btn-primary" type="button" value="삭제"
								 onclick="delBoard('${dto.seq}')"/>
								 
			<input class="btn btn-primary" type="button" value="글목록" 
						onclick="location.href='boardlist.board'"/>
		</td>
	</tr>
</table>
<div id="replyForm">
	<h1>답글 작성하기</h1>
	<form action="replyboard.board" method="post">
		<input type="hidden" name="seq" value="${dto.seq}"/>
		<table class="table table-striped" border="1">
			<tr>
				<th>작성자(ID)</th>
				<td><input class="form-control" 
				           type="text" name="id" 
				           pattern="^[a-zA-Z0-9]+$"
				           required="required"/></td>
			</tr>
			<tr>
				<th>글제목</th>
				<td><input class="form-control" type="text" name="title" required="required"/></td>
			</tr>
			<tr>
				<th>글내용</th>
				<td>
					<textarea class="form-control" rows="5" cols="60" name="content" 
					                          required="required"></textarea>
				</td>
			</tr>
			<tr>
				<td colspan="2">
					<input class="btn btn-primary" type="submit" value="답글등록"/>
					<input class="btn btn-primary" type="button" value="글목록" 
				        onclick="location.href='boardlist.board'"/>
				</td>
			</tr>
		</table>
	</form>
</div>
<script type="text/javascript">

	// 필요한 파라미터 pk값
	// boardupdateform.jsp, boardupdate.jsp
	function updateForm(seq){
		// 수정폼 이동-> 수정폼에서는 글의 상세내용 보여주고, 
		//             수정완료버튼클릭하면 수정되게 처리(제목,내용만 수정)
		location.href="boardupdateform.board?seq="+seq;
	}
	// boardDelete.jsp
	function delBoard(seq){
		location.href="boarddelete.board?seq="+seq;
	}
	//답글 폼 보여주기
	function replyForm(){
		document.querySelector("#replyForm")
				.style.display="block";
	}
</script>
</body>
</html>
<%-- <jsp:include page="footer.jsp"/> --%>