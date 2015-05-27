<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@page session="false"%>

<html>
<head>
<title><spring:message code="uploadXML.title" /></title>
</head>
<body>
	<spring:message code="uploadXML.language"/>
	:
	<a href="?lang=en"><spring:message code="uploadXML.english"/></a>
	|
	<a href="?lang=uk_UA"><spring:message code="uploadXML.ukrainian"/></a>
	<h1>
		<spring:message code="uploadXML.title"/>
	</h1>

	<form:form method="post" action="uploadXML" commandName="uploadedFile" 
		enctype="multipart/form-data" modelAttribute="uploadedFile">
		<table>
			<tr>
				<td><spring:message code="uploadXML.title"/>:</td>
				<td><input type="file" name="file" accept="application/xml"></td>
				<td><form:errors path="file" cssStyle="color:red"/></td>  
			</tr>
			<tr>
				<td colspan="2"><input type="submit"
					value=<spring:message code="uploadXML.submit"/>>
				</td>
			</tr>
		</table>
	</form:form>
	
	<a href="cdcatalog"><spring:message code="viewing.cdatalog"/></a>
</body>
</html>
