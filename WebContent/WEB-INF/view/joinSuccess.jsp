<%@ page contentType="text/html; charset=utf-8"%>
<!DOCTYPE html>
<html>
<head>
<style>
div{
	margin-top: 10%;
}
</style>
<link rel="stylesheet" href="${pageContext.request.contextPath}/bootstrap-3.3.2-dist/css/bootstrap.min.css">
<title>가입 완료</title>
</head>
<body>
<div></div>
<center>
${param.name}님, 회원 가입에 성공했습니다.<br>
<a class="btn btn-default" href="${pageContext.request.contextPath}/index.jsp">돌아가기</a>
</center>
<br/>
</body>
</html>