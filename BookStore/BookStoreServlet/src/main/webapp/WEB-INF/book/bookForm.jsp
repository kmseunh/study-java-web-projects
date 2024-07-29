<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<html>
    <head>
        <meta charset="UTF-8">
        <title>${formTitle}</title>
    </head>
    <body>
        <h1>${formTitle}</h1>
        <form action="${pageContext.request.contextPath}/book/${formAction}.do" method="post">
            <c:if test="${not empty bookInfo.id}">
                <input type="hidden" name="id" value="${bookInfo.id}">
            </c:if>
            <label for="title">Title:</label>
            <input type="text" name="title" id="title" value="${bookInfo.title}" required>
            <br>
            <label for="author">Author:</label>
            <input type="text" name="author" id="author" value="${bookInfo.author}" required>
            <br>
            <label for="price">Price:</label>
            <input type="number" name="price" id="price" step="0.01" value="${bookInfo.price}"
                   required>
            <br>
            <button type="submit">${submitButtonText}</button>
        </form>
        <a href="${pageContext.request.contextPath}/book/listBook.do">Back to Book List</a>
    </body>
</html>
