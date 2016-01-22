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
					<div class="col-md-3">Question</div>
					<div class="col-md-3">PostedBy</div>
					<div class="col-md-3">PostedDate</div>
					<div class="col-md-3">Tags</div>
				</div>
			</div>
			<br />
			<c:forEach items="${tagEntries}" var="entry">
				<div class="row">
					<div class="col-md-3">
						<a href="QuestionDisplay#${entry.questionNumber}">${entry.title}</a>
					</div>
					<div class="col-md-3">${entry.postedby}</div>
					<div class="col-md-3">${entry.posttime}</div>
					<div class="col-md-3">
						<c:forEach items="${entry.tagList}" var="tag">
							<a href="HandleTag?tagName=${tag}">${tag}</a>
						</c:forEach>
					</div>
				</div>
				<br />
			</c:forEach>
			<div class="nav">
				<ul>
					<li><a style='float: left; margin-left: 150px'
						href='AddQuestion'>Post a Question</a></li>
					<li><a style='float: left; margin-left: 30px'
						href="TagPage.jsp">Tag Statistics</a></li>
				</ul>
			</div>
		</div>
	</div>
</body>
</html>