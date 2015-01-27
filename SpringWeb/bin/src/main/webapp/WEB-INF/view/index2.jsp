<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/header.jsp"%>
<!DOCTYPE html>
<html lang="ko">
<head>
	<meta charset="UTF-8">
	<title>Index입니다...</title>
	<meta name="viewport" content="width=device-width, initial-scale=1">
  	<link rel="stylesheet" href="./com/css/bootstrap.min.css">
	<script src="./com/js/jquery-1.9.1.min.js"></script>
	<script src="./com/js/bootstrap.min.js"></script>
</head>
<body>
<div class="container">
	<h1>Hello World!</h1>
	<h2>현재시간 : ${now} </h2>
	<table class="table">
  	<form:form modelAttribute="loginForm">
 		<tr>
			<td>아이디 :</td>
			<td><form:input path="userid"/></td>
		</tr>
 		<tr>
			<th>비밀번호 :</th>
			<td><form:password path="password"/></td>
		</tr>
 		<tr>
			<td colspan="2"><input type="submit" value="로그인"/></td>
		</tr>
	</form:form>
	</table>
	
	<a href="./boardList.html">글목록보기</a>
	<a href="./insert.html">글작성하기</a>
</div>
</body>
</html>