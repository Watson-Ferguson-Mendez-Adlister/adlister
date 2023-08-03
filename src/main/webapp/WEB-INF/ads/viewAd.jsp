<%--
  Created by IntelliJ IDEA.
  User: robertmendez
  Date: 8/1/23
  Time: 2:13 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <jsp:include page="/WEB-INF/partials/head.jsp">
        <jsp:param name="title" value="${ad.title}"/>
    </jsp:include>
</head>
<body>
<jsp:include page="/WEB-INF/partials/navbar.jsp"/>

<div class="container">
    <h1 class="pt-5 mb-3 header-text">${ad.title}</h1>
    <p>${ad.description}</p>
    <p class="text-muted">Posted by ${user.username}</p>
    <button onclick="window.history.back()" class="btn btn-primary mt-3">Go Back</button>
</div>
<jsp:include page="/WEB-INF/partials/footer.jsp"/>
</body>
</html>
