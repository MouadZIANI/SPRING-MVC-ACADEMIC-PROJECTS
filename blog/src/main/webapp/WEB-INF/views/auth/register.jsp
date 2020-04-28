<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri = "http://www.springframework.org/tags/form" prefix = "form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<html>
<head>
    <title>Ajouter utilisateur</title>
    <link href="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
    <link href="<c:url value="/resources/css/homeStyle.css" />" rel="stylesheet" />
</head>
<body>

<div class="container">
    <div class="wrapper fadeInDown">
	  <div id="formContent">
	    <form:form method="POST" action="${pageContext.request.contextPath}/register-user/" modelAttribute="user">
	      <form:input path="email" type="text" id="login" class="fadeIn second" name="login" placeholder="email" />
	      <form:input path="password" type="text" id="password" class="fadeIn third" name="login" placeholder="password" />
	      <form:select path="roles" id="UserRole" multiple="true">
	      	<form:option value="Admin">Admin</form:option>
	      	<form:option value="ContentWriter">Content Writer</form:option>
	      </form:select>
	      <input type="submit" class="fadeIn fourth loginButton" value="Sign up">
	      <form:errors path="email" cssClass="alert-danger" />
	      <form:errors path="password" cssClass="alert-danger" />
	    </form:form>
	    <div id="formFooter">
	      <a class="underlineHover" href="${pageContext.request.contextPath}">Already have an account login.</a>
	    </div>

  </div>
	</div>
</div>
<script src="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
<script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
</body>
</html>
