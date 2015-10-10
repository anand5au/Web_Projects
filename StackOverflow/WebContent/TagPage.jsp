<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Tag Page</title>
</head>
<body>
<a href='QuestionList' style='text-align: left;'>Back to Questions</a><br />
<table border="1" width='500' align="center">
<caption>Tag Statistics</caption>
<tr><th>Tag</th><th>Count</th></tr>
<c:forEach var="entry" items="${tagStat}" varStatus="status">
<tr>          
<td>${entry.key}</td>
<td>${entry.value}</td>
</tr>
</c:forEach>
</table>
</body>
</html>