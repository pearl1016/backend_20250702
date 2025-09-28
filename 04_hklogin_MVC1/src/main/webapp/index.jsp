<%@ include file="header.jsp" %>
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
<% //로그인 실패시 전달되는 메시지를 받는다.
   String msg=request.getParameter("msg"); %>
<div id="container" style="width: 500px;margin:100px auto;">
	<form class="form-control" action="userController.jsp" method="post" onsubmit="userIdCookie()">
		<input type="hidden" name="command" value="login">
		<h1>Login</h1>
		<input class="form-control mb-2" type="text" name="id" placeholder="ID" 
									 required="required"/>
		<input class="form-control mb-2" type="password" name="password" 
									 placeholder="password" 
									 required="required"/>
	    <input class="form-check-input mb-2" type="checkbox" value="remember-me"/><label>아이디 저장</label>
		<label><small><%=msg==null?"":msg%></small></label>
		<button class="form-control btn btn-primary mb-2" type="submit">Sign In</button>
		<button class="form-control btn btn-primary mb-2" type="button" onclick="registForm()">Sign Up</button>
	</form>
</div>
<script type="text/javascript">
	function registForm(){
		location.href="userController.jsp?command=registform";
	}
	
	//아이디를 쿠키에 저장하기
	function userIdCookie(){
		//chkId: 체크박스 객체 구함
		const chkID=
			document.querySelectorAll("input[type=checkbox]")[0];
		//입력된 id구함
		const id=document.querySelectorAll("input[name=id]")[0].value;
		
		if(chkID.checked){//체크가 됐다면 쿠키에 id값을 저장
			setCookie("rememberid",id,100);
		}else{
			removeCookie("rememberid");
		}
	}
	
	//cookie에 아이디값이 저장되어 있다면 id입력박스에 id를 출력시키자
	onload=function(){
		const cookieID=getCookie("rememberid");
		if(cookieID!=null){
			//저장된 id가 있다면 id입력박스에 해당id를 입력
			document.querySelectorAll("input[name=id]")[0]
								.value=cookieID;
			//저장된 id가 있다면, id체크박스에 체크되게 함
			document.querySelectorAll("input[type=checkbox]")[0]
								.checked=true;
		}
	}
	
</script>
</body>
</html>
<%@ include file="footer.jsp" %>