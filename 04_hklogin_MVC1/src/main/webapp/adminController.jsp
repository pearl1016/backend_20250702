<%@page import="com.hk.board.dtos.UserDto"%>
<%@page import="java.util.List"%>
<%@page import="com.hk.board.daos.AdminDao"%>
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
	/* 만들어 줄 페이지
	admin_main.jsp
	admin_listAll.jsp
	admin_list.jsp
	admin_userRoleForm.jsp : 회원상세조회(등급수정폼 포함)
	*/
	String command=request.getParameter("command");

	AdminDao dao=AdminDao.getAdminDao();
	
	if(command.equals("userlistall")){
		List<UserDto>list=dao.getAllUserList();
		request.setAttribute("list", list);
		pageContext.forward("admin_listAll.jsp");
	}else if(command.equals("userlist")){
		List<UserDto>list=dao.getUserList();
		request.setAttribute("list", list);
		pageContext.forward("admin_list.jsp");
	}else if(command.equals("userrole")){
		String id=request.getParameter("id");
		UserDto dto=dao.getUserRole(id);
		request.setAttribute("dto", dto);
		pageContext.forward("admin_userRoleForm.jsp");
	}else if(command.equals("updaterole")){
		String id=request.getParameter("id");
		String role=request.getParameter("role");
		boolean isS=dao.getUpdateRole(id,role);
		if(isS){
			response.sendRedirect("adminController.jsp?command=userlist");
		}else{
			response.sendRedirect("error.jsp");
		}
	}
%>
</body>
</html>