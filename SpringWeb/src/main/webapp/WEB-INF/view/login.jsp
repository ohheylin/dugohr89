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
	<h1>회원인증</h1>
  	<form:form modelAttribute="loginForm" class="form-horizontal" role="form">
 	  	<div class="form-group">
			<div class="col-sm-10">
				<form:errors path="*" element="div" class="form-control"/>
			</div>
	    </div>
 	  	<div class="form-group">
			<label class="control-label col-sm-2" for="userid">userid</label>
			<div class="col-sm-10">
				<form:input path="userid" class="form-control" placeholder="Enter userid"/>
			</div>
	    </div>
	    <div class="form-group">
			<label class="control-label col-sm-2" for="password">password</label>
			<div class="col-sm-10">
				<form:input path="password" class="form-control" placeholder="Enter password"/>
			</div>
	    </div>
	    <div class="form-group">        
			<div class="col-sm-offset-2 col-sm-10">
				<button type="submit" class="btn btn-default">로그인</button>
			</div>
		</div>
	</form:form>
</div>
</body>
</html>