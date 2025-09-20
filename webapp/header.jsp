<%@page import="com.hk.board.daos.board_daos"%>
<%@page import="com.hk.board.dtos.board_dtos"%>
<%
	response.setHeader("Pragma", "no-cache");
	response.setHeader("Cache-Control","no-cache");
	response.setHeader("Cache-Control","no-store");
	response.setDateHeader("Expires", 0L);
%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%request.setCharacterEncoding("utf-8"); %>
<%response.setContentType("text/html;charset=UTF-8"); %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Insert title here</title>
	<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-sRIl4kxILFvY47J16cr9ZwB07vP4J8+LH7qKQnuqkuIAvNWLzeN8tE5YBujZqJLB" crossorigin="anonymous">
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/js/bootstrap.bundle.min.js" integrity="sha384-FKyoEForCGlyvwx9Hj09JcYn3nv7wiPVlz7YYwJrWVcXK/BmnVDxM+D2scQbITxI" crossorigin="anonymous"></script>
	<script src="js/cookieFunc.js" type="text/javascript"></script>
	<link href="css/layout1.css" rel="stylesheet"/>
</head>
<%
	// index.jsp, registform.jsp -> 로그인 정보가 필요없음
	// -->문제점: 로그인정보가 없기때문에 index.jsp로 무조건 이동된다.
	// 요청URL을 확인해서 2개의 페이지 url이 해당된다면 제외시키자
	System.out.println("요청URL:"+request.getRequestURI());
	board_dtos ldto=null;
	String requestPath=request.getRequestURI();
	
	//요청 URL에 index.jsp와 registform.jsp가 포함되지 않았을 경우
	if(!requestPath.contains("index.jsp")&&
	   !requestPath.contains("registform.jsp")){
		ldto=(board_dtos)session.getAttribute("ldto");
		if(ldto==null){// 로그인이 안되어 있는 경우
			response.sendRedirect("index.jsp");
			return ;
		}
	}
	
	//폴더별로 페이지를 구분 관리
	// user/user_main.jsp
	//     /user_info.jsp
	
	// user/* --> 로그인이 필요한 페이지 처리
	// admin/*
	
	// filter 개념 : 클라이언트에서 서버(컨테이너)로 요청들어가기전에 실행
%>
<body>
<nav class="navbar navbar-expand-lg bg-primary-subtle">
<div class="container-fluid">
  <a class="navbar-brand" href="#">Navbar</a>
  <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
    <span class="navbar-toggler-icon"></span>
  </button>
  <div class="collapse navbar-collapse" id="navbarSupportedContent">
    <ul class="navbar-nav me-auto mb-2 mb-lg-0">
      <li class="nav-item">
        <a class="nav-link active" aria-current="page" href="#">Home</a>
      </li>
      <li class="nav-item">
        <a class="nav-link" href="#">Link</a>
      </li>
      <li class="nav-item dropdown">
        <a class="nav-link dropdown-toggle" href="#" role="button" data-bs-toggle="dropdown" aria-expanded="false">
          Dropdown
        </a>
        <ul class="dropdown-menu">
          <li><a class="dropdown-item" href="#">Action</a></li>
          <li><a class="dropdown-item" href="#">Another action</a></li>
          <li><hr class="dropdown-divider"></li>
          <li><a class="dropdown-item" href="#">Something else here</a></li>
        </ul>
      </li>
      <li class="nav-item">
        <a class="nav-link disabled" aria-disabled="true">Disabled</a>
      </li>
    </ul>
    <form class="d-flex" role="search">
      <input class="form-control me-2" type="search" placeholder="Search" aria-label="Search" />
      <button class="btn btn-outline-success" type="submit">Search</button>
    </form>
  </div>
</div>
</nav>
</body>
</html>