<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/header.jsp"%>
<!DOCTYPE>
<html lang="ko">
<head>
	<meta charset="UTF-8">
	<title>메인 페이지</title>
	<meta name="viewport" content="width=device-width, initial-scale=1">
  	<link rel="stylesheet" href="com/css/bootstrap.min.css">
	<script src="com/js/jquery-1.9.1.min.js"></script>
	<script src="com/js/bootstrap.min.js"></script>
	<script type="text/javascript">
	$(document).ready(function() {
	});
	</script>
</head>
<body>
<div class="container">
	<h1>Main Page입니다.</h1>
	<%-- <h2><font color="blue">${sessionScope.userInfo.userid }님 환영합니다.</font></h2> --%>
	<h2><font color="blue">${s_title }님 환영합니다.</font></h2>
	<button type="button" class="btn btn-danger" onClick="javascript:location.href='logout.html'">로그아웃</button></td>

	<br/>
	<br/>
	<button type="button" class="btn btn-primary" onClick="javascript:location.href='boardList.html'">글목록보기</button>
	<button type="button" class="btn btn-primary" onClick="javascript:location.href='insert.html'">글작성하기</button>
</div>
</body>
</html>

