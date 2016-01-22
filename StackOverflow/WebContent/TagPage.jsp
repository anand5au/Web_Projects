<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Tag Statistics</title>
<link
	href="http://s3.amazonaws.com/codecademy-content/courses/ltp/css/shift.css"
	rel="stylesheet">

<link
	href="http://s3.amazonaws.com/codecademy-content/courses/ltp/css/bootstrap.css"
	rel="stylesheet">

<link rel="stylesheet" href="main.css">
</head>
<body>
<div class="jumbotron">
	<div class="nav">
		<div class="container">
			<ul class="pull-left">
				<li><img src="images/logo.png"
					style="float: left; width: 45%; height: 45%" usemap="#logomap" /></li>

			</ul>
			<map name="logomap">
				<area shape="default" href="QuestionList" alt="">
			</map>
			<ul class="pull-right">
				<c:if test="${empty user}">
				<li><a style='float: right;' href='login'>Login</a></li>
					<li><a style='float: right;' href='Register'>New User?</a></li>
				</c:if>
				<c:if test="${not empty user}">
					<li><span style='float: right;'>Hi ${user.username}</span></li>
					<li><a style='float: right;' href='logout'>Logout</a></li>
				</c:if>
			</ul>
		</div>
	</div>

	<div class="container">
		<div class="heading">
		<div class="row">
			<div class="col-md-4">Tag</div>
			<div class="col-md-4">Count</div>
		</div>
		</div>
		<br/>
		<c:forEach var="entry" items="${tagStat}" varStatus="status">
			<div class="row">
				<div class="col-md-4">${entry.key}</div>
				<div class="col-md-4">${entry.value}</div>
			</div>
			<br/>
		</c:forEach>
	</div>
	<div class="nav">
		<ul>
			<li><a href='QuestionList'
				style='float: left; margin-left: 150px'>Back to Questions</a></li>
		</ul>
	</div>
	</div>
</body>
</html>