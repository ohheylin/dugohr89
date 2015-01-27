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
	<h2>현재시간 : ${now} </h2>
	
	<c:if test="${sessionScope.userInfo.name ne null }">
		<h5><font color="blue">${s_title }님 환영합니다.</font></h5>
		<button type="button" class="btn btn-warning" onClick="javascript:location.href='logout.html'">로그아웃</button></td>	
	</c:if>
	<c:if test="${sessionScope.userInfo.name eq null }">
		<button type="button" class="btn btn-default" onClick="javascript:location.href='login.html'">로그인</button>
		<button type="button" class="btn btn-default" onClick="javascript:location.href='signUp.html'">회원가입</button>
	</c:if>

	<br/>
	<br/>
	<button type="button" class="btn btn-primary" onclick="location.href='index.html'">초기화면</button>
	<button type="button" class="btn btn-primary" onclick="location.href='board/boardList.html'">게시판</button>
</div>
</body>
</html>