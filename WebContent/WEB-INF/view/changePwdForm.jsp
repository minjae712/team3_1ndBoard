<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="${pageContext.request.contextPath}/bootstrap-3.3.2-dist/css/bootstrap.min.css">
<style>
body{
	margin-top: 10%;
}
</style>

<title>암호 변경</title>
</head>
<body>
<center>
<form class="form-inline" action="changePwd.do" method="post" >
<div class="form-group" align="center">
<p>
	<label for="exampleInputName2">현재 비밀번호</label>
	<br/><input type="password" name="curPwd" class="form-control" placeholder="현재 비밀번호">
	<c:if test="${errors.curPwd}"><br>현재 비밀번호를 입력하세요.</c:if>
	<c:if test="${errors.badCurPwd}"><br>현재 비밀번호가 일치하지 않습니다.</c:if>
</p>
</div>
<br>
<div class="form-group" align="center">
<br>
<p>
	<label for="exampleInputName2">새 비밀번호 </label>
	<br/><input type="password" name="newPwd" class="form-control" placeholder="새 비밀번호">
	<c:if test="${errors.newPwd}"><br>새 비밀번호를 입력하세요.</c:if>
</p>
<br>
<input class="btn btn-default" type="submit" value="암호 변경">
<a class="btn btn-default" href="${pageContext.request.contextPath}/index.jsp">취소</a>
</div>
</form>
</center>
</body>
</html>