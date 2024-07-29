<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Book List</title>
    </head>
    <body>
        <h1>Book List</h1>
        <c:choose>
            <c:when test="${empty bookList}">
                <p>No books available.</p>
            </c:when>
            <c:otherwise>
                <table border="1">
                    <thead>
                        <tr>
                            <th>ID</th>
                            <th>Title</th>
                            <th>Author</th>
                            <th>Price</th>
                            <th>Actions</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="book" items="${bookList}">
                            <tr>
                                <td>${book.id}</td>
                                <td>${book.title}</td>
                                <td>${book.author}</td>
                                <td>${book.price}</td>
                                <td>
                                    <a href="${pageContext.request.contextPath}/book/showUpdateBook.do?id=${book.id}">Edit</a>
                                    <a href="${pageContext.request.contextPath}/book/deleteBook.do?id=${book.id}"
                                       onclick="return confirm('Are you sure you want to delete this book?');">Delete</a>
                                </td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </c:otherwise>
        </c:choose>
        <a href="${pageContext.request.contextPath}/book/showBookForm.do">Add New Book</a>
    </body>
</html>