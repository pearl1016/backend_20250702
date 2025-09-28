<%@page import="com.hk.board.dtos.HkDto"%>
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
<title>게시판 상세보기</title>
</head>
<%
//전달된 파라미터 받기
	String sseq=request.getParameter("seq");
	int seq=Integer.parseInt(sseq);//"5"->정수 5 변환
	
	HkDao dao=new HkDao();
	HkDto dto=dao.getBoard(seq);
%>
<body>
<h1>게시판 상세보기</h1>
<table border="1">
	<tr>
		<th>작성자(ID)</th>
		<td><%=dto.getId()%></td>
	</tr>
	<tr>
		<th>글제목</th>
		<td><%=dto.getTitle()%></td>
	</tr>
	<tr>
		<th>글내용</th>
		<td>
			<textarea rows="10" cols="60" name="content" 
			  readonly="readonly"><%=dto.getContent()%></textarea>
		</td>
	</tr>
	<tr>
		<td colspan="2">
			<input type="button" value="수정폼이동"
								 onclick="updateForm()"/>
								 
			<input type="button" value="삭제"
								 onclick="delBoard()"/>
								 
			<input type="button" value="글목록" 
						onclick="location.href='boardlist.jsp'"/>
		</td>
	</tr>
</table>
<script type="text/javascript">

	// 필요한 파라미터 pk값
	// boardupdateform.jsp, boardupdate.jsp
	function updateForm(seq){
		// 수정폼 이동-> 수정폼에서는 글의 상세내용 보여주고, 
		//             수정완료버튼클릭하면 수정되게 처리(제목,내용만 수정)
	}
	// boardDelete.jsp
	function delBoard(seq){
		
	}
</script>
</body>
</html>