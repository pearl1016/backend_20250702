package com.hk.board.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.hk.board.daos.HkDao;
import com.hk.board.dtos.HkDto;

@WebServlet("*.board")
public class BoardController extends HttpServlet {
	
	private static final long serialVersionUID = 1L;

	//클라이언트에서 get방식으로 요청을 하면 실행하는 메서드
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//session객체를 얻어오기
//		HttpSession session=request.getSession();
//					session.setAttribute("ldto", dto);
		
		//		request.setCharacterEncoding("utf-8");
//		response.setContentType("text/html;charset=utf-8");
		//--> 인코딩처리: filter에서 구현함
		
		//command 구현(요청 구분 값을 받기)
		// --> command=boardlist 파라미터값을 
		//     추가적으로 전달해야 되는 불필요한 작업
		// getRequestURI() : 주소의 ?전까지 구해줌
		// 컨트롤러 요청할때 boardlist.board 요청
//		request.getRequestURI();// 요청 주소를 얻어옴
		String requestURI=request.getRequestURI();
		StringBuffer requestURL=request.getRequestURL();
		String contextPath=request.getContextPath();
		String pathInfo=request.getPathInfo();
		System.out.println(requestURI+"\n"
						 + requestURL+"\n"
						 + contextPath+"\n"
						 + pathInfo);
		
		//command 값 구하기 --> "/boardlist.board" 추출
		String command=requestURI.substring(contextPath.length());
		System.out.println("요청Command:"+command);
		
		HkDao dao=new HkDao();
		
		if(command.equals("/boardlist.board")) { // 글목록
			List<HkDto> list=dao.getAllList();
			request.setAttribute("list", list);
			
//			pageContext.forward("boardlist.jsp");
//			request.getRequestDispatcher("boardlist.jsp")
//			       .forward(request, response);
			dispatch("boardlist.jsp", request, response);
		}else if(command.equals("/insertboardform.board")) {//글추가폼
			response.sendRedirect("insertboardform.jsp");
		}else if(command.equals("/insertboard.board")) {//글추가하기
			//id, title, content
			String id=request.getParameter("id");
			String title=request.getParameter("title");
			String content=request.getParameter("content");
			
			boolean isS=dao.insertBoard(new HkDto(id,title,content));
			if(isS){
				//새로 다시 요청을 해서 응답하기 때문에 주소창이 업데이트 된다.
				response.sendRedirect("boardlist.board");
			}else{
				response.sendRedirect("error.jsp");
			}
		}else if(command.equals("/muldel.board")) {//여러글 삭제
			String[] seqs=request.getParameterValues("seq");//여러개의 값을 받아 배열로 반환
			boolean isS=dao.mulDel(seqs);
			if(isS){
				response.sendRedirect("boardlist.board");
			}else{
				response.sendRedirect("error.jsp");
			}
		}else if(command.equals("/boarddetail.board")) {//상세보기
			//전달된 파라미터 받기
			String sseq=request.getParameter("seq");
			int seq=Integer.parseInt(sseq);//"5"->정수 5 변환
			
			HkDto dto=dao.getBoard(seq);//db에서 글하나에 대한 정보가져오기
			//dto객체를 boarddeatil.jsp로 전달해야 함
			request.setAttribute("dto", dto);
//			pageContext.forward("boarddetail.jsp");//JSP문법 
			dispatch("boarddetail.jsp", request, response);
		}else if(command.equals("/boardupdateform.board")) {//수정폼이동
			//수정폼 이동          
			//전달된 파라미터 받기
			String sseq=request.getParameter("seq");
			int seq=Integer.parseInt(sseq);//"5"->정수 5 변환
			
			HkDto dto=dao.getBoard(seq);//db에서 글하나에 대한 정보가져오기
			//dto객체를 boardupdateform.jsp로 전달해야 함
			request.setAttribute("dto", dto);
//			pageContext.forward("boardupdateform.jsp");
			dispatch("boardupdateform.jsp", request, response);
		}else if(command.equals("/boardupdate.board")) {
			//수정하기
			String sseq=request.getParameter("seq");
			int seq=Integer.parseInt(sseq);
			String title=request.getParameter("title");
			String content=request.getParameter("content");
			
			boolean isS=dao.updateBoard(new HkDto(seq,title,content));
			if(isS){
//				response.sendRedirect("boarddetail.board?seq="+seq);
//				PrintWriter out=response.getWriter();
//				String js=
//				 "<script type='text/javascript'>"
//				+"	alert('수정완료');"
//				+"	location.href='boarddetail.board?seq="+seq+"';"
//				+"</script>" ;
//				
//				out.print(js);//브라우저로 출력한다.
				jsResponse("boarddetail.board?seq="+seq
						  ,"수정완료", response);
			}else{
				response.sendRedirect("error.jsp");
			}
		}else if (command.equals("/boarddelete.board"));
			//삭제하기
			String sseq=request.getParameter("seq");
			int seq=Integer.parseInt(sseq);
			boolean isS=dao.deleteBoard(seq);
			if(isS) {
				response.sendRedirect("boardlist.board");
			} else {
				response.sendRedirect("error.jsp");
			}
	}
	

	//클라이언트에서 post방식으로 요청을 하면 실행하는 메서드
	// tomcat이 요청을 받아서 doPost에 파라미터로 request,response객체를 전달해주는 개념
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);//클라이언트에서 요청X -> 서버 내부에서 호출
//		test(request);
	}
	
	public void test(HttpServletRequest request) {
		System.out.println(request.getRequestURI());
	}
	
	//forward 기능 구현
	public void dispatch(String url,
						 HttpServletRequest request, 
			             HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher(url)
	       .forward(request, response);
	}
	
	public void jsResponse(String url, String msg,
						   HttpServletResponse response) 
						   throws IOException {
		PrintWriter out=response.getWriter();
		String js=
		 "<script type='text/javascript'>"
		+"	alert('"+msg+"');"
		+"	location.href='"+url+"';"
		+"</script>" ;
		
		out.print(js);//브라우저로 출력한다.
	}

}