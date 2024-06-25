<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8"
         isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Board Detail</title>
    </head>
    <body>
    <div class="container">
        <div class="board-detail">
            <h2>${board.title}</h2>
            <p><strong>Writer:</strong> ${board.writer}</p>
            <p><strong>Content:</strong></p>
            <p>${board.content}</p>
            <p><strong>Registered Date:</strong> ${board.regdate}</p>
            <p><strong>Last Modified:</strong> ${board.lastModified}</p>
            <p><strong>View Count:</strong> ${board.cnt}</p>
            <a class="back-link" href="list">Back to list</a>
        </div>
    </div>
    </body>
</html>
