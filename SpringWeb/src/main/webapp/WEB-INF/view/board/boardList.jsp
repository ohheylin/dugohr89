<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/header.jsp"%>
<!DOCTYPE html>
<html lang="ko">
<head>
	<meta charset="UTF-8">
	<title>자유게시판(리스트)</title>
	<meta name="viewport" content="width=device-width, initial-scale=1">
 	<link rel="stylesheet" href="<%=request.getContextPath() %>/com/css/bootstrap.min.css">
	<script src="<%=request.getContextPath() %>/com/js/jquery-1.9.1.min.js"></script>
	<script src="<%=request.getContextPath() %>/com/js/bootstrap.min.js"></script>
	<script type="text/javascript">
 		function goPage(page) {
			location.href = 'boardList.html?pageNum='+page;
		}
	</script>
</head>
<body>
<div class="container">
	<h2>자유게시판</h2>
	<table class="table">
	<thead>
		<tr>
			<th>번호</th>
			<th>제목</th>
			<th>작성자</th>
			<th>조회수</th>
			<th>파일</th>
			<th>날짜</th>
		</tr>
	</thead>
	<tbody>
		<c:forEach var="board" items="${list}">
		<tr>
			<td>${board.num}</td>
			<td><a href ="detail.html?num=${board.num}">${board.title}</a></td>
			<td>${board.userid}</td>
			<td>${board.count}</td>
			<td><a href ="./images/${board.filename}" target="_blank">${board.filename}</a></td>
			<td><fmt:formatDate value="${board.regdate}" pattern="yyyy/MM/dd HH:mm:ss"/></td>
		</tr>
		</c:forEach>
	</tbody>
	</table>
	
	<table border="0" cellpadding="0" cellspacing="0" align="center">
		<tr>
			<td>
				<ul class="pager">
					<!-- 이전 -->
					<c:if test="${prevPage gt 0 }">
						<li><a href="javascript:goPage(${prevPage });">Previous</a></li>
					</c:if>
				</ul>
			</td>
			<td width="20"></td>
			<td>
				<ul class="pagination pagination-sm">
					<c:forEach begin="${1+prevPage }" end="${nextPage-1 }" step="1" varStatus="status">
						<c:choose>
							<c:when test="${currentPage eq status.index }">
								<li class="active"><a href="#">${status.index }</a></li>
							</c:when>
							<c:otherwise>
								<li><a href="javascript:goPage(${status.index });">${status.index }</a></li>
							</c:otherwise>
						</c:choose>
					</c:forEach>
				</ul>
			</td>
			<td width="20"></td>
			<td>
				<ul class="pager">
					<!-- 다음 -->
					<c:if test="${totalPage gt nextPage }">
						<li><a href="javascript:goPage(${nextPage });">Next</a></li>
					</c:if>
				</ul>
			</td>
		</tr>
	</table>

	<button type="button" class="btn btn-primary" onclick="location.href='insert.html'">글쓰기</button>
</div>
</body>
</html>