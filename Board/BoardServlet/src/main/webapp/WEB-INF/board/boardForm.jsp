<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
    <head>
        <title>${formTitle}</title>
        <link rel="stylesheet"
              href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
    </head>
    <body>
        <div class="container mt-5">
            <h2>${formTitle}</h2>
            <form method="post" action="${contextPath}/board/${formAction}.do">
                <c:if test="${not empty boardInfo.num}">
                    <input type="hidden" name="num" value="${boardInfo.num}">
                </c:if>
                <div class="form-group">
                    <label for="title">제목:</label>
                    <input type="text" class="form-control" id="title" name="title"
                           value="${boardInfo.title}" required>
                </div>
                <div class="form-group">
                    <label for="writer">작성자:</label>
                    <input type="text" class="form-control" id="writer" name="writer"
                           value="${boardInfo.writer}" required>
                </div>
                <div class="form-group">
                    <label for="content">내용:</label>
                    <textarea class="form-control" id="content" name="content" rows="5"
                              required>${boardInfo.content}</textarea>
                </div>
                <div class="form-group">
                    <button type="submit" class="btn btn-primary">${submitButtonText}</button>
                    <a href="${contextPath}/board/listBoard.do" class="btn btn-secondary">취소</a>
                </div>
            </form>
        </div>
        <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
    </body>
</html>
