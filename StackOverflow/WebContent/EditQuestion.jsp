<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Edit Question</title>
</head>
<body>
<form action='EditQuestion' method='post'>
        <table>
        <tr><td>Question Title:</td><td><input type='text' name='title' value="${editentry.title}"></td></tr>
        <tr><td>Question:</td><td><textarea name='question' rows='5' cols='60'>${editentry.question}</textarea></td></tr>
        <tr><td><input type='hidden' name='postedby' value='${user.username}' /></td></tr>
        <tr><td><input type='hidden' name='qno' value='${editentry.questionNumber}' /></td></tr>
        <tr><td>Tags:</td><td><input type='text' name='tag' value="${tags}"></td></tr>
        <tr><td colspan='2'><input type='submit' name='submit' value='Post Question' /></td></tr>
        </table>
        </form>
</body>
</html>