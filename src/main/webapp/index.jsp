<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <jsp:include page="/WEB-INF/partials/head.jsp">
        <jsp:param name="title" value="Adlister"/>
    </jsp:include>
</head>
<body>
<jsp:include page="/WEB-INF/partials/navbar.jsp"/>
<div class="container">
    <h1 class="text-center mb-4">The Ultimate Student Market</h1>
    <h2 class="text-center">Search for what you're looking for</h2>
</div>
<%-- search form--%>
<form action="${pageContext.request.contextPath}/search" method="GET" class="text-center pt-3">
    <input type="text" name="query" placeholder="Search for ads" class="search-bar">
</form>
<div class="container">
    <div class="row">
        <c:forEach var="ad" items="${ads}">
            <div class="col-3">
                <div class="card index-card mb-4">
                    <div class="card-body">
                        <h5 class="card-title">
                            <a href="${pageContext.request.contextPath}/ads/${ad.id}">${ad.title}</a>
                        </h5>
                        <p class="card-text text-muted">${ad.description}</p>
                    </div>
                </div>
            </div>
        </c:forEach>
    </div>
</div>
</body>
</html>
