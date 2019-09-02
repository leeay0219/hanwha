<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h1>직원 정보 입력</h1>
<form action = "empinsert" method = "post">
직원번호: <input type = "number" name = "employee_id"><br>
이름: <input type = "text" name = "first_name"><br>
성: <input type = "text" name = "last_name"><br>
이메일: <input type = "text" name = "email"><br>
전화번호: <input type = "text" name = "phone_number"><br>
입사일: <input type = "date" name = "hire_date"><br>
직무: <select name = "job_id"> 
	  	<c:forEach items = "${joblist }" var = "job">
	  	<option value = "${job}">${job }</option>
	  	</c:forEach>
	  </select>
	  <br>
연봉: <input type = "number" name = "salary"><br>
커미션: <input type = "number" name = "commission_pct"><br>
매니저번호: <select name = "manager_id"> 
	  	<c:forEach items = "${managerlist }" var = "m">
	  	<option>${m }</option>
	  	</c:forEach>
	  </select>
	  <br>
부서 번호:	<select name="department_id">
				<c:forEach items="${deptlist}" var="dept">
					<option value="${dept.department_id}">${dept.department_name}</option>
				</c:forEach>
			</select>
			<br>
<input type = "submit" value = "입력하기">
</form>
</body>
</html>