<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/header.jsp"%>
<!DOCTYPE html>
<html lang="ko">
<head>
	<meta charset="UTF-8">
	<title>자유게시판(글읽기)</title>
	<meta name="viewport" content="width=device-width, initial-scale=1">
 	<link rel="stylesheet" href="./com/css/bootstrap.min.css">
	<script src="./com/js/jquery-1.9.1.min.js"></script>
	<script src="./com/js/bootstrap.min.js"></script>
</head>
<body>
<div class="container">
	<h2>자유게시판</h2><h4>글보기</h4>
	<form id="readForm" name="readForm" mathod="post" action="update.html">
	<table class="table">
		<tr>
			<th>글번호</th>
			<td>${board.num}</td>
			<th>조회수</th>
			<td>${board.count}</td>
		</tr>
		<tr>
			<th>작성자</th>
			<td>${board.userid}</td>
			<th>작성일</th>
			<td><fmt:formatDate value="${board.regdate}" pattern="yyyy/MM/dd HH:mm:ss" /></td>
		</tr>
		<tr>
			<th>글제목</th>
			<td colspan="3">${board.title}</td>
		</tr>
		<tr>
			<th>글내용</th>
			<td colspan="3"><textarea class="form-control" rows="5" id="content" readonly>${board.content}</textarea>
			</td>
		</tr>
		<c:if test="${board.filename ne null}">
		<tr>
			<th>첨부파일</th>
			<td colspan="3"><label>${board.filename }</label></textarea>
			</td>
		</tr>
		</c:if>
		
		
	</table>
	
	<button type="button" class="btn btn-primary" onclick="location.href='boardList.html'">목록</button>
	<button type="button" class="btn btn-primary" onclick="location.href='update.html?num=${board.num}'">수정</button>
	<button type="button" class="btn btn-primary" onclick="location.href='delete.html?num=${board.num}'">삭제</button>
	</form>
</div>
</body>
</html>