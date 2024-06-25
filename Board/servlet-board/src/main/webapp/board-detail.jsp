<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8"
         isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Board Detail</title>
        <style>
          /* 기본적인 스타일링 */
          body {
            font-family: Arial, sans-serif;
            margin: 0;
            padding: 0;
            background-color: #f4f4f4;
          }

          .container {
            max-width: 600px;
            margin: 20px auto;
            background: white;
            padding: 20px;
            border-radius: 8px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
          }

          .board-detail h2 {
            margin-top: 0;
          }

          .board-detail p {
            margin: 10px 0;
          }

          .back-link {
            display: block;
            margin-top: 20px;
            text-decoration: none;
            color: #007bff;
          }
        </style>
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
