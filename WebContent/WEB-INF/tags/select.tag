<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<form:select path="author">
	<c:forEach items="${authorsMap}" var="aut">
		<c:choose>
			<c:when test="${book.author.authorName eq aut.value}">
				<option value="${aut.key}" selected>${aut.value}</option>
			</c:when>
			<c:otherwise>
				<option value="${aut.key}">${aut.value}</option>
			</c:otherwise>
		</c:choose>
	</c:forEach>
</form:select>