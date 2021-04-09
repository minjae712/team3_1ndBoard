<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>		<!-- JSTL 코어 태그를 사용하기 위해 태그라이브러리 디렉티브를 구현 -->
<%@ taglib prefix="u" tagdir="/WEB-INF/tags/" %>						<!-- 사용자 지정 태그를 사용하기 위해 태그라이브러리 디렉티브를 구현 --> 

<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>회원제 게시판 예제</title>												<!-- index 이름 지정 -->
</head>
<body>

<!-- 사용자 지정 태그라이브러리를 사용하여 login,logout 상태를 보여준다. --> 
<u:isLogin>																<!-- u 접두어를 사용하여 tags에 있는 사용자지정 태그를 불러온다. -->
																		<!-- isLogin은 로그인한 상태에서 볼  수 있는 메뉴를 띄운다. -->
	CT: ${authUser.name}님 환영합니다.										<!-- Login을 거쳐 ,Session 영역에 authUser 변수가 저장된 후, 그 변수를 불러온다. -->
	<a href="${pageContext.request.contextPath}/logout.do">[로그아웃하기]</a><!--  앵커태그로 링크를 걸어준다.(링크는 WEB-INF/commandHandlerURI.property에서 설정한다.) -->
	<a href="${pageContext.request.contextPath}/changePwd.do">[암호 변경하기]</a>
																	
</u:isLogin>
<u:notLogin>															<!-- u 접두어를 사용하여 tags에 있는 사용자지정 태그를 불러온다. -->
																		<!-- notLogin은 로그인 하지 않은 상태에서 볼 수 있는 목록을 띄운다. -->
	<a href="${pageContext.request.contextPath}/login.do">[로그인하기]</a>
	<a href="${pageContext.request.contextPath}/join.do">[회원가입하기]</a>

</u:notLogin>

</body>
</html>