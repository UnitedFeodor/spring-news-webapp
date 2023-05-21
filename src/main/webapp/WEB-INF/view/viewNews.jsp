<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ include file="/WEB-INF/view/localization/localizationBase.jsp" %>

<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<div class="body-title">
	<a href="/news/newslist">${goback_news}</a> ${viewnews_goback_current}
</div>

<div class="add-table-margin">
	<table class="news_text_format">
		<tr>
			<td class="space_around_title_text">${news_title}</td>

			<td class="space_around_view_text"><div class="word-breaker">
					<c:out value="${news.title }" />
				</div></td>
		</tr>
		<tr>
			<td class="space_around_title_text">${date}</td>

			<td class="space_around_view_text"><div class="word-breaker">
				<tags:localDate date="${news.dateAdded}"/>
				</div></td>
		</tr>
		<tr>
			<td class="space_around_title_text">${brief}</td>
			<td class="space_around_view_text"><div class="word-breaker">
					<c:out value="${news.brief }" />
				</div></td>
		</tr>
		<tr>
			<td class="space_around_title_text">${content}</td>
			<td class="space_around_view_text"><div class="word-breaker">
					<c:out value="${news.content }" />
				</div></td>
		</tr>
	</table>
</div>


<sec:authorize access="hasAuthority('admin')">
<%--<c:if test="${sessionScope.role eq 'admin'}">--%>
	<div style="margin-left: 30px; margin-bottom: 10px">
		<form action="/news/edit/${news.id}" method="get">
			<input type="submit" value="${edit}" />
		</form>
	</div>

	<div style="margin-left: 30px" >
		<form action="/news/deletenews" method="post">
			<input type="hidden"name="id" value="${news.id}" />
			<input type="submit" value="${delete}" />
		</form>
	</div>
</sec:authorize>
<%--</c:if>--%>