<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Register to StackOverFlow</title>
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
		</div>
	</div>
	<form action='Register' method='post'>
		<table border='1' align='center' width='300'>
			<caption>StackOverFlow Register</caption>
			<tr>
				<td>Username: <input type='text' name='username' /></td>
			</tr>
			<tr>
				<td>Password: <input type='password' name='password' /></td>
			</tr>
			<tr>
				<td><input type='submit' name='submit' value='Register' /></td>
			</tr>
		</table>
	</form>
</body>
</html>