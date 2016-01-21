<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Edit Answer</title>
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
	<form action='EditAnswer' method='post'>
		<table border='1' width='800'>
			<caption>Question Title : ${editansentry.title}</caption>
			<tr>
				<td><input type='hidden' name='postby' value="${user.username}" /></td>
			</tr>
			<tr>
				<td><input type='hidden' name='id'
					value="${editansentry.answerId}" /></td>
			</tr>
			<tr>
				<td>Your Answer:</td>
				<td><textarea name='answer' rows='5' cols='60'>${editansentry.answer}</textarea></td>
			</tr>
			<tr>
				<td><input type='submit' value='Submit' name='submit' /></td>
			</tr>
		</table>
	</form>
</body>
</html>