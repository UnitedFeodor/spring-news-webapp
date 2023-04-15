<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ include file="/WEB-INF/view/localization/localizationBase.jsp" %>

<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<div class="body-title">
	<a href="/news/newslist">${goback_news} </a> ${addnews_goback_current}
</div>

<div class="add-table-margin">
    <form action="/news/add/${news.id}" method="post">
        <table class="news_text_format">
            <tr>
                <td class="space_around_title_text">${title}</td>

                <td class="space_around_view_text"><div class="word-breaker">
                    <input type="text" name="news_title" value="<c:out value="${news.title }"/>"/>

                </div></td>
            </tr>
            <tr>
                <td class="space_around_title_text">${date}</td>

                <td class="space_around_view_text"><div class="word-breaker">
                         <input type="text" name="news_date" value="<tags:localDate date="${news.dateAdded}"/>"/>

                    </div></td>
            </tr>
            <tr>
                <td class="space_around_title_text">${brief}</td>
                <td class="space_around_view_text"><div class="word-breaker">
                    <textarea rows="7" cols="30" name="news_brief" style="resize: none;"><c:out value="${news.brief }" /></textarea>

                </div></td>
            </tr>
            <tr>
                <td class="space_around_title_text">${content}</td>
                <td class="space_around_view_text"><div class="word-breaker">
                    <textarea rows="11" cols="30" name="news_content" style="resize: none;"><c:out value="${news.content }" /></textarea>

                    </div></td>
            </tr>
        </table>

        <div style="margin-left: 120px; margin-bottom: 10px">
            <input type="submit" value="${save}" />
        </div>
    </form>

    <div style="margin-left: 120px" >
        <form action="/news/newslist" method="get">
             <input type="submit" value="${cancel}" />
        </form>
    </div>
</div>
<%-- </c:if> --%>
