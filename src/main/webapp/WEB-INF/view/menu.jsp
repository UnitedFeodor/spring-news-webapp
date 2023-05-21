<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ include file="/WEB-INF/view/localization/localizationBase.jsp" %>

<div class="menu-wrapper">
	<div class="menu-title-wrapper">
		<div class="menu-title">
		      ${menu_title}
		</div>
	</div>

	<div class="list-menu-invisible-wrapper">
		<div class="list-menu-wrapper" style="float: right;">
			<ul style="text-align: left;">
				<li style="padding-left: 15px;">
					<a href="/news/newslist">${list_news}</a><br />
				</li>

				<sec:authorize access="hasAuthority('admin')">
<%--				<c:if test="${sessionScope.role eq 'admin'}">--%>
					<li style="padding-left: 15px;">
				
				    <a href="/news/add">${list_add}</a>
                
                   <br />
					
					</li>
<%--				</c:if>--%>
				</sec:authorize>
			</ul>
		</div>
		<div class="clear"></div>
	</div>
	<div style="height: 25px;"></div>
</div>

