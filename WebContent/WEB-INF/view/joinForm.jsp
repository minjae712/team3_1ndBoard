<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="${pageContext.request.contextPath}/bootstrap-3.3.2-dist/css/bootstrap.min.css">
<style>
body{
	margin-top: 5%;
}
</style>

<title>가입</title>
</head>
<body>
<center>
<form class="form-inline" action="join.do" method="post">
<div class="form-group" align="center">
<p>
	<label for="exampleInputName2">ID</label>
	<br/><input type="text" name="id" value="${param.id}" class="form-control" placeholder="ID">
	<c:if test="${errors.id}"><br>ID를 입력하세요.</c:if>
	<c:if test="${errors.duplicateId}"><br>이미 사용중인 ID입니다.</c:if>
</p>
</div>
<br>
<div class="form-group" align="center">
<p>
	<label for="exampleInputName2">이름</label>
	<br/><input type="text" name="name" value="${param.name}" class="form-control" placeholder="이름">
	<c:if test="${errors.name}"><br>이름을 입력하세요.</c:if>
</p>
</div>
<br>
<div class="form-group" align="center">
<p>
	<label for="exampleInputName2">비밀번호</label>
	<br/><input type="password" name="password" class="form-control" placeholder="비밀번호">
	<c:if test="${errors.password}"><br>비밀번호를 입력하세요.</c:if>
</p>
</div>
<br>
<div class="form-group" align="center">
<p>
	<label for="exampleInputName2">비밀번호 확인</label>
	<br/><input type="password" name="confirmPassword" class="form-control" placeholder="비밀번호 확인">
	<c:if test="${errors.confirmPassword}"><br>확인을 입력하세요.</c:if>
	<c:if test="${errors.notMatch}"><br>암호와 확인이 일치하지 않습니다.</c:if>
</p>
</div>
<br>
<input class="btn btn-default" type="submit" value="가입">
<a class="btn btn-default" href="${pageContext.request.contextPath}/index.jsp">취소</a>
</center>
</form>
</body>
</html>