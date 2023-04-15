<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ include file="/WEB-INF/view/localization/localizationBase.jsp" %>

<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<div class="body-title">
	<a href="newslist">${goback_news} </a> ${newslist_goback_current}
</div>

<form action="deletenews" method="post">
	<c:forEach var="news" items="${news}">
		<div class="single-news-wrapper">
			<div class="single-news-header-wrapper">
				<div class="news-title">
					<c:out value="${news.title}" />
				</div>
				<div class="news-date">
					<tags:localDate date="${news.dateAdded}"/>
				</div>

				<div class="news-content">
					<c:out value="${news.brief}" />
				</div>
				<div class="news-link-to-wrapper">
					<div class="link-position">
						<c:if test="${sessionScope.role eq 'admin'}">
						      <a href="edit/${news.id}">${edit} </a>
						</c:if>
						<span>&nbsp&nbsp</span>
						<a href="view/${news.id}">${newslist_view} </a>
   					    
   					    <c:if test="${sessionScope.role eq 'admin'}">
   					         <input type="checkbox" name="id" value="${news.id}" />
   					    </c:if>
					</div>
				</div>

			</div>
		</div>

	</c:forEach>

    <c:if test="${(sessionScope.role eq 'admin') and (not (news eq null))}">
        <div class="delete-button-position">
            <input type="submit" value="${delete}" />
        </div>
    </c:if>

	<div class="no-news">
		<c:if test="${news eq null}">
        ${nonews}
	</c:if>
	</div>
</form>

<form name="amountForm" action="newslist" method="get" style="margin: 20px; font-size: larger; min-font-size: 14">
	<input type="hidden" name="page" value="${requestScope.page}">
	<div class="news-amount">
		<select name="count" id="count" onchange="javascript:document.amountForm.submit();">

			<option value="5" ${requestScope.count.equals("5") ? 'selected' : ''}>5</option>
			<option value="10" ${requestScope.count.equals("10") ? 'selected' : ''}>10</option>
			<option value="25" ${requestScope.count.equals("25") ? 'selected' : ''}>25</option>
		</select>
	</div>
</form>
<div class="pagination">
	<c:if test="${not (requestScope.page eq 1) }">
		<a href="newslist?page=${requestScope.page-1}"> &laquo;</a>
	</c:if>

	<c:if test="${not (requestScope.page eq 1) }" >
		<a href="newslist?page=1" > 1 </a>
	</c:if>

	<a id="selected-page"> ${requestScope.page} </a>

	<c:if test="${not (requestScope.page eq requestScope.final_page_number)}">
		<a href="newslist?page=${requestScope.final_page_number}"> ${requestScope.final_page_number}</a>
	</c:if>


	<c:if test="${not (requestScope.page eq requestScope.final_page_number) }">
		<a href="newslist?page=${requestScope.page+1}"> &raquo;</a>
	</c:if>

</div>