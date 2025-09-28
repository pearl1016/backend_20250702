package com.hk.board.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hk.board.dao.AnsDao;
import com.hk.board.dto.AnsDto;

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
			List<AnsDto> list=dao.getAllList();
			request.setAttribute("list", list);
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
		}else if(command.equals("/boarddetail.board")) {
			String sseq=request.getParameter("seq");
			int seq=Integer.parseInt(sseq);
			AnsDto dto=dao.getBoard(seq);//상세내용 조회
		
			String review=request.getParameter("review");
			//글목록 페이지에서 요청한 경우
			if(review!=null&&review.equals("y")) {
				dao.readCount(seq);
				response.sendRedirect("boarddetail.board?seq="+seq);
			} else {
				request.setAttribute("dto", dto);
				dispatch("boarddetail.jsp", request, response);
			}
			
			//sessionScope를 이용해서 처리하는 방법
			
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