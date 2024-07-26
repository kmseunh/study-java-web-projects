<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
    <head>
        <title>게시글 상세</title>
        <link rel="stylesheet"
              href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
    </head>
    <body>
        <div class="container mt-5">
            <h2>게시글 상세</h2>
            <div class="form-group">
                <label>제목:</label>
                <p class="form-control-static">${board.title}</p>
            </div>
            <div class="form-group">
                <label>작성자:</label>
                <p class="form-control-static">${board.writer}</p>
            </div>
            <div class="form-group">
                <label>내용:</label>
                <pre class="form-control-static">${board.content}</pre>
            </div>
            <div class="form-group">
                <label>조회수:</label>
                <p class="form-control-static">${board.cnt}</p>
            </div>
            <div class="form-group">
                <label>등록일:</label>
                <p class="form-control-static">${board.regdate}</p>

            </div>
            <div class="form-group">
                <label>수정일:</label>
                <p class="form-control-static">${board.lastModified}</p>
            </div>
            <div class="form-group">
                <a href="${contextPath}/board/showModBoardForm.do?num=${board.num}"
                   class="btn btn-warning">수정</a>
                <a href="${contextPath}/board/delBoard.do?num=${board.num}" class="btn btn-danger"
                   onclick="return confirm('정말 삭제하시겠습니까?');">삭제</a>
                <a href="${contextPath}/board/listBoard.do" class="btn btn-secondary">목록</a>
            </div>
        </div>
        <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
    </body>
</html>
