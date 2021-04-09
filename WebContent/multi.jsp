<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="u" tagdir="/WEB-INF/tags/" %>						<!-- 사용자 지정 태그를 사용하기 위해 태그라이브러리 디렉티브를 구현 --> 

<!DOCTYPE html>
<html>
<link rel="stylesheet" href="${pageContext.request.contextPath}/bootstrap-3.3.2-dist/css/bootstrap.min.css">
<head>
<meta charset="utf-8">
<title>회원제 게시판 예제</title>						
</head>  
<body>
	
<!-- 사용자 지정 태그라이브러리를 사용하여 login,logout 상태를 보여준다. --> 
<u:isLogin>						
								
	${authUser.name}님, 환영합니다.
	<a class="btn btn-primary btn-sm" href="${pageContext.request.contextPath}/logout.do">로그아웃하기</a>
	<a class="btn btn-primary btn-sm" href="${pageContext.request.contextPath}/changePwd.do">암호 변경하기</a>
	<a class="btn btn-default" href="${pageContext.request.contextPath}/index.jsp">메인으로</a>
</u:isLogin>
<u:notLogin>
			
		<a class="btn btn-primary btn-sm" href="${pageContext.request.contextPath}/login.do"><b>로그인하기</b></a>
		<a class="btn btn-primary btn-sm" href="${pageContext.request.contextPath}/join.do"><b>회원가입하기</b></a>
		<a class="btn btn-default" href="${pageContext.request.contextPath}/index.jsp">메인으로</a>
</u:notLogin>
	
</body>
</html>