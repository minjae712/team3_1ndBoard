<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<title>게시글 읽기</title>
</head>
<body style="background: #fffcf6">
    <hr>
    <h1 style="font-style: inherit;">team3 게시판</h1>
    <hr>
	<div style="position: static;">
		<c:if test="${no == null} ">
		<%@ include file="/WEB-INF/view/ristArticle.jsp" %>
		</c:if>
		<c:if test="${no != null }">
		<%@ include file="/WEB-INF/view/readArticle.jsp" %>
		</c:if>
    </div>
    <div style="position: absolute; left: 78%;  margin: 0px;">
		<%@ include file="index.jsp" %>
    </div>
</body>
</html>