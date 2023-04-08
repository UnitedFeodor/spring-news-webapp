<%--
  Created by IntelliJ IDEA.
  User: fedor
  Date: 06.04.2023
  Time: 14:28
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>

<%@ include file="/WEB-INF/view/localization/localizationBase.jsp" %>

<html>
<head>
    <title>Title</title>
</head>
<body>
<div class="body-title">
    <a href="">${goback_news} </a> ${newslist_goback_current}
</div>

<form action="/news" method="post">
    <c:forEach var="news" items="${requestScope.news}">
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
                        <a href="controller?command=go_to_edit_news&idNews=${news.id}">${edit} </a>
                    </c:if>
                    <span>&nbsp&nbsp</span>
                    <a href="controller?command=go_to_view_news&idNews=${news.id}">${newslist_view} </a>

                    <c:if test="${sessionScope.role eq 'admin'}">
                        <input type="checkbox" name="idNews" value="${news.id }" />
                    </c:if>
                </div>
            </div>

        </div>
    </div>

    </c:forEach>

    <c:if test="${(sessionScope.role eq 'admin') and (not (requestScope.news eq null))}">
    <div class="delete-button-position">

        <input type="hidden" name="command" value="do_delete_news" />
        <input type="submit" value="${delete}" />

    </div>
    </c:if>

    <div class="no-news">
        <c:if test="${requestScope.news eq null}">
            ${nonews}
        </c:if>
    </div>

</form>
</body>
</html>
