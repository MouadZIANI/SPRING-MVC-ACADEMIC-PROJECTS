<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Insert title here</title>
		<title>Register success</title>
		<link href="<c:url value="/resources/css/bootstrap.min.css" />" rel="stylesheet">
		<script src="<c:url value="/resources/js/jquery-1.11.1.min.js" />"></script>
		<script src="<c:url value="/resources/js/bootstrap.min.js" />"></script>
	</head>
	<body>
		<div class="container">
			  <div class="col-md-offset-2 col-md-7">
			   <h1>${message}</h1>
			
			   <table class="table table-striped table-bordered">
				   <tr>
				   		<td><b>UserName </b> : ${user.userName}</td>
				   </tr>
				   <tr>
				   		<td><b>Email </b>: ${user.email}</td>
				   </tr>
				   <tr>
				   		<td><b>Password </b> : ${user.password}</td>
				   </tr>
			   </table>
			  </div>
		 </div>
	</body>
</html>
