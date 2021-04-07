<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<title>게시글 삭제</title>
</head>
<body>
<form action="delete.do" method="post">
<input type="hidden" name="no" value="${delReq.articleNumber}">
정말 삭제 하시겠습니까?
<input type="submit" name="삭제" value="삭제">
</form>
</body>
</html>