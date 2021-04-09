<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<link rel="stylesheet" href="${pageContext.request.contextPath}/bootstrap-3.3.2-dist/css/bootstrap.min.css">
<head>
<style>
body{
	margin-top: 10%;
}
</style>
<title>로그인</title>
</head>
<body>
<center>
<form class="form-inline" action="login.do" method="post" >
<div class="form-group" align="center">
<p>
	<label for="exampleInputName2">아이디 </label>
	<br/><input type="text" name="id" value="${param.id}" class="form-control" placeholder="ID">
	<c:if test="${errors.idOrPwNotMatch}">
	<br>아이디와 암호가 일치하지 않습니다.
	</c:if>
	<c:if test="${errors.id}">ID를 입력하세요.<br></c:if>
</p>
</div>
<br>
<div class="form-group" align="center">
<p>
	<label for="exampleInputName2">비밀번호 </label>
	<br/><input type="password" name="password" class="form-control" placeholder="비밀번호">
	<c:if test="${errors.password}"><br>비밀번호를 입력하세요.</c:if>
</p>
<br>
</div>
<br>
<input class="btn btn-default" type="submit" value="로그인">
<a class="btn btn-default" href="${pageContext.request.contextPath}/index.jsp">취소</a>
</form>
</center>
</body>
</html>