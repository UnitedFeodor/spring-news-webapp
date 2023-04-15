<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ include file="/WEB-INF/view/localization/localizationBase.jsp" %>

<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<div class="body-title">
	<a href="/news/newslist">${goback_news} </a> ${addnews_goback_current}
</div>

<div class="add-table-margin">
    <form:form action="/news/add" modelAttribute="news" method="post">
        <table class="news_text_format">
            <tr>
                <td class="space_around_title_text">${news_title}</td>

                <td class="space_around_view_text"><div class="word-breaker">
                    <form:input type="text" path="title" name="news_title" value="${title}"/>

                </div></td>
            </tr>
            <tr>
                <td class="space_around_title_text">${date}</td>

                <td class="space_around_view_text"><div class="word-breaker">
                         <form:input path="dateAdded" type="text" name="news_date" />

                    </div></td>
            </tr>
            <tr>
                <td class="space_around_title_text">${brief}</td>
                <td class="space_around_view_text"><div class="word-breaker">
                    <form:textarea path="brief" rows="7" cols="30" name="news_brief" style="resize: none;" />

                </div></td>
            </tr>
            <tr>
                <td class="space_around_title_text">${content}</td>
                <td class="space_around_view_text"><div class="word-breaker">
                    <form:textarea path="content" rows="11" cols="30" name="news_content" style="resize: none;"/>

                    </div></td>
            </tr>
        </table>

        <div style="margin-left: 120px; margin-bottom: 10px">
            <input type="submit" value="${save}" />
        </div>
    </form:form>

    <div style="margin-left: 120px" >
        <form action="/news/newslist" method="get">
             <input type="submit" value="${cancel}" />
        </form>
    </div>
</div>
<%-- </c:if> --%>
