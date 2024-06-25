<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8"
         isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Board List</title>
        <style>
          /* 기본적인 스타일링 */
          body {
            font-family: Arial, sans-serif;
            margin: 0;
            padding: 0;
            background-color: #f4f4f4;
          }

          .container {
            max-width: 800px;
            margin: 20px auto;
            background: white;
            padding: 20px;
            border-radius: 8px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
          }

          table {
            width: 100%;
            border-collapse: collapse;
          }

          th, td {
            padding: 10px;
            border: 1px solid #ddd;
            text-align: left;
          }

          th {
            background-color: #f2f2f2;
          }

          .btn {
            padding: 5px 10px;
            text-decoration: none;
            color: white;
            background-color: #007bff;
            border-radius: 4px;
          }

          .btn.edit {
            background-color: #ffc107;
          }

          .btn.delete {
            background-color: #dc3545;
          }

          .new-btn {
            margin-bottom: 20px;
            display: inline-block;
            text-decoration: none;
            color: white;
            background-color: #28a745;
            padding: 10px 15px;
            border-radius: 4px;
          }
        </style>
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
