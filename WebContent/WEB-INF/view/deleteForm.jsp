<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<style>
div{
	margin-top: 10%;
}
</style>

<link rel="stylesheet" href="${pageContext.request.contextPath}/bootstrap-3.3.2-dist/css/bootstrap.min.css">
<title>게시글 삭제</title>
</head>
<body>
<div></div>
<center>
<form action="delete.do" method="post" >
<div class="form-group" align="center">
<input type="hidden" name="no" value="${delReq.articleNumber}">
<label for="exampleInputName2"><b>게시글을 정말로 삭제하시겠습니까 ?</b><br></label>
<input class="btn btn-default" type="submit" value="삭제">
<input class="btn btn-default" type="button" onclick="history.back(-1);" value="취소">
</div>
</center>
</form>
</body>
</html>