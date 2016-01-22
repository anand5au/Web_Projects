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
	<div class="jumbotron" style="height: 8000px">
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

		<c:forEach items="${entries}" var="entry">
			<div class="nav">
				<ul class="pull-left">
					<li><a style='float: left; margin-left: 150px'
						href='QuestionList' id="${entry.questionNumber}">Back to
							Questions</a></li>

					<c:if test="${user.username == entry.postedby}">
						<li><a style='float: left; margin-left: 150px'
							href='EditQuestion?id=${entry.questionNumber}'>Edit Question</a></li>
					</c:if>
				</ul>
			</div>

			<div class="container">
				<div class="heading">
					<div class="row">
						<div class="col-md-8">TechHelp - ${entry.title}</div>
					</div>
				</div>
				<br />
				<div class="row">
					<div class="col-md-8">
						<span style="text-align: center;">${entry.question}</span>
					</div>
				</div>
				<br />
				<div class="row">
					<div class="col-md-8">
						<span style="text-align: center; font-style: italic;">Posted
							by ${entry.postedby} at ${entry.posttime}</span>
					</div>
				</div>
				<br />
			</div>
			
			<c:if test="${not empty ansentries}">
			<div class="container">
			<div class="row">
			<div class="col-md-8" style="font-weight: bold; font-size: 18px">Answers:</div>
			</div></div>
				<c:forEach items="${ansentries}" var="ansentry">
				<div class="container">
					<c:if test="${ansentry.title == entry.title}">
						<c:if test="${ansentry.posted == user.username}">
						<div class="nav">
						<div class="row">
						<div class="col-md-4" >
							<a style='text-align: center;font-size: 12px' href='EditAnswer?aid=${ansentry.answerId}&atitle=${ansentry.title}'>Edit
								Answer</a></div>
							<c:if test='${ansentry.accepted == "no"}'>
				 			<div class="col-md-4" style='text-align: center;font-size: 12px'><a style='text-align: center;font-size: 12px'
									href='ChangeAnswerState?aid=${ansentry.answerId}&atitle=${ansentry.title}'>Mark
									Accepted</a></div>
							</c:if>
							</div>
							</div>
						</c:if>
						<div class="row">
						<div class="col-md-4">
							<c:if test='${ansentry.accepted=="yes"}'>
									 <span style="color: green; text-align: center;">Accepted
											Answer</span>
							</c:if>
							</div>
								<div class="col-md-4" >Posted by ${ansentry.posted}
									at ${ansentry.posttime}</div>
						</div>
						<br/>
							<div class="row">
								<div class="col-md-8">${ansentry.answer}</div>
							</div>
							<div class="row">
								<div class="col-md-4">Positive Votes : ${ansentry.positiveVoteCount}</div>
								<div class="col-md-4">Negative Votes : ${ansentry.negativeVoteCount}</div>
							</div>
							<br/>
							<c:if test="not empty ${users}">
								<c:if test="${ansentry.posted!=user.username}">
									<div class="row">
										<div class="col-md-4"><a
											href='VoteServlet?vtype=positive&answerId=${ansentry.answerId}'
											style='color: green;'>Vote Positive</a></div>
										<div class="col-md-4"><a
											href='VoteServlet?vtype=negative&answerId=${ansentry.answerId}'
											style='color: red;'>Vote Negative</a></div>
									</div>
								</c:if>
							</c:if>
					</c:if>
					</div>
					<br/>
				</c:forEach>
			</c:if>

			<c:if test="${not empty users}">
				<br />
				<form action='QuestionDisplay' method='post'>
					<input type='hidden' name='anstitle' value="${entry.title}" />
					<div class="container">
						<div class="row">
							<div class="col-md-4" style="font-weight: bold">Submit an
								Answer:</div>
						</div>
						<br />
						<div class="row">
							<div class="col-md-4">
								<input type='hidden' name='postby' value="${user.username}" />
							</div>
						</div>
						<br />
						<div class="row">
							<div class="col-md-2">Your Answer:</div>
							<div class="col-md-2">
								<textarea style="float: left; margin-left: 0px;" name='answer'
									rows='5' cols='30'></textarea>
							</div>
						</div>
						<br />
						<div class="row">
							<div class="col-md-2"></div>
							<div class="col-md-2">
								<input type='submit' value='Submit' name='submit' />
							</div>
						</div>
					</div>
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
			<br />
			<br />
			<br />
			<br />
			<br />
			<br />
			<br />
		</c:forEach>
	</div>
</body>
</html>