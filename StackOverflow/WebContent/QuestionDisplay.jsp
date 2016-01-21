<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Question and Answers</title>
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
	<c:forEach items="${entries}" var="entry">
		<a href='QuestionList' style='text-align: left;'
			id="${entry.questionNumber}">Back to Questions</a>
		<br />
		<c:if test="not empty ${users}">
			<a style='text-align: left;' href='logout'>Logout</a>
			<br />
		</c:if>
		<c:if test="${user.username == entry.postedby}">
			<a style='text-align: left;'
				href='EditQuestion?id=${entry.questionNumber}'>Edit Question</a>
			<br />
		</c:if>
		<table border='1' width='800'>
			<caption>
				StackOverFlow - ${entry.title}<br />
			</caption>
			<tr>
				<td style='text-align: right;'>Posted by ${entry.postedby} at
					${entry.posttime}</td>
			</tr>
			<tr>
				<td style='text-align: left;'>${entry.question}</td>
			</tr>
		</table>
		<c:if test="${not empty ansentries}">
			<c:forEach items="${ansentries}" var="ansentry">
				<c:if test="${ansentry.title == entry.title}">
					<br />
					<b>Answers:</b>
					<br />
					<c:if test="${ansentry.posted == user.username}">

						<a style='text-align: left;'
							href='EditAnswer?aid=${ansentry.answerId}&atitle=${ansentry.title}'>Edit
							Answer</a>
						<c:if test='${ansentry.accepted == "no"}'>
				 | <a
								href='ChangeAnswerState?aid=${ansentry.answerId}&atitle=${ansentry.title}'>Mark
								Accepted</a>
						</c:if>
					</c:if>
					<br />
					<table border='1' width='800'>
						<c:if test='${ansentry.accepted=="yes"}'>
							<tr>
								<td style="color: green; text-align: center;"><b>Accepted
										Answer</b></td>
							</tr>
						</c:if>
						<tr>
							<td style="text-align: right;">Posted by ${ansentry.posted}
								at ${ansentry.posttime}</td>
						</tr>
						<tr>
							<td>${ansentry.answer}</td>
						</tr>
						<tr>
							<td>Positive Votes : ${ansentry.positiveVoteCount}</td>
						</tr>
						<tr>
							<td>Negative Votes : ${ansentry.negativeVoteCount}</td>
						</tr>
						<c:if test="not empty ${users}">
							<c:if test="${ansentry.posted!=user.username}">
								<tr>
									<td><a
										href='VoteServlet?vtype=positive&answerId=${ansentry.answerId}'
										style='color: green;'>Vote Positive</a></td>
								</tr>
								<tr>
									<td><a
										href='VoteServlet?vtype=negative&answerId=${ansentry.answerId}'
										style='color: red;'>Vote Negative</a></td>
								</tr>
							</c:if>
						</c:if>
					</table>
				</c:if>
			</c:forEach>
		</c:if>
		<c:if test="${not empty users}">
			<br />
			<b>Submit an Answer:</b>
			<form action='QuestionDisplay' method='post'>
				<input type='hidden' name='anstitle' value="${entry.title}" />
				<table border='1' width='800'>
					<tr>
						<td><input type='hidden' name='postby'
							value="${user.username}" /></td>
					</tr>
					<tr>
						<td>Your Answer:</td>
						<td><textarea name='answer' rows='5' cols='60'></textarea></td>
					</tr>
					<tr>
						<td><input type='submit' value='Submit' name='submit' /></td>
					</tr>
				</table>
			</form>
		</c:if>
		<br />
		<br />
		<br />
		<br />
		<br />
		<br />
		<br />
		<br />
		<br />
		<br />
		<br />
		<br />
		<br />
		<br />
		<br />
		<br />
		<br />
		<br />
		<br />
		<br />
		<br />
		<br />
		<br />
		<br />
		<br />
		<br />
		<br />
		<br />
		<br />
		<br />
	</c:forEach>
</body>
</html>