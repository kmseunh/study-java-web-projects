<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<html>
    <head>
        <title><c:out value="${formTitle}"/></title>
        <style>
          body {
            text-align: center;
          }

          table {
            margin: 0 auto;
          }

          td {
            padding: 10px;
          }

          .error-message {
            color: red;
            font-weight: bold;
          }
        </style>
    </head>
    <body>
        <h1><c:out value="${formTitle}"/></h1>

        <c:if test="${not empty errorMessage}">
            <p class="error-message">${errorMessage}</p>
        </c:if>

        <form method="post" action="${contextPath}/member/${formAction}.do">
            <c:if test="${not empty memberInfo.id}">
                <input type="hidden" name="id" value="${memberInfo.id}"/>
            </c:if>
            <table>
                <tr>
                    <td align="center">아이디</td>
                    <td><input type="text" name="id" value="${memberInfo.id}" ${readonly} required/>
                    </td>
                </tr>
                <tr>
                    <td align="center">패스워드</td>
                    <td><input type="password" name="pwd" value="${memberInfo.pwd}" required/></td>
                </tr>
                <tr>
                    <td align="center">이름</td>
                    <td><input type="text" name="name" value="${memberInfo.name}" required/></td>
                </tr>
                <tr>
                    <td align="center">이메일</td>
                    <td><input type="email" name="email" value="${memberInfo.email}" required/></td>
                </tr>
                <tr>
                    <td></td>
                    <td>
                        <input type="submit" value="<c:out value='${submitButtonText}'/>">
                        <input type="reset" value="초기화">
                    </td>
                </tr>
            </table>
        </form>
    </body>
</html>