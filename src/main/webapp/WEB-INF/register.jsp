<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <jsp:include page="partials/head.jsp">
        <jsp:param name="title" value="Register For Our Site!"/>
    </jsp:include>
</head>
<body>
<jsp:include page="partials/navbar.jsp"/>
<div class="container">
    <h1 class="header-text text-center pt-5">Please fill in your information.</h1>
    <form action="/register" method="post" class="mx-auto w-50">
        <div class="form-group">
            <label for="username">Username</label>
            <input id="username" name="username" class="form-control" type="text"
                   value="<%=request.getSession().getAttribute("prevUsername")%>">
            <c:if test="${not empty requestScope.userError}">
                <p class="error-message">${requestScope.userError}</p>
            </c:if>
        </div>
        <div class="form-group">
            <label for="email">Email</label>
            <input id="email" name="email" class="form-control" type="text"
                   value="<%=request.getSession().getAttribute("prevEmail")%>">
            <c:if test="${not empty requestScope.emailError}">
                <p class="error-message">${requestScope.emailError}</p>
            </c:if>
        </div>
        <div class="form-group">
            <label for="password">Password</label>
            <input id="password" name="password" class="form-control" type="password"
                   value="<%=request.getSession().getAttribute("prevPassword")%>">
        </div>
        <div class="form-group">
            <label for="confirm_password">Confirm Password</label>
            <input id="confirm_password" name="confirm_password" class="form-control" type="password">
            <c:if test="${not empty requestScope.passError}">
                <p class="error-message">${requestScope.passError}</p>
            </c:if>
        </div>
        <input type="submit" class="btn btn-primary btn-block">
    </form>
</div>
</body>
<jsp:include page="/WEB-INF/partials/footer.jsp"/>
</html>
