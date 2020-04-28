<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>Blog</title>
    <link href="<c:url value="/resources/css/bootstrap.min.css" />" rel="stylesheet" />
    <link href="<c:url value="/resources/css/style.css" />" rel="stylesheet" />
    <script src="<c:url value="/resources/js/jquery-1.11.1.min.js" />"></script>
    <script src="<c:url value="/resources/js/bootstrap.min.js" />"></script>
</head>
<body>
<div class="container">
    <jsp:directive.include file="../layout/header.jsp" />
 <header class="col-lg-12">
    <h1 class="text-center">Tous les utilisateurs du Blog</h1>

        <div class="row">
       		<div class="col-lg-12 AddTagLink">
	            <a href="${pageContext.request.contextPath}/user/add" class="btn btn-primary">ajouter un utilisateur
	            </a>
	            <br/>
	        </div>
       	</div>
        <div class="row">
        	<div class="col-md-6 col-md-offset-3">
        		<table class="table table-bordered">
		            <tr>
		                <th>Id</th>
		                <th>Email</th>
		                <th>created</th>
		                <th>Action</th>
		
		            </tr>
		            <c:forEach items="${pageable.content}" var="item">
		                <tr>
		                    <td>${item.id}</td>
		                    <td>${item.email}</td>
		                    <td><fmt:formatDate type = "date" value = "${item.created}" /> </td>
		                    <td>
		                        <a href="${pageContext.request.contextPath}/user/delete/${pageable.number}/${item.id}" class="btn btn-danger"
		                           onclick="if (!(confirm('Vous Voulez supprimer cet elementr?'))) return false">Supprimer</a>
		                        <a href="${pageContext.request.contextPath}/user/add/${item.id}" class="btn btn-success">Modifier</a>
		                    </td>
		                </tr>
		
		            </c:forEach>
      			  </table>
        	</div>
        </div>
	<div class="row">
        	<div class="col-md-6 col-md-offset-3 text-center">
     			<nav aria-label="Page navigation example">
			        <ul class="pagination">
			            <c:choose>
			                <c:when test="${pageable.number !=0 }">
			                    <li class="page-item"><a class="page-link" href="${pageContext.request.contextPath}/user/page/${pageable.number-1 }">Previous</a></li>
			                </c:when>
			            </c:choose>
			            <c:forEach begin="0"   end="${pageable.totalPages -1}" var="i">
			                <c:choose>
			                    <c:when test="${pageable.number ==i }">
			                        <li class="page-item disabled"><a class="page-link" href="${pageContext.request.contextPath}/user/page/${i}">${i}</a></li>
			                    </c:when>
			                    <c:otherwise>
			                        <li class="page-item"><a class="page-link" href="${pageContext.request.contextPath}/user/page/${i}">${i}</a></li>
			                    </c:otherwise>
			                </c:choose>
			            </c:forEach>
			            <c:choose>
			                <c:when test="${pageable.number <pageable.totalPages-1 }">
			                    <li class="page-item"><a class="page-link" href="${pageContext.request.contextPath}/user/page/${pageable.number+1 }">Next</a></li>
			                </c:when>
			            </c:choose>
			        </ul>
			     </nav>
     			
     		</div>
     	</div>


 </header>

</div>

</body>
</html>