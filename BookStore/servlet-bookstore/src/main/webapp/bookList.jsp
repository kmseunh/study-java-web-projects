<%@ page contentType="text/html;charset=UTF-8" language="java"
         isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
    <head>
        <title>Books Store Application</title>
    </head>
    <body>
    <center>
        <h1>Books Management</h1>
        <h2>
            <a href="<%= request.getContextPath() %>/new">Add New Book</a>
            &nbsp;&nbsp;&nbsp;
            <a href="<%= request.getContextPath() %>/list">List All Books</a>
        </h2>
    </center>
    <div align="center">
        <form action="<%= request.getContextPath() %>/search" method="get">
            <input type="text" name="keyword" placeholder="keyword..">
            <input type="submit" value="Search">
        </form>
        <table border="1" cellpadding="5">
            <caption><h2>List of Books</h2></caption>
            <tr>
                <th>ID</th>
                <th>Title</th>
                <th>Author</th>
                <th>Price</th>
                <th>Actions</th>
            </tr>
            <c:forEach var="book" items="${listBook}">
                <tr>
                    <td><c:out value="${book.id}"/></td>
                    <td><c:out value="${book.title}"/></td>
                    <td><c:out value="${book.author}"/></td>
                    <td><c:out value="${book.price}"/></td>
                    <td>
                        <a href="<%= request.getContextPath() %>/edit?id=<c:out value='${book.id}' />">Edit</a>
                        &nbsp;&nbsp;&nbsp;&nbsp;
                        <a href="<%= request.getContextPath() %>/delete?id=<c:out value='${book.id}' />">Delete</a>
                    </td>
                </tr>
            </c:forEach>
        </table>
    </div>
    </body>
</html>