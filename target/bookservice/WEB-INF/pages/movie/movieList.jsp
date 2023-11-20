
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Movies</title>
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <script src="${pageContext.servletContext.contextPath}/js/parameterFunctions.js"></script>
    <script src="${pageContext.servletContext.contextPath}/js/movie.js"></script>
</head>
<body>
<div class="container-fluid">
    <jsp:include page="../navbar/navbar.jsp" />
    <form id="queryForm" class="mt-4">
        <div class="form-group">
            <label for="query">Find:</label>
            <input type="text" class="form-control" name="query" id="query">
        </div>
        <button type="submit" class="btn btn-primary">Find</button>
    </form>

    <c:forEach var="movie" items="${movies}">
        <div class="card mt-4">
            <div class="card-body">
                <h5 class="card-title"><a href="${pageContext.servletContext.contextPath}/movie/${movie.getId()}">${movie.getTitle()}</a></h5>
                <p class="card-text">${movie.getDescription()}</p>
                <c:if test="${isAdmin}">
                    <a href="${pageContext.servletContext.contextPath}/movie/edit/${movie.getId()}" class="btn btn-secondary">Edit</a>
                </c:if>
            </div>
        </div>
    </c:forEach>

    <form id="next" class="mt-4">
        <button type="submit" class="btn btn-primary">Next</button>
    </form>

    <c:if test="${page > 1}">
        <form id="previous" class="mt-4">
            <button type="submit" class="btn btn-primary">Previous</button>
        </form>
    </c:if>
</div>
</body>
</html>


