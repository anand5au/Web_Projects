<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Question List</title>
<link
	href="http://s3.amazonaws.com/codecademy-content/courses/ltp/css/shift.css"
	rel="stylesheet">

<link
	href="http://s3.amazonaws.com/codecademy-content/courses/ltp/css/bootstrap.css"
	rel="stylesheet">

<link rel="stylesheet" href="main.css">
</head>
<body>
	<div class="nav">
		<div class="container">
			<ul class="pull-left">
				<li><img src="images/logo.png"
					style="float: left; width: 45%; height: 45%" usemap="#logomap" /></li>
			</ul>
			<map name="logomap">
				<area shape="default" href="QuestionList.html" alt="">
			</map>
			<ul class="pull-right">
				<c:if test="${empty user}">
					<li><a style='float: right;' href='Register'>Register</a></li>
				</c:if>
				<c:if test="${not empty user}">
					<li><p style='float: right;'>Hi ${user.username}</p></li>
					<li><a style='float: right;' href='logout'>Logout</a></li>
				</c:if>
			</ul>
		</div>
	</div>
	<table border="1">
		<tr>
			<th>Question</th>
			<th>PostedBy</th>
			<th>PostedDate</th>
			<th>Tags</th>
		</tr>
		<c:forEach items="${tagEntries}" var="entry">
			<tr>
				<td><a href="QuestionDisplay#${entry.questionNumber}">${entry.title}</a></td>
				<td>${entry.postedby}</td>
				<td>${entry.posttime}</td>
				<td><c:forEach items="${entry.tagList}" var="tag">
						<a href="HandleTag?tagName=${tag}">${tag}</a>
					</c:forEach></td>
			</tr>
		</c:forEach>
	</table>
	<p>
		<a href='AddQuestion'>Post a Question</a>|<a href='QuestionList'>All
			Questions</a>
	</p>

</body>
</html>