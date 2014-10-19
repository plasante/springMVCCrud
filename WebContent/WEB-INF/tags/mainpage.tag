<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
    <title>Spring 3 MVC Hibernate - Book CRUD</title>
    <link href="<c:url value="/resources/css/main.css" />" rel="stylesheet">
    <script src="<c:url value="/resources/js/main.js" />"></script>
    <script src="<c:url value="/resources/js/jquery-1.11.1.min.js" />"></script>
</head>
<body>
	<c:if test="${!empty notFoundMsg}">
		<font color="red">
			<b>
				<spring:message code="${notFoundMsg}"/>
			</b>
		</font>
	</c:if>
	<jsp:doBody/>
</body>
</html>