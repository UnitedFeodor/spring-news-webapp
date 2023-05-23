<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ include file="/WEB-INF/view/localization/localizationBase.jsp" %>

<div class="body-title">
    <a href="/news/newslist">${goback_news} </a> ${error_goback}
</div>

<h1>${error_message}</h1>


<div class="body-title">
    <h2>
        <c:out value="${error}" />
    </h2>

</div>