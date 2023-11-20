<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <title>Create Movie</title>
</head>
<body>
<div class="container-fluid">
    <jsp:include page="../navbar/navbar.jsp" />
    <h1 class="mt-4">Edit Movie</h1>
    <form class="mt-4" method="post" action="${pageContext.servletContext.contextPath}/movie/edit/${movie.getId()}">
        <div class="form-group">
            <label for="title">Title:</label>
            <input type="text" class="form-control" id="title" name="title" value="${movie.getTitle()}" required>
        </div>
        <div class="form-group">
            <label for="description">Description:</label>
            <input type="text" class="form-control" id="description" name="description" value="${movie.getDescription()}" required>
        </div>
        <div class="form-group">
            <label for="imageUrl">Image URL:</label>
            <input type="url" class="form-control" id="imageUrl" name="imageUrl" value="${movie.getImageUrl()}" required>
        </div>
        <div class="form-group">
            <label for="releaseDate">Release Date:</label>
            <input type="date" class="form-control" id="releaseDate" name="releaseDate" value="${movie.getReleaseDate()}" required>
        </div>
        <button type="submit" class="btn btn-primary">Save Changes</button>
    </form>
</div>
</body>
</html>
