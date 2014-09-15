<%@ page isELIgnored="false" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="springMVC-tag" prefix="springMVC" %>

<springMVC:mainpage>
	<h2><spring:message code="h2.body_book" /></h2>
	<p style="color:green; font-weight: bold;">
		<spring:message code="label.addbook_msg" /> <a href="<c:url value='/home' />">
		<img src="<c:url value='/resources/images/vcard_add.png' />" title="Add a New Book"></a>
	</p>
	<c:url var="action" value="/book/add.html" ></c:url>
	<form:form method="post" action="${action}" commandName="book" cssClass="bookForm" modelAttribute="book">
		<table>
		    <c:if test="${!empty book.bookName}">
		    <tr>
		        <td>
		            <form:label path="id" cssClass="entityLabel">
		                <spring:message code="label.bookId" />
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
		            <form:label path="bookName" cssClass="entityLabel">
		                <spring:message code="label.bookName" />
		            </form:label>
		        </td>
		        <td>
		            <form:input path="bookName" />
		            <form:errors path="bookName" cssClass="error" />
		        </td>
		    </tr>
		    <tr>
		        <td>
		            <form:label path="author" cssClass="entityLabel">
		                <spring:message code="label.author" />
		            </form:label>
		        </td>
		        <td>
		        	<springMVC:select/>
		            <form:errors path="author" cssClass="error" />
		        </td>
		    </tr>
		    <tr>
		        <td>
		            <form:label path="price" cssClass="entityLabel">
		                <spring:message code="label.price" />
		            </form:label>
		        </td>
		        <td>
		            <form:input path="price" />
		            <form:errors path="price" cssClass="error" />
		        </td>
		    </tr>
		    <tr>
		        <td>
		            <form:label path="quantity" cssClass="bookLabel">
		                <spring:message code="label.qty" />
		            </form:label>
		        </td>
		        <td>
		            <form:input path="quantity" />
		            <form:errors path="quantity" cssClass="error" />
		        </td>
		    </tr>
		    <tr>
		        <td colspan="2">
		            <c:if test="${!empty book.bookName}">
		                <input type="submit"
		                    value="<spring:message code="label.editbook"/>" />
		            </c:if>
		            <c:if test="${empty book.bookName}">
		                <input type="submit"
		                    value="<spring:message code="label.addbook"/>" />
		            </c:if>
		        </td>
		    </tr>
		</table>    
	</form:form>
	<h3><spring:message code="h3.list_of_books" /></h3>
	<c:if test="${!empty bookList}">
		<table class="bookTable">
			<tr>
				<th width="180"><spring:message code="label.bookName" /></th>
				<th width="160"><spring:message code="label.author" /></th>
				<th width="60"><spring:message code="label.price" /></th>
				<th width="80"><spring:message code="label.qty" /></th>
				<th width="60"><spring:message code="label.action" /></th>
			</tr>
			<c:forEach items="${bookList}" var="book">
			<tr>
				<td>
					<a href="<c:url value='/edit/${book.id}' />" >${book.bookName}</a>
				</td>
				<td>${book.author.authorName}</td>
				<td>${book.price}
				<td>${book.quantity}</td>
				<td><img src="<c:url value='/resources/images/vcard_delete.png' />" 
						 title="Delete Book"
						 onclick="javascript:deleteBook(${book.id})" />
					<a href="<c:url value='/edit/${book.id}' />" >
						<img src="<c:url value='/resources/images/vcard_add.png'/>" 
							 title="Edit Book" />
					</a>
				</td>
			</tr>
			</c:forEach>
		</table>
	</c:if>
</springMVC:mainpage>
