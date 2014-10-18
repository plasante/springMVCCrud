<%@ page isELIgnored="false" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="springMVC-tag" prefix="springMVC" %>

<springMVC:mainpage>
	<h2><spring:message code="h2.body_book" /></h2>
	<p style="color:green; font-weight: bold;">
		<spring:message code="label.addstudent_msg" /> <a href="<c:url value='/students/new' />">
		<img src="<c:url value='/resources/images/vcard_add.png' />" 
						 title='<spring:message code="imgTitle.addStudent" />'></a>
	</p>
	<c:url var="action" value="/students" ></c:url>
	<form:form method="post" action="${action}" commandName="student" cssClass="bookForm" modelAttribute="student">
		<table>
		    <c:if test="${!empty student.id}">
		    <tr>
		        <td>
		            <form:label path="id" cssClass="entityLabel">
		                <spring:message code="label.studentId" />
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
		            <form:label path="studentName" cssClass="entityLabel">
		                <spring:message code="label.studentName" />
		            </form:label>
		        </td>
		        <td>
		            <form:input path="studentName" />
		            <form:errors path="studentName" cssClass="error" />
		        </td>
		    </tr>
		    <tr>
		    	<td>
		            <form:label path="studentCourses" cssClass="entityLabel">
		                <spring:message code="label.studentCourses" />
		            </form:label>
		        </td>
		        <td>
		        	<form:select path="studentCourses">
						<c:forEach items="${courseMap}" var="course">
							<c:set var="found" value="false"/>
							<c:forEach items="${student.studentCourses}" var="studentCourse">
								<c:if test="${studentCourse.course.courseName eq course.value}">
									<c:set var="found" value="true"/>
								</c:if>
							</c:forEach>
							<c:choose>
								<c:when test="${found eq true}">
									<option value="${course.key}" selected>${course.value}</option>
								</c:when>
								<c:otherwise>
									<option value="${course.key}">${course.value}</option>
								</c:otherwise>
							</c:choose>
						</c:forEach>
					</form:select>
		            <form:errors path="studentCourses" cssClass="error" />
		        </td>
		    </tr>
		    <tr>
		        <td colspan="2">
		            <c:if test="${!empty student.studentName}">
		                <input type="submit" value="<spring:message code="label.editstudent"/>" />
		            </c:if>
		            <c:if test="${empty student.studentName}">
		                <input type="submit" value="<spring:message code="label.addstudent"/>" />
		            </c:if>
		        </td>
		    </tr>
		</table>
	</form:form>
	<h3><spring:message code="h3.list_of_students" /></h3>
	<c:if test="${!empty studentList}">
		<table class="entityTable">
			<tr>
				<th width="180"><spring:message code="label.studentName" /></th>
			</tr>
			<c:forEach items="${studentList}" var="student">
			<tr>
				<td>
					<a href="<c:url value='/students/edit/${student.id}' />" >${student.studentName}</a>
				</td>
				<td><img src="<c:url value='/resources/images/vcard_delete.png' />" 
						 title='<spring:message code="title_delete_student" />'
						 onclick="javascript:deleteEntity(${student.id})" />
						 <a href="<c:url value='/students/edit/${student.id}'/>" >
						 <img src="<c:url value='/resources/images/vcard_edit.png'/>" 
						 				  title='<spring:message code="imgTitle.addStudent" />'/></a>
				</td>
			</tr>
			</c:forEach>
		</table>
	</c:if>
</springMVC:mainpage>