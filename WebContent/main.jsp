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
		<c:set var="page" value="${page}"></c:set>
		<c:choose>
		<c:when test="${page eq 'read'}">
		<%@ include file="/WEB-INF/view/readArticle.jsp" %>
		</c:when>
		<c:otherwise>
		<%@ include file="/WEB-INF/view/listArticle.jsp" %>
		</c:otherwise>
		</c:choose>
    </div>
    <div style="position: absolute; left: 78%;  margin: 0px;"> 
		<%@ include file="/index.jsp" %>
    </div>
</body>
</html>