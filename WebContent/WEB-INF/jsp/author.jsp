<%@ page isELIgnored="false" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="springMVC-tag" prefix="springMVC" %>

<springMVC:mainpage>
	<h2><spring:message code="h2.body_book" /></h2>
	<p style="color:green; font-weight: bold;">
		<spring:message code="label.addauthor_msg" /> <a href="<c:url value='/home' />">
		<img src="<c:url value='/resources/images/vcard_add.png' />" title='<spring:message code="label.addauthor_msg" />'></a>
	</p>
	<c:url var="action" value="/author/add.html" ></c:url>
	<form:form method="post" action="${action}" commandName="author" cssClass="bookForm">
		<table>
		    <tr>
		        <td>
		            <form:label path="authorName" cssClass="bookLabel">
		                <spring:message code="label.authorName" />
		            </form:label>
		        </td>
		        <td>
		            <form:input path="authorName" />
		            <form:errors path="authorName" cssClass="error" />
		        </td>
		    </tr>
		    <tr>
		        <td colspan="2">
		            <c:if test="${!empty author.authorName}">
		                <input type="submit"
		                    value="<spring:message code="label.editauthor"/>" />
		            </c:if>
		            <c:if test="${empty author.authorName}">
		                <input type="submit"
		                    value="<spring:message code="label.addauthor"/>" />
		            </c:if>
		        </td>
		    </tr>
		</table>
	</form:form>
	<h3><spring:message code="h3.list_of_authors" /></h3>
	<c:if test="${!empty authorList}">
		<table class="entityTable">
			<tr>
				<th width="180"><spring:message code="label.authorName" /></th>
			</tr>
			<c:forEach items="${authorList}" var="author">
			<tr>
				<td>
					<a href="<c:url value='/edit/${author.id}' />" >${author.authorName}</a>
				</td>
				<td><img src="<c:url value='/resources/images/vcard_delete.png' />" 
						 title='<spring:message code="title_delete_author" />'
						 onclick="javascript:deleteEntity(${author.id})" />
						 <a href="<c:url value='/edit/${author.id}'/>" >
						 <img src="<c:url value='/resources/images/vcard_add.png'/>" /></a>
				</td>
			</tr>
			</c:forEach>
		</table>
	</c:if>
</springMVC:mainpage>