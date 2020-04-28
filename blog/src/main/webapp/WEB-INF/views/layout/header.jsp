<%--
  Created by IntelliJ IDEA.
  User: binizmohamed
  Date: 4/7/20
  Time: 20:07
  To change this template use File | Settings | File Templates.
--%>

      <!-- Static navbar -->
   <header>
      <nav class="navbar navbar-default">
        <div class="container-fluid">
          <div id="navbar" class="navbar-collapse collapse">
            <ul class="nav navbar-nav">
              <li class="active"><a href="${pageContext.request.contextPath}/article/">Article</a></li>
              <li><a href="${pageContext.request.contextPath}/tag/">Tag</a></li>
              <li><a href="${pageContext.request.contextPath}/user/">User</a></li>
              <li><a href="${pageContext.request.contextPath}/user/logout/">Logout</a></li>
            </ul>
          </div><!--/.nav-collapse -->
        </div><!--/.container-fluid -->
      </nav>
  </header>