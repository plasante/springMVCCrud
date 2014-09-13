<%@ page isELIgnored="false" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="springMVC-tag" prefix="springMVC" %>

<springMVC:mainpage>
	<h3><spring:message code="h3.list_of_roles" /></h3>
	<c:if test="${!empty roleList}">
		<table class="entityTable">
			<tr>
				<th width="180"><spring:message code="label.roleName" /></th>
			</tr>
			<c:forEach items="${roleList}" var="role">
			<tr>
				<td>
					<a href="<c:url value='/role/edit/${role.id}' />" >${role.roleName}</a>
				</td>
				<td><img src="<c:url value='/resources/images/vcard_delete.png' />" 
						 title='<spring:message code="title_delete_role" />'
						 onclick="javascript:deleteEntity(${role.id})" />
						 <a href="<c:url value='/edit/${role.id}'/>" >
						 <img src="<c:url value='/resources/images/vcard_add.png'/>" /></a>
				</td>
			</tr>
			</c:forEach>
		</table>
	</c:if>
</springMVC:mainpage>