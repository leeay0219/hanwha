<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    isErrorPage = "true"
    %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>내부오류</h1>
	<h2>${errormessage }</h2>
	<h2>관리자에게 연락하세요.</h2>
	<h3>${company}</h3>
	<h3>${manager}</h3>
	<h3>${number}</h3>
</body>
</html>