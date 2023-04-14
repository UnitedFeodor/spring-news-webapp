<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ include file="/WEB-INF/view/localization/localizationBase.jsp" %>

<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
${guestinfo_head}

<div class="body-title">
	<a href="">${goback_news} </a> ${guestinfo_goback_current}
</div>

<form action="controller" method="post">
	<c:forEach var="news" items="${news}">
		<div class="single-news-wrapper">
			<div class="single-news-header-wrapper">
				<div class="news-title">
					<c:out value="${news.title}" />
				</div>
				<div class="news-date">
					<tags:localDate date="${news.dateAdded}"/>
					<%--%><c:out value="${news.newsDate}" /> --%>
				</div>

				<div class="news-content">
					<c:out value="${news.brief}" />
				</div>
			</div>
		</div>

	</c:forEach>

	<div class="no-news">
		<c:if test="${news eq null}">
			${nonews}
		</c:if>
	</div>

</form>
