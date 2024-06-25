<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8"
         isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Board Form</title>
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

          .form-group {
            margin-bottom: 15px;
          }

          .form-group label {
            display: block;
            margin-bottom: 5px;
          }

          .form-group input, .form-group textarea {
            width: 100%;
            padding: 10px;
            box-sizing: border-box;
          }

          .form-group textarea {
            height: 100px;
          }

          .error {
            color: red;
          }
        </style>
    </head>
    <body>
    <div class="container">
        <h2><c:choose>
            <c:when test="${board != null}">Edit Board</c:when>
            <c:otherwise>New Board</c:otherwise>
        </c:choose></h2>

        <form action="${board != null ? 'update' : 'insert'}" method="post">
            <input type="hidden" name="num" value="${board != null ? board.num : ''}">
            <div class="form-group">
                <label for="title">Title</label>
                <input type="text" id="title" name="title"
                       value="${board != null ? board.title : ''}" required>
            </div>
            <c:if test="${board == null}">
                <div class="form-group">
                    <label for="writer">Writer</label>
                    <input type="text" id="writer" name="writer" required>
                </div>
            </c:if>
            <div class="form-group">
                <label for="content">Content</label>
                <textarea id="content" name="content"
                          required>${board != null ? board.content : ''}</textarea>
            </div>
            <c:if test="${error != null}">
                <div class="error">${error}</div>
            </c:if>
            <button type="submit">${board != null ? 'Update' : 'Create'}</button>
            <a href="list">Back to list</a>
        </form>
    </div>
    </body>
</html>
