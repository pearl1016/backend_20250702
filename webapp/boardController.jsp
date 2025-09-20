<%@page import="com.hk.board.daos.board_daos"%>
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
<title>Insert title here</title>
</head>
<body>
<%
	//1단계:command값 받기 -> 어떤 요청인지 확인 값 받기
	String command=request.getParameter("command");
	
	//2단계: DAO객체 생성
	board_daos dao=new board_daos();
	
	//3단계:요청 분기
	if(command.equals("boardlist")){  // 글목록 요청 처리
		//4단계:파라미터 받기 (글목록에서는 받을 파라미터가 X)
		
		//5단계:dao 메서드 실행
		List<board_dtos> list=dao.getAllList();//DB로부터 글목록 데이터 가져오기
		
		//6단계:Scope 객체에 담기
		request.setAttribute("list", list);//["list":list]
		
		//7단계: 페이지 응답(이동)
		pageContext.forward("boardlist.jsp");
	}else if(command.equals("insertboardform")){//글쓰기폼요청
		response.sendRedirect("insertboardform.jsp");
	}else if(command.equals("insertboard")){//글추가하기
		//id, title, content
		String id=request.getParameter("id");
		String title=request.getParameter("title");
		String content=request.getParameter("content");
		
		boolean isS=dao.insertBoard(new board_dtos(id,title,content));
		if(isS){
			response.sendRedirect("boardController.jsp?command=boardlist");
		}else{
			response.sendRedirect("error.jsp");
		}
	}else if(command.equals("boarddetail")){//상세보기
		//전달된 파라미터 받기
		String sseq=request.getParameter("seq");
		int seq=Integer.parseInt(sseq);//"5"->정수 5 변환
		
		board_dtos dto=dao.getBoard(seq);//db에서 글하나에 대한 정보가져오기
		//dto객체를 boarddeatil.jsp로 전달해야 함
		request.setAttribute("dto", dto);
		pageContext.forward("boarddetail.jsp");
	}else if(command.equals("boardupdateform")){
		//수정폼 이동
		//전달된 파라미터 받기
		String sseq=request.getParameter("seq");
		int seq=Integer.parseInt(sseq);//"5"->정수 5 변환
		
		board_dtos dto=dao.getBoard(seq);//db에서 글하나에 대한 정보가져오기
		//dto객체를 boardupdateform.jsp로 전달해야 함
		request.setAttribute("dto", dto);
		pageContext.forward("boardupdateform.jsp");
	}else if(command.equals("boardupdate")){
		//수정하기
		String sseq=request.getParameter("seq");
		int seq=Integer.parseInt(sseq);
		String title=request.getParameter("title");
		String content=request.getParameter("content");
		
		boolean isS=dao.updateBoard(new board_dtos(seq,title,content));
		if(isS){
			response.sendRedirect("boardController.jsp?"
					             +"command=boarddetail&seq="+seq);
		}else{
			response.sendRedirect("error.jsp");
		}
	}else if(command.equals("boarddelete")){
		//삭제하기
		String sseq=request.getParameter("seq");
		int seq=Integer.parseInt(sseq);
		boolean isS=dao.deleteBoard(seq);
		if(isS){
			response.sendRedirect("boardController.jsp?command=boardlist");
		}else{
			response.sendRedirect("error.jsp");
		}
		
		
	}else if(command.equals("muldel")){
		//여러글 삭제
		String[] seqs=request.getParameterValues("seq");
		boolean isS=dao.mulDel(seqs);
		if(isS){
			response.sendRedirect("boardController.jsp?command=boardlist");
		}else{
			response.sendRedirect("error.jsp");
		}
	}
%>
</body>
</html>