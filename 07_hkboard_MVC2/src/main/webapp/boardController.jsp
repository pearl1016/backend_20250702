<%@page import="com.hk.board.dtos.HkDto"%>
<%@page import="java.util.List"%>
<%@page import="com.hk.board.daos.HkDao"%>
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
	//    -요청값: 별도의 command라는 값을 전달
	//    -요청url: boardlist.board  :MVC2방식일때
	String command=request.getParameter("command");
	
	//2단계: DAO객체 생성
	HkDao dao=new HkDao();
	
	//3단계:요청 분기
	if(command.equals("boardlist")){  // 글목록 요청 처리
		//4단계:파라미터 받기 (글목록에서는 받을 파라미터가 X)
		
		//5단계:dao 메서드 실행
		List<HkDto> list=dao.getAllList();//DB로부터 글목록 데이터 가져오기
		
		//6단계:Scope 객체에 담기
		//request스코프: 객체 전달범위 
		// 요청페이지 ----> 응답페이지
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
		
		boolean isS=dao.insertBoard(new HkDto(id,title,content));
		if(isS){
			//새로 다시 요청을 해서 응답하기 때문에 주소창이 업데이트 된다.
			response.sendRedirect("boardController.jsp?command=boardlist");
			//글추가할때 요청 주소가 남아있어서 새로고침하면 글이 계속 추가된다.
// 			pageContext.forward("boardController.jsp?command=boardlist");
		}else{
			response.sendRedirect("error.jsp");
		}
	}else if(command.equals("boarddetail")){//상세보기
		//전달된 파라미터 받기
		String sseq=request.getParameter("seq");
		int seq=Integer.parseInt(sseq);//"5"->정수 5 변환
		
		HkDto dto=dao.getBoard(seq);//db에서 글하나에 대한 정보가져오기
		//dto객체를 boarddeatil.jsp로 전달해야 함
		request.setAttribute("dto", dto);
		pageContext.forward("boarddetail.jsp");
	}else if(command.equals("boardupdateform")){
		//수정폼 이동
		//전달된 파라미터 받기
		String sseq=request.getParameter("seq");
		int seq=Integer.parseInt(sseq);//"5"->정수 5 변환
		
		HkDto dto=dao.getBoard(seq);//db에서 글하나에 대한 정보가져오기
		//dto객체를 boardupdateform.jsp로 전달해야 함
		request.setAttribute("dto", dto);
		pageContext.forward("boardupdateform.jsp");
	}else if(command.equals("boardupdate")){
		//수정하기
		String sseq=request.getParameter("seq");
		int seq=Integer.parseInt(sseq);
		String title=request.getParameter("title");
		String content=request.getParameter("content");
		
		boolean isS=dao.updateBoard(new HkDto(seq,title,content));
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
		// 전달받는 파라미터: 동일한 name으로 여러개의 값이 전달되는 상황
	    //   url -> boardController.jsp?seq=5,6,7,8
// 		request.getParameter("seq");// 해당 name에 대한 값 1개를 받는다
		String[] seqs=request.getParameterValues("seq");//여러개의 값을 받아 배열로 반환
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