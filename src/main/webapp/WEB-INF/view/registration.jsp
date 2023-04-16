
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>

<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<%-- <%@include jsp directive here for localization vars import in a different file --%>
<%@ include file="/WEB-INF/view/localization/localizationBase.jsp" %>
<html>
<head>
    <title></title>
</head>
<body>
<div class="body-title">
    <a href="home">${goback_news} </a> ${header_registration_link}
</div>
<div class="add-table-margin">
    <form:form action="signup" modelAttribute="user" method="post">
        <table class="news_text_format">
            <tr>
                <td class="space_around_title_text2">${header_logination_login}</td>
                
                <td  class="space_around_view_text2">
                    <form:input type="text" path="login"/> <br>
                </td>

            </tr>
            <tr>
                <td class="space_around_title_text2">
                    ${header_logination_password}
                </td>
                <td  class="space_around_view_text2">
                    <form:input type="text" path="password"/> <br>
                </td>
            </tr>
            <tr>
                <td class="space_around_title_text2">
                    <label for="roles">${registration_role}</label>
                </td>
                <td class="space_around_view_text2">
                    <form:select path="role.id" id="roles">
                        <form:option value="1">${registration_role_user}</form:option>
                        <form:option value="2">${registration_role_admin}</form:option>
                    </form:select>
                </td>
            </tr>
            <br><br>

            <tr>
                <td class="space_around_title_text2">
                    ${registration_name}
                </td>
                <td class="space_around_view_text2">
                    <form:input  type="text" path="userDetails.name"/> <br>
                </td>
            </tr>
            <tr>
                <td class="space_around_title_text2">
                 ${registration_surname}
                </td>
                <td class="space_around_view_text2">
                <form:input  type="text" path="userDetails.surname"/> <br>
                </td>
            </tr>
            <tr>
                <td class="space_around_title_text2">
                    ${registration_birthday}
                </td>
                <td class="space_around_view_text2">
                    <form:input  type="text" path="userDetails.birthday"/> <br>
                </td>
            </tr>

            <tr>
                <td class="space_around_title_text2">

                </td>
                <td class="space_around_view_text2" >
                    <input type="submit" value="${save}" style="margin-left: 70px"/>
                </td>
            </tr>

        </table>

        <!--<a href="controller?command=do_change_language"> en </a> &nbsp;&nbsp; -->
    </form:form>
</div>
</body>
</html>
