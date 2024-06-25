<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8"
         isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Board Form</title>
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
