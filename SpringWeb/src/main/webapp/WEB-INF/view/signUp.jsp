<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/header.jsp"%>
<!DOCTYPE>
<html lang="ko">
<head>
	<meta charset="UTF-8">
	<title>회원가입</title>
	<meta name="viewport" content="width=device-width, initial-scale=1">
  	<link rel="stylesheet" href="com/css/bootstrap.min.css">
	<script src="com/js/jquery-1.9.1.min.js"></script>
	<script src="com/js/bootstrap.min.js"></script>
	<script type="text/javascript">
	$(document).ready(function() {
		$("#userid").blur(function() {
			var userid = $("#userid").val();
			$.ajax({
	            type: "POST", //GET or POST
	            url: "userSearId.json", //접속할 URL
	            data: {"userid": userid},  //파라미터
	            datatype: "json",  //출력 데이터 타입(html, xml, json, text)
	            success: function(data) {
	            	if (data.length == 0) {
	            		$(".userid-block").css("color", "#000");
	            		$(".userid-block").text("사용 가능한 아이디 입니다");
	            	} else {
	            		$(".userid-block").css("color", "#f00");
	            		$(".userid-block").text("이미 사용중인 아이디 입니다.");
	            		$("#userid").val("");
	            		$("#userid").focus();
	            	}
	            }
	        });
		});
	});
	</script>
</head>
<body>
<div class="container">
	<h2>회원가입</h2>
	<h4>회원 가입해 주시기 바랍니다.</h4>
	<form:form modelAttribute="signUpForm" class="form-horizontal" role="form">
	    <div class="form-group">
			<label class="control-label col-sm-2" for="userid">아이디</label>
			<div class="col-sm-10">
				<form:input path="userid" class="form-control" placeholder="Enter userid"/>
				<p class="userid-block"></p>
				<p class="help-block"><form:errors path="userid" /></p>
				<span class="glyphicon glyphicon-ok form-control-feedback"></span>
			</div>
	    </div>
	    <div class="form-group">
			<label class="control-label col-sm-2" for="password">패스워드</label>
			<div class="col-sm-10">
				<form:password path="password" class="form-control" placeholder="Enter password"/>
				<p class="help-block"><form:errors path="password" /></p>
			</div>
	    </div>
	    <div class="form-group">
			<label class="control-label col-sm-2" for="name">이름</label>
			<div class="col-sm-10">
				<form:input path="name" class="form-control" placeholder="Enter name"/>
				<p class="help-block"><form:errors path="name" /></p>
			</div>
	    </div>
	    <div class="form-group">
			<label class="control-label col-sm-2" for="email">이메일</label>
			<div class="col-sm-10">
				<form:input path="email" class="form-control" placeholder="Enter email"/>
				<p class="help-block"><form:errors path="email" /></p>
			</div>
		</div>
		<div class="form-group">
			<label class="control-label col-sm-2" for=role>권한</label>
			<div class="col-sm-10">
				<form:select path="role" class="form-control">
					<form:option value="">:::::  선택하세요  :::::</form:option>
					<form:option value="admin">어드민</form:option>
					<form:option value="user">일반</form:option>
				</form:select>
			</div>
		</div>
	    <div class="form-group">        
			<div class="col-sm-offset-2 col-sm-10">
				<button type="button" class="btn btn-default" onClick="havascript:history.back(-1);">취소</button>
				<button type="submit" class="btn btn-default">가입</button>
			</div>
		</div>
	</form:form>
</div>
</body>
</html>