<%@page import="com.hk.board.dtos.board_dtos"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%request.setCharacterEncoding("utf-8"); %>
<%response.setContentType("text/html;charset=UTF-8"); %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>글목록 조회</title>
<script type="text/javascript">
	function insertBoardForm(){
		location.href=
	        "boardController.jsp?command=insertboardform";
	}
	
	//전체선택 체크박스 기능
	function allSel(bool){// bool:전체 선택 박스의 체크 여부(true/false)
		const seqs=document.getElementsByName("seq");//[seq,seq..]
		for (let i = 0; i < seqs.length; i++) {
			seqs[i].checked=bool;//체크여부 적용
		}
	}
	
	//삭제 체크박스 유효값 처리 기능
	function isAllCheck() {
		let chks=document.querySelectorAll("input[name=seq]:checked");
		console.log(chks.length);
		if(chks.length==0){
			document.querySelector("#msg").textContent="하나 이상 체크하세요";
			return false;
		} else {
			if(confirm("정말 삭제하겠습니까?")) {
				return true; 
			} else {
				document.querySelector("#msg").textContent="";
				return false;
			}	
	
		}//if종료
	}
	
</script>
</head>
<%
	//request 스코프에 객체를 저장하면 모두 Object로 강제 형변환됨
// 	Object obj=request.getAttribute("list");
// 	List<HkDto> list=(List<HkDto>)obj;
	List<board_dtos> list=
			(List<board_dtos>)request.getAttribute("list");
%>
<body>
<h1>게시판</h1>
<h2>글목록</h2>
<form action="boardController.jsp" method="post" onsubmit="return isAllCheck()">
<input type="hidden" name="command" value="muldel"/>
<table border="1">
	<col width="50px"/>
	<col width="50px"/>
	<col width="100px"/>
	<col width="300px"/>
	<col width="200px"/>
	<tr><th>
			<input type="checkbox" name="all" 
			       onclick="allSel(this.checked)">
		</th>
		<th>번호</th><th>작성자</th><th>제목</th><th>작성일</th>
	</tr>
	<%
		for(board_dtos dto:list){
			%>
			<tr>
				<td><input type="checkbox" name="seq" value="<%=dto.getTseq()%>" /></td>
				<td><%=dto.getTseq()%></td>
				<td><%=dto.getTid()%></td>
				<td><a href="boardController.jsp?command=boarddetail&seq=<%=dto.getTseq()%>"><%=dto.getTtitle()%></a></td>
				<td><%=dto.getTregdate()%></td>
			</tr>
			<%
		}
	%>
	<tr>
		<td colspan="5">
			<button type="button" onclick="insertBoardForm()">글추가</button>	
			<button type="submit">글삭제</button>
		</td>
	</tr>
</table>
</form>
</body>
</html>