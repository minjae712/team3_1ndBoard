<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>					<!-- 코어태그를 사용하기 위해 태그라이브러리를 설정한다.--> 
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="${pageContext.request.contextPath}/bootstrap-3.3.2-dist/css/bootstrap.min.css">
<meta charset="UTF-8">
<title>게시글 목록</title>
</head>
<body>

	<table class="table table-striped" >											<!-- 테이블 형식을 만든다. -->
	<tr><td colspan="4"><a class="btn btn-default" href="write.do"><b>게시글쓰기</b></a></td></tr>
																					<!-- 만든 테이블 형식에  tr로 행(row)을 생성하고 td로 4칸짜리 열을 만든 후 링크를 건다. -->
	<tr><td>번호</td><td>제목</td><td>작성자</td><td>조회수</td></tr>						<!-- 각 번호,제목,작성자,조회수 의 열(column)을 형성해 4개의 열을 만든다. -->
	
																					<!-- 3,4번째 행은 게시물이 있는지 없는지에 따라 나타내는 형식을 다르게 설정한다. -->
	<c:if test="${articlePage.hasNoArticles() }">										<!-- 게시글이 없을경우,(전체 게시글 수가 0일때 true)  -->
	<tr>																				<!-- 3행 -->
	<td colspan="4">게시글이 없습니다.</td>													<!-- "게시글이 없습니다" 라는 문구를 1~4 열에 해당하는 범위만큼 지정한다. -->
	</tr>												
	</c:if>																				<!-- /게시글이 없을경우  -->
	
	
	
	<c:forEach var="article" items="${articlePage.content}">							<!-- 게시글이 있을경우(1)[전체 게시글 객체가 0일경우 반복출력되지 않으므로 없는 행이 된다.]  -->
	<tr>																				<!-- 3행 -->
	<td>${article.number }</td>															<!-- 3행 1열은 article(= 전체 게시글 List)의 getNumber(= 게시글 번호)로 채운다. -->
	<td>																				<!-- 3행 2열은 -->
	<a href="read.do?no=${article.number}&pageNo=${articlePage.currentPage}">			<!-- read.do(readArticle.jsp)페이지[게시글 번호와 사용자의 현재 페이지를 파라미터로 받는다] -->
	<c:out value="${article.title}" />													<!-- 로 이동하게 하고,게시글 제목 을 문자 그대로 출력하게 한다. -->
	</a>
	</td>
	<td>${article.writer.name }</td>													<!-- 3행 3열은 해당 게시글 작성자의 이름으로 채우고 -->
	<td>${article.readCount }</td>														<!-- 3행 4열은 해당 게시글의 조회수로 채운다. -->
	</tr>
	</c:forEach>																		<!-- /게시글이 있을경우(1)  -->
	
	
	
	<c:if test="${articlePage.hasArticles() }">											<!-- 게시글이 있을경우(2),(전체 게시글 수가 1 이상일 경우 true)  -->
	<tr>																				<!-- 4행 -->
	<td colspan="4" class="text-center">																	<!-- 4행 1~4열에 해당하는 범위만큼 -->
	
	<ul class="pagination">
	<c:if test="${articlePage.startPage > 5 }">											<!-- 만약 페이지의 시작페이지의 숫자가 5보다 크다면 -->
	<li><a href="list.do?pageNo=${articlePage.startPage - 5}">이전</a></li>				<!-- [이전] 이라는 링크를 만들어 현재 보여주는 목록보다 5개 이전 목록을 볼 수 있도록 이동할 수 있게 한다. -->
	</c:if>
	<c:forEach var="pNo" begin="${articlePage.startPage}" end="${articlePage.endPage }"><!-- articlePage.startPage 부터 articlePage.endPage까지를 범위로--> 
	<li><a href="list.do?pageNo=${pNo}">${pNo}</a></li>									<!-- list.do?pageNo에서 첫 페이지[1]부터 마지막 페이지[n]까지 반복 출력후 링크를 건다. -->
	</c:forEach>
	<c:if test="${articlePage.endPage < articlePage.totalPages }">						<!-- 만약 마지막페이지가 전체 페이지수보다 작을 경우 --> 
	<li><a href="list.do?pageNo=${articlePage.starPage + 5 }">다음</a></li>				<!-- [다음] 이라는 링크를 만들어 현재 보여주는 목록보다 5개 이후 목록을 볼 수 있도록 이동할 수 있게 한다. -->
	</c:if>
	
	</ul>
	</td>																				<!-- /4행 1~4열 -->
	</tr>																				<!-- /게시글이 있을경우(2),  -->
	</c:if>
	</table>

</body>
</html>