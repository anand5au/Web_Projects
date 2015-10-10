<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Edit Answer</title>
</head>
<body>
<form action='EditAnswer' method='post'>
        <table border='1' width='800'>
        <caption>Question Title : ${editansentry.title}</caption>
    	<tr><td><input type='hidden' name='postby' value="${user.username}" /></td></tr>
    	<tr><td><input type='hidden' name='id' value="${editansentry.answerId}" /></td></tr>
    	<tr><td>Your Answer:</td><td><textarea name='answer' rows='5' cols='60'>${editansentry.answer}</textarea></td></tr>
    	<tr><td><input type='submit' value='Submit' name='submit' /></td></tr>
    	</table>
</form>
</body>
</html>