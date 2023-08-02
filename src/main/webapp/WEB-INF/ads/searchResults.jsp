<%--
  Created by IntelliJ IDEA.
  User: robertmendez
  Date: 7/31/23
  Time: 1:41 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
  <jsp:include page="/WEB-INF/partials/head.jsp">
    <jsp:param name="title" value="Search Results"/>
  </jsp:include>
</head>
<body>
<jsp:include page="/WEB-INF/partials/navbar.jsp"/>
<div class="container">
  <h1 class="pt-5 header-text">Search Results</h1>
  <c:choose>
    <c:when test="${empty ads}">
      <p>No ads found for your search.</p>
    </c:when>
    <c:otherwise>
      <div class="row mt-4">
        <c:forEach var="ad" items="${ads}">
          <div class="col-3">
            <div class="card mb-4 index-card">
              <div class="card-body">
                <h5 class="card-title"><a href="${pageContext.request.contextPath}/ads/${ad.id}">${ad.title}</a></h5>
                <p class="card-text">${ad.description}</p>
              </div>
            </div>
          </div>
        </c:forEach>
      </div>
    </c:otherwise>
  </c:choose>
  <button onclick="window.history.back()" class="btn btn-primary mt-3">Go Back</button>
</div>
</body>
</html>
