<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/header.jsp"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<title>자유게시판(글읽기)</title>
</head>
<body>
	<form name="form1" mathod="post" action="">
		<table width="700" border="1" align="center">
			<tr>
				<td width="125">글번호</td>
				<td width="125">${board.num}</td>
				<td width="125">조회수</td>
				<td width="125">${board.count}</td>
			</tr>
			<tr>
				<td width="125">작성자</td>
				<td width="125">${board.userid}</td>
				<td width="125">작성일</td>
				<td width="125"><fmt:formatDate value="${board.regdate}"
						pattern="yyyy/MM/dd HH:mm:ss" /></td>
			</tr>
			<tr>
				<td width="125">글제목</td>
				<td width="375" colspan="3">${board.title}</td>
			</tr>
			<tr>
				<td width="125">글내용</td>
				<td width="375" colspan="3"><textarea rows="13" cols="18"
						readOnly>${board.content}</textarea></td>
			</tr>
		</table>
	</form>
</body>
</html>