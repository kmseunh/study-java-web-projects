<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<html>
    <head>
        <title>회원 정보 출력창</title>
        <style>
          .cls1 {
            font-size: 40px;
            text-align: center;
          }

          .cls2 {
            font-size: 20px;
            text-align: center;
          }
        </style>
        <c:choose>
            <c:when test="${msg == 'added'}">
                <script>
                  window.onload = function () {
                    alert('회원을 등록했습니다.');
                  };
                </script>
            </c:when>
            <c:when test="${msg == 'updated'}">
                <script>
                  window.onload = function () {
                    alert('회원 정보를 수정했습니다.');
                  };
                </script>
            </c:when>
            <c:when test="${msg == 'deleted'}">
                <script>
                  window.onload = function () {
                    alert('회원 정보를 삭제했습니다.');
                  };
                </script>
            </c:when>
        </c:choose>
    </head>
    <body>
        <p class="cls1">회원정보</p>
        <table align="center" border="1">
            <tr align="center" bgcolor="#90ee90">
                <td width="7%"><b>아이디</b></td>
                <td width="7%"><b>비밀번호</b></td>
                <td width="7%"><b>이름</b></td>
                <td width="7%"><b>이메일</b></td>
                <td width="7%"><b>가입일</b></td>
                <td width="7%"><b>수정</b></td>
                <td width="7%"><b>삭제</b></td>
            </tr>
            <c:choose>
                <c:when test="${empty memberList}">
                    <tr>
                        <td colspan="7">
                            <b>등록된 회원이 없습니다.</b>
                        </td>
                    </tr>
                </c:when>
                <c:otherwise>
                    <c:forEach var="mem" items="${memberList}">
                        <tr align="center">
                            <td>${mem.id}</td>
                            <td>${mem.pwd}</td>
                            <td>${mem.name}</td>
                            <td>${mem.email}</td>
                            <td>${mem.joinDate}</td>
                            <td>
                                <a href="${contextPath}/member/showModMember.do?id=${mem.id}">수정</a>
                            </td>
                            <td>
                                <a href="${contextPath}/member/delMember.do?id=${mem.id}">삭제</a>
                            </td>
                        </tr>
                    </c:forEach>
                </c:otherwise>
            </c:choose>
        </table>
        <div class="cls2" align="center">
            <a href="${contextPath}/member/memberForm.do">회원가입</a>
        </div>
    </body>
</html>