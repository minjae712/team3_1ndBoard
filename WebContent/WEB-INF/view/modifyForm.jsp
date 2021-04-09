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
<title>게시글 수정</title>
</head>
<!-- 게시글 수정 폼을 보여주는 modifyform.jsp 파일이며
	ModifyArticleHandler에서 전달 받은  ModifyRequest객체를 이용해서 
	폼에 데이터를 채운다. 실제 로그인을 하고 본인이 작성한 게시글 읽기 화면에서 [게시글 수정]링크를 클릭하면 
	수정폼이 출력되도록 해줌-->
<body>
<center>
<form action="modify.do" method="post">
<input type="hidden" name="no" value="${modReq.articleNumber}" >
<p>
<b>
	게시글 번호 : ${modReq.articleNumber}
</b>
</p>
<div class="form-group" align="center">
<p>
	<label for="exampleInputName2">제목 </label>
	<br/><input type="text" name="title" value="${modReq.title}" class="form-control" placeholder="제목을 입력해주세요" style="width:500px">
	<c:if test="${errors.title}"><br>제목을 입력하세요.</c:if>
</p>
</div>
<div class="form-group" align="center">
<p>
	<label for="exampleInputName2">내용</label>
	<br/>
	<textarea name="content" rows="5" cols="30" class="form-control" placeholder="내용을 입력해주세요" style="width:500px;height: 300px">${modReq.content}</textarea>
</p>
</div>
<input class="btn btn-default" type="submit" value="글 수정">
<input type="button" onclick="history.back(-1);" value="취소">
</form>
</center>
</body>
</html>