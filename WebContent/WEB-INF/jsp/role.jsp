<%@ page isELIgnored="false" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="springMVC-tag" prefix="springMVC" %>

<springMVC:mainpage>
	<h2><spring:message code="h2.body_book" /></h2>
	<p style="color:green; font-weight: bold;">
		<spring:message code="label.addrole_msg" /> <a href="<c:url value='/roles/new' />">
		<img src="<c:url value='/resources/images/vcard_add.png' />" 
						 title='<spring:message code="imgTitle.addRole" />'></a>
	</p>
	<c:url var="action" value="/roles" ></c:url>
	<form:form method="post" action="${action}" commandName="role" cssClass="bookForm" modelAttribute="role">
		<table>
		    <c:if test="${!empty role.id}">
		    <tr>
		        <td>
		            <form:label path="id" cssClass="entityLabel">
		                <spring:message code="label.roleId" />
		            </form:label>
		        </td>
		        <td>
		            <form:input path="id" readonly="true" size="8"  disabled="true" />
		            <form:hidden path="id" />
		        </td>
		    </tr>
			</c:if>
			<tr>
		        <td>
		            <form:label path="roleName" cssClass="entityLabel">
		                <spring:message code="label.roleName" />
		            </form:label>
		        </td>
		        <td>
		            <form:input path="roleName" />
		            <form:errors path="roleName" cssClass="error" />
		        </td>
		    </tr>
		    <tr>
		        <td>
		            <form:label path="privileges" cssClass="entityLabel">
		                <spring:message code="label.privileges" />
		            </form:label>
		        </td>
		        <td>
		        	<form:select path="privileges">
						<c:forEach items="${privilegeMap}" var="priv">
							<c:set var="found" value="false"/>
							<c:forEach items="${role.privileges}" var="rolePriv">
								<c:if test="${rolePriv.privilegeName eq priv.value}">
									<c:set var="found" value="true"/>
								</c:if>
							</c:forEach>
							<c:choose>
								<c:when test="${found eq true}">
									<option value="${priv.key}" selected>${priv.value}</option>
								</c:when>
								<c:otherwise>
									<option value="${priv.key}">${priv.value}</option>
								</c:otherwise>
							</c:choose>
						</c:forEach>
					</form:select>
		            <form:errors path="privileges" cssClass="error" />
		        </td>
		    </tr>
		    <tr>
		        <td colspan="2">
		            <c:if test="${!empty role.roleName}">
		                <input type="submit" value="<spring:message code="label.editrole"/>" />
		            </c:if>
		            <c:if test="${empty role.roleName}">
		                <input type="submit" value="<spring:message code="label.addrole"/>" />
		            </c:if>
		        </td>
		    </tr>
		</table>
	</form:form>
	<h3><spring:message code="h3.list_of_roles" /></h3>
	<c:if test="${!empty roleList}">
		<table class="entityTable">
			<tr>
				<th width="180"><spring:message code="label.roleName" /></th>
			</tr>
			<c:forEach items="${roleList}" var="role">
			<tr>
				<td>
					<a href="<c:url value='/roles/edit/${role.id}' />" >${role.roleName}</a>
				</td>
				<td><a href="<c:url value='/roles/delete/${role.id}'/>" >
			        <img src="<c:url value='/resources/images/vcard_delete.png'/>" 
							  title='<spring:message code="title_delete_role" />'/></a>
					<a href="<c:url value='/roles/edit/${role.id}'/>" >
					<img src="<c:url value='/resources/images/vcard_edit.png'/>" 
							  title='<spring:message code="imgTitle.addRole" />'/></a>
				</td>
			</tr>
			</c:forEach>
		</table>
	</c:if>
</springMVC:mainpage>