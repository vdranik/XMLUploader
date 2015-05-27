<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page session="false"%>

<html>
<head>
<link media="screen" rel="stylesheet" href="<c:url value="resources/css/table.css"/>" type="text/css"/>
<title><spring:message code="cdcatalog.title" /></title>
</head>
<body>

	<h1>
		<spring:message code="cdcatalog.title" />
	</h1>

	<a href="cdcatalog/downloadFiles"><spring:message code="download.cdcatalog" /></a>
	
	<div class="CSSTableGenerator" style="width: 600px; height: 150px;">
		<table>
			<tr>
				<td><spring:message code="cdcatalog.table.title" /></td>
				<td><spring:message code="cdcatalog.table.artist" /></td>
				<td><spring:message code="cdcatalog.table.country" /></td>
				<td><spring:message code="cdcatalog.table.company" /></td>
				<td><spring:message code="cdcatalog.table.price" /></td>
				<td><spring:message code="cdcatalog.table.year" /></td>
			</tr>
			<c:forEach items="${cds}" var="cd">
				<tr>
					<td><c:out value="${cd.title}" /></td>
					<td><c:out value="${cd.artist}" /></td>
					<td><c:out value="${cd.country}" /></td>
					<td><c:out value="${cd.company}" /></td>
					<td><c:out value="${cd.price}" /></td>
					<td><c:out value="${cd.year}" /></td>
				</tr>
			</c:forEach>
		</table>
	</div>
</body>
</html>