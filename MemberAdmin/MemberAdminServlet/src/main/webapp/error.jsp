<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
    <head>
        <title>에러 페이지</title>
        <style>
          body {
            text-align: center;
            font-family: Arial, sans-serif;
          }

          .error-message {
            font-size: 20px;
            color: red;
          }

          .error-details {
            margin-top: 20px;
            font-size: 16px;
          }
        </style>
    </head>
    <body>
        <h1 class="error-message">오류가 발생했습니다.</h1>
        <p class="error-details">
            <c:if test="${not empty errorMsg}">
                ${errorMsg}
            </c:if>
            <c:if test="${empty errorMsg}">
                문제가 발생했습니다. 잠시 후 다시 시도해 주시기 바랍니다.
            </c:if>
        </p>
        <p><a href="${pageContext.request.contextPath}/member/listMembers.do">회원 목록으로 돌아가기</a></p>
    </body>
</html>