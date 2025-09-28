-<%@page import="com.hk.board.dtos.UserDto"%>
<%@page import="com.hk.board.daos.UserDao"%>
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
<title>회원등록폼</title>
</head>
<body>
<%
//userInsertForm.jsp에서 전달된 파라미터를 받기 작업 구현
	// 회원정보를 입력한 뒤 등록을 요청함 --> request 객체 관리(요청정보)
	// JSP에서는 request객체를 생성하는 작업을 직접 해줄 필요가 없다
	// getParameter("key")전달된 파라미터 받는 메서드
	String userId=request.getParameter("userId");
	String name=request.getParameter("name");
	String birthYear=request.getParameter("birthYear");
	int birthYearInt=Integer.parseInt(birthYear);
	String addr=request.getParameter("addr");
	String mobile1=request.getParameter("mobile1");
	String mobile2=request.getParameter("mobile2");
	String height=request.getParameter("height");
	int heightInt = Integer.parseInt(height);
	System.out.println("주소:"+addr);
	
	//받은 파라미터 값들을 DB에 추가하는 작업 필요
	//DB에 접근할 수 있는 객체 -> DAO객체가 필요함
	UserDao dao=new UserDao();
	
	boolean isS = dao.insertUser(new UserDto(userId,name,birthYearInt, addr, mobile1, mobile2,heightInt,null));
	
	if(isS){
		response.sendRedirect("index.jsp");
	} else {
		response.sendRedirect("error.jsp");
	}
%>
</body>
</html>