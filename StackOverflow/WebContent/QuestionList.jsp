<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Question List</title>
</head>
<body>
<p style="text-align:left;"><a href="TagPage.jsp">View TagPage</a></p>
<c:if test="${empty user}">
<a style='text-align: right;' href='Register'>Click here to register</a>
</c:if>
<c:if test="${not empty user}">
<p>Hi ${user.username}</p>
<a style='text-align: right;' href='logout'>Logout</a>
</c:if>
<table border="1">
<tr><th>Question</th><th>PostedBy</th><th>PostedDate</th><th>Tags</th></tr>
<c:forEach items="${entries}" var="entry">
<tr>
	<td><a href="QuestionDisplay#${entry.questionNumber}">${entry.title}</a></td>
	<td>${entry.postedby}</td>
  	<td>${entry.posttime}</td>
  	<td>
  	<c:forEach items="${entry.tagList}" var="tag" >
   		<a href="HandleTag?tagName=${tag}">${tag}</a>
   	</c:forEach>
   	</td>
   	</tr>
</c:forEach>
</table>
<p><a href='AddQuestion'>Post a Question</a></p>

</body>
</html>