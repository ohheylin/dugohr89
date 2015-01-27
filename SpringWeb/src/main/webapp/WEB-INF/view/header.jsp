<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<c:if test="${sessionScope.userInfo.role ne null && sessionScope.userInfo.role eq 'admin' }">
	<c:set var="s_title" value="관리자 ${sessionScope.userInfo.name}"/>
</c:if>
<c:if test="${sessionScope.userInfo.role ne null && sessionScope.userInfo.role eq 'user' }">
	<c:set var="s_title" value="일반사용자 ${sessionScope.userInfo.name}"/>
</c:if>