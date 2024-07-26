<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
    <head>
        <title>게시판 목록</title>
        <link rel="stylesheet"
              href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
    </head>
    <body>
        <div class="container mt-5">
            <h2>게시판 목록</h2>
            <table class="table table-striped">
                <thead>
                    <tr>
                        <th scope="col">번호</th>
                        <th scope="col">제목</th>
                        <th scope="col">작성자</th>
                        <th scope="col">조회수</th>
                        <th scope="col">등록일</th>
                        <th scope="col">수정일</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="board" items="${boardList}">
                        <tr>
                            <td>${board.num}</td>
                            <td>
                                <a href="${contextPath}/board/detailBoard.do?num=${board.num}">
                                        ${board.title}
                                </a>
                            </td>
                            <td>${board.writer}</td>
                            <td>${board.cnt}</td>
                            <td>${board.regdate}</td>
                            <td>${board.lastModified}</td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
            <div class="mt-3">
                <a href="${contextPath}/board/showBoardForm.do" class="btn btn-primary">새 글 작성</a>
            </div>
        </div>
        <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
    </body>
</html>
