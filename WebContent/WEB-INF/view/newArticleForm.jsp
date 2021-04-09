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

<title>게시글 쓰기</title>
</head>
<body>
<center>
<form class="form-inline" action="write.do" method="post">
<div class="form-group" align="center">
<p>
	<label for="exampleInputName2">제목</label>
	<br/><input type="text" name="title" value="${param.title}" class="form-control" placeholder="제목을 입력해주세요" style="width:300px">
	<c:if test="${errors.title}">제목을 입력하세요.</c:if>
</p>
</div>
<br>
<div class="form-group" align="center">
<p>
	<label for="exampleInputName2">내용</label>
	<br/>
	<textarea name="content" rows="5" cols="30" class="form-control" placeholder="내용을 입력해주세요" style="width:500px;height: 300px">${param.title}</textarea>
</p>
</div>
<br>
<input class="btn btn-default" type="submit" value="새 글 등록">
<input class="btn btn-default" type="button" value="취소" onClick="location.href='list.do'">
</center>
</form>
</body>
</html>