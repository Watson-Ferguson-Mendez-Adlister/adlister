<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <jsp:include page="/WEB-INF/partials/head.jsp">
        <jsp:param name="title" value="Create a new Ad" />
    </jsp:include>
</head>
<body>
<jsp:include page="/WEB-INF/partials/navbar.jsp"/>
<div class="container">
    <h1 class="header-text text-center pt-5">Create a new Ad</h1>
    <form action="/ads/create" method="post" class="mx-auto w-50">
        <div class="form-group">
            <label for="category">Category</label>
            <select id="category" name="category" class="form-control" required>
                <option value="">Select a category</option>
                <option value="community">Community</option>
                <option value="events">Events</option>
                <option value="forSale">For Sale</option>
                <option value="jobs">Jobs</option>
                <option value="housing">Housing</option>
                <option value="services">Services</option>
                <option value="gigs">Gigs</option>
            </select>
        </div>
        <div class="form-group">
            <label for="title">Title</label>
            <input id="title" name="title" class="form-control" type="text">
        </div>
        <div class="form-group">
            <label for="description">Description</label>
            <textarea id="description" name="description" class="form-control" type="text"></textarea>
        </div>
        <input type="submit" class="btn btn-block btn-primary">
    </form>
</div>
<jsp:include page="/WEB-INF/partials/footer.jsp"/>
</body>
</html>
