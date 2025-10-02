package com.hk.board.controller;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.hk.board.dao.AnsDao;
import com.hk.board.dto.AnsDto;
import com.hk.board.util.Paging;

//servlet으로 만들기: HttpServlet을 상속받아야 함
@WebServlet("*.board")
public class AnsController extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//doGet()에서만 코드 작성하면 됨
		
		//command 값 구하기
		//예) 글목록 요청: http://localhost:8080/09_ans~~/boardlist.board
		//    글추가 요청:  ~~~~~~                       /insertboard.board
		String command=request.getRequestURI()  //09_ans~~/boardlist.board
							  .substring(request.getContextPath().length());
		
		System.out.println("요청Command:"+command);
		
		AnsDao dao=new AnsDao();
		
		if(command.equals("/boardlist.board")) {
			//페이지번호 받기
			String pnum=request.getParameter("pnum");
			
			HttpSession session=request.getSession();
			if(pnum==null) {//페이지번호가 없이 요청들어왔을 경우
				pnum=(String)session.getAttribute("pnum");
			}else {//새로운 페이지 요청이 들어왔을 경우
				session.setAttribute("pnum", pnum);
			}
			
			List<AnsDto> list=dao.getAllList(pnum);
			request.setAttribute("list", list);
			
			//페이지 개수 구해서 boardlist.jsp로 보내기 위해 스코프에 담기
			int pcount=dao.getPCount();
			request.setAttribute("pcount", pcount);
			
			//페이지에 페이징 처리 기능 추가
			//필요한 값: pcount(총페이지개수), pnum(요청 페이지번호), 페이지 범위(페이지 수)
			Map<String, Integer>map=Paging.pagingValue(pcount, pnum, 5);
			request.setAttribute("pMap", map);
			
			dispatch("boardlist.jsp", request, response);
		}else if(command.equals("/insertboardform.board")) {
			response.sendRedirect("insertboardform.jsp");
		}else if(command.equals("/insertboard.board")) {
			String id=request.getParameter("id");
			String title=request.getParameter("title");
			String content=request.getParameter("content");
			
			boolean isS=dao.insertBoard(new AnsDto(0,id,title,content));
			if(isS) {
				response.sendRedirect("boardlist.board");
			}else {
				response.sendRedirect("error.jsp");
			}
		}else if(command.equals("/boarddetail.board")){	
			String sseq=request.getParameter("seq");
			int seq=Integer.parseInt(sseq);
			
			AnsDto dto=dao.getBoard(seq);//상세내용 조회
			
			String review=request.getParameter("review");
			//글목록 페이지에서 요청한 경우
			if(review!=null&&review.equals("y")) {
				dao.readCount(seq);//조회수 증가(글목록에서 요청했다면)
				response.sendRedirect("boarddetail.board?seq="+seq);
			}else {
				request.setAttribute("dto", dto);//dto(상세내용)
				dispatch("boarddetail.jsp", request, response);
			}
			
			//sessionScope를 이용해서 처리하는 방법
			
			//cookie를 이용해서 처리하는 방법
		}else if(command.equals("/boardupdateform.board")) {
			String sseq=request.getParameter("seq");
			int seq=Integer.parseInt(sseq);
			AnsDto dto=dao.getBoard(seq);
			
			request.setAttribute("dto", dto);
			dispatch("boardupdateform.jsp", request, response);
		}else if(command.equals("/boardupdate.board")) {//수정완료 요청
			String sseq=request.getParameter("seq");
			int seq=Integer.parseInt(sseq);
			String title=request.getParameter("title");
			String content=request.getParameter("content");
			
			boolean isS=dao.boardUpdate(title, content, seq);
			
			if(isS) {
				response.sendRedirect("boarddetail.board?seq="+seq);
			}else {
				response.sendRedirect("error.jsp");
			}
		}else if(command.equals("/boarddelete.board")) {
			String sseq=request.getParameter("seq");
			int seq=Integer.parseInt(sseq);
			
			boolean isS=dao.deleteBoard(seq);
			
			if(isS) {
				response.sendRedirect("boardlist.board");
			}else {
				response.sendRedirect("error.jsp");
			}
		}else if(command.equals("/muldel.board")) {
			String[]seqs=request.getParameterValues("seq");
			
			boolean isS=dao.mulDel(seqs);
			
			if(isS) {
				response.sendRedirect("boardlist.board");
			}else {
				response.sendRedirect("error.jsp");
			}
		}else if(command.equals("/replyboard.board")) {
			String sseq=request.getParameter("seq");
			int seq=Integer.parseInt(sseq);
			String id=request.getParameter("id");
			String title=request.getParameter("title");
			String content=request.getParameter("content");
			
			boolean isS=dao.replyBoard(new AnsDto(seq,id,title,content));
			if(isS) {
				response.sendRedirect("boardlist.board");
			}else {
				response.sendRedirect("error.jsp");
			}
		}
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
	
	//forward 기능 구현
	public void dispatch(String url, HttpServletRequest request,
									 HttpServletResponse response) 
									throws ServletException, IOException {
		request.getRequestDispatcher(url)
		       .forward(request, response);
	}
}