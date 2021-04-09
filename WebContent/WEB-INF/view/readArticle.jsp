<%@ page contentType="text/html; charset=utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="u" tagdir="/WEB-INF/tags" %>

<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="${pageContext.request.contextPath}/bootstrap-3.3.2-dist/css/bootstrap.min.css">
<title>게시글 읽기</title>
</head>
<body style="background: #fffcf6">
		<!-- 변수 pageNo에 파라미터로 받은 pageNO을 담아주고 만약 pageNo이 없다면 1을 값으로 한다. -->
		 	<c:set var="pageNo" value="${empty param.pageNo ? '1' : param.pageNo}" />
		<a href="list.do?pageNo=${pageNo}">[목록]</a>
    <table class="table table-bordered" border="1" style="width: 75%; position: absolute;">
        <tr>
            <td colspan="2"><c:out value="${articleData.article.title}"></c:out></td>
        </tr>
        <tr>
            <td style="width: 30%;">작성자</td>
            <!-- 작성자 이름 -->
            <td><c:out value="${articleData.article.writer.name}"></c:out></td>
        </tr>
        <tr>
            <td colspan="2" style="height: 400px;">
            <u:pre value="${articleData.content}"/></td>
        </tr>
        <tr>
		<td colspan="2"m>
			<!-- 목록으로 돌아갈 링크를 출력한다. -->
			<!-- 게시글 작성자 아이디와 로그인 한 사용자 아이디가 동일하면 게시글 수정과 삭제 링크를 출력한다. -->
		 	<c:if test="${authUser.id == articleData.article.writer.id}">
			<a href="modify.do?no=${articleData.article.number}">[게시글수정]</a>
			<a href="delete.do?no=${articleData.article.number}">[게시글삭제]</a>
			</c:if>
		</td>
		</tr> 
	</table>
</body>
</html>

