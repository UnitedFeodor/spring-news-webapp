<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>

<%@ page language="java" contentType="text/html; charset=utf-8"
		 pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>


<%-- <%@include jsp directive here for localization vars import in a different file --%>
<%@ include file="/WEB-INF/view/localization/localizationBase.jsp" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript" src="script/validation.js"></script>
<title> news management

</title>

<link rel="stylesheet" type="text/css" href="../../static/css/newsStyleBroken.css">
	<style>
		.header {
			background: -webkit-linear-gradient(top left,cornflowerblue,lightskyblue )
		}

		.page {
			background: -webkit-linear-gradient(top,crimson,hotpink );
			-webkit-text-fill-color: white;
		}
		.content,.footer {background: -webkit-linear-gradient(top left,cornflowerblue,lightskyblue )}
		input {
			-webkit-text-fill-color: black;
			background: white;
		}
		select {
			-webkit-text-fill-color: black;
			background: white;
		}
		textarea {
			-webkit-text-fill-color: black;
			background: white;
		}

		.content > a {
			-webkit-text-fill-color: hotpink;
			border-bottom-color: hotpink
		}
		a {
			-webkit-text-fill-color: yellow;
			border-bottom-color: yellow}
		a:hover {
			-webkit-text-fill-color: hotpink;
			background-color: transparent;
			text-decoration: underline;
		}

		.menu-wrapper {
			background: -webkit-linear-gradient(top left,cornflowerblue,lightskyblue  );
			-webkit-text-fill-color: white;
		}
		.menu-title,.list-menu-wrapper {
			background: -webkit-linear-gradient(top left,crimson,hotpink );
			-webkit-text-fill-color: white;
		}
		.menu-title,.list-menu-wrapper,.menu-wrapper > li {
			color: yellow;
		}

		.pagination {
			margin: 20px;
			padding-bottom: 10px;
			align-content: center;
		}
		.pagination a {
			font-size: larger;
			color: yellow;
			float: left;
			padding: 8px 16px;
			text-decoration: none;
		}

		.pagination a.active {
			background-color: deeppink;
			color: white;
		}
		.pagination #selected-page {
			background-color: hotpink;
			pointer-events: none;
		}

		.news-title {
			background: -webkit-linear-gradient(top left,crimson,hotpink );
			-webkit-text-fill-color: white;
			font-size: xx-large;
		}
		.news-content {
			background: -webkit-linear-gradient(top right,crimson,hotpink );
			-webkit-text-fill-color: white;
		}
		.news-padding {
			padding-left: 10x;
		}


	</style>

</head>
<body style="background: -webkit-linear-gradient(bottom left, crimson 10px, hotpink, crimson, hotpink 90%)">
	<div class="page">
		<div class="header">
			<%--
			<c:url value = "/WEB-INF/pages/tiles/header.jsp" var = "headerURL">
				<c:param  name="header_name" value="${header_name}"/>
			</c:url>
			<c:import url = "${headerURL}"/> --%>
			<%--
			<c:out value="${loc}"/><br>
			<c:out value="${sessionScope.local}"/>

			<c:out value="${wrapper_welcome}" />
			--%>

			<c:import url="/WEB-INF/view/header.jsp">

				<c:param  name="header_name" value="${header_name}"/>
			</c:import>


		</div>

		<div class="base-layout-wrapper">
			<div class="menu">
				<sec:authorize access="!isAuthenticated()">
<%--				<c:if test="${not (sessionScope.userActivity eq 'active')}">--%>
					<div style="align-content: center; padding: 20px">
						<c:out value="${wrapper_welcome}"/>
					</div>
				</sec:authorize>
					<%-- <c:import url=""></c:import> --%>
<%--				</c:if>--%>

				<sec:authorize access="isAuthenticated()">
<%--				<c:if test="${sessionScope.userActivity eq 'active'}">--%>
					<c:import url="/WEB-INF/view/menu.jsp" />
<%--				</c:if>--%>
				</sec:authorize>
		</div>

		<div class="content">
				<c:choose>
					<c:when test="${not empty sessionScope.error_msg}">
						<c:import url="/WEB-INF/view/error.jsp"/>
					</c:when>
<%--					<c:when test="${not (sessionScope.userActivity eq 'active')}">--%>
<%--						<c:import url="/WEB-INF/view/guestBody.jsp" />--%>
<%--					</c:when>--%>
<%--					<c:when test="${(sessionScope.userActivity eq 'active')}">--%>
<%--						<c:import url="/WEB-INF/view/body.jsp" />--%>
<%--					</c:when>--%>
				</c:choose>

			<sec:authorize access="!isAuthenticated()">
				<c:import url="/WEB-INF/view/guestBody.jsp" />
			</sec:authorize>
			<sec:authorize access="isAuthenticated()">
				<c:import url="/WEB-INF/view/body.jsp" />
			</sec:authorize>



			</div>
		</div>

		<div class="footer">

			<c:import url="/WEB-INF/view/footer.jsp" />
		</div>
	</div>
</body>
</html>