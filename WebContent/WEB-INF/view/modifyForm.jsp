<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="${pageContext.request.contextPath}/bootstrap-3.3.2-dist/css/bootstrap.min.css">
<title>게시글 수정</title>
</head>
<!-- 게시글 수정 폼을 보여주는 modifyform.jsp 파일이며
	ModifyArticleHandler에서 전달 받은  ModifyRequest객체를 이용해서 
	폼에 데이터를 채운다. 실제 로그인을 하고 본인이 작성한 게시글 읽기 화면에서 [게시글 수정]링크를 클릭하면 
	수정폼이 출력되도록 해줌-->
<body>
<form action="modify.do" method="post">
<input type="hidden" name="no" value="${modReq.articleNumber}">
<div class="form-group">
	제목:<br/><input type="text" name="title" class="form-control" style="width: 40%" value="${modReq.title}">
	<c:if test="${errors.title}">제목을 입력하세요.</c:if>
</div>
<div class="form-group">
	내용:<br/>
	<textarea name="content" class="form-control" style="width: 40%" rows="5" cols="30">${modReq.content}</textarea>
</div>
<button type="submit" class="btn btn-default">수정</button>
</form>
</body>
</html>