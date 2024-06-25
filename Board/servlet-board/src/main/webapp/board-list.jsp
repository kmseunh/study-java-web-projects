<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8"
         isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Board List</title>
    </head>
    <body>
    <div class="container">
        <h2>Board List</h2>
        <a class="new-btn" href="new">New Board</a>
        <table>
            <thead>
            <tr>
                <th>Number</th>
                <th>Title</th>
                <th>Writer</th>
                <th>Date</th>
                <th>View Count</th>
                <th>Actions</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="board" items="${listBoard}">
                <tr>
                    <td>${board.num}</td>
                    <td><a href="detail?num=${board.num}">${board.title}</a></td>
                    <td>${board.writer}</td>
                    <td>${board.regdate}</td>
                    <td>${board.cnt}</td>
                    <td>
                        <a class="btn edit" href="edit?num=${board.num}">Edit</a>
                        <a class="btn delete" href="delete?num=${board.num}"
                           onclick="return confirm('Are you sure you want to delete this board?');">Delete</a>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
    </body>
</html>
