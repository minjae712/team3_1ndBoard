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
<title>게시글 수정</title>
</head>
<body>
<div></div>
<center>
<b>게시글을 수정했습니다.</b><br>
<br>
${ctxPath = pageContext.request.contextPath ; ''}
<a class="btn btn-default" href="${ctxPath}/article/list.do">게시글 목록보기</a>
<a class="btn btn-default" href="${ctxPath}/article/read.do?no=${modReq.articleNumber}">게시글 내용보기</a>
</center>
</body>
</html>