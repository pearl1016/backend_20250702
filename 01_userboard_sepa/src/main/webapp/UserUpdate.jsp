<%@page import="com.hk.board.dtos.UserDto"%>
<%@page import="com.hk.board.daos.UserDao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%
// 회원등록 기능을 참고하여 구현해보세요
	// UserDao 클래스에 구현할 내용
	// updateUser()메서드를 구현
	//		--> update문을 실행
	// 파라미터 받기
	String userId = request.getParameter("userId");
	String name = request.getParameter("name");
	String birthYear = request.getParameter("birthYear"); // String -> int
	int birthYearInt = Integer.parseInt(birthYear);
	String addr = request.getParameter("addr");
	String mobile1 = request.getParameter("mobile1");
	String mobile2 = request.getParameter("mobile2");
	String height = request.getParameter("height");
	int heightInt = Integer.parseInt(height); // String -> int
	// dao 객체 생성
	UserDao dao = new UserDao();
	// updateUser() 실행
	boolean isS = dao.updateUser(new UserDto(userId, name, birthYearInt,addr, mobile1, mobile2, heightInt, null));
	// 응답 페이지로 이동 : 수정 성공하면 index.jsp로 이동, 실패하면 error.jsp 이동
	if(isS) {
		/* response.sendRedirect("index.jsp"); */
		// 회원 목록 페이지로 응답..
		/* response.sendRedirect("userList.jsp"); */
		// 수정폼으로 응답..
		//response.sendRedirect("userUpdateForm.jsp?userId="+userId);
%>
		<!-- html 영역 -->
		<script type="text/javascript">
			alert("회원정보를 수정했습니다!!");
			location.href="userUpdateForm.jsp?userId<%=userId%>";
		</script>
		
		<% 
	}else {
		response.sendRedirect("error.jsp");
	}
%>
</body>
</html>