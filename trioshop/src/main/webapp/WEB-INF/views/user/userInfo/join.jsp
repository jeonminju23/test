<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>회원가입</title>
    <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<div class="container mt-5">
    <div class="row justify-content-center">
        <div class="col-md-6">
            <div class="card">
                <div class="card-header text-center">
                    <h4>회원가입</h4>
                </div>
                <div class="card-body">
                    <form id="joinForm" action="/join" method="post">
                        <div class="form-group">
                            <label for="userId">아이디:</label>
                            <input type="text" class="form-control" id="userId" name="userId" required>
                        </div>

                        <div class="form-group">
                            <label for="userPasswd">비밀번호:</label>
                            <input type="password" class="form-control" id="userPasswd" name="userPasswd" required>
                        </div>

                        <div class="form-group">
                            <label for="userName">이름:</label>
                            <input type="text" class="form-control" id="userName" name="userName" required>
                        </div>

                        <div class="form-group">
                            <label for="userAddress">주소:</label>
                            <input type="text" class="form-control" id="userAddress" name="userAddress" required>
                        </div>

                        <div class="form-group">
                            <label for="userTel">전화번호:</label>
                            <input type="text" class="form-control" id="userTel" name="userTel" required>
                        </div>

                        <div class="form-group">
                            <label for="userNickname">닉네임:</label>
                            <input type="text" class="form-control" id="userNickname" name="userNickname" required>
                        </div>

                        <button type="submit" class="btn btn-primary btn-block">회원가입</button>
                        <button type="button" class="btn btn-secondary btn-block" onclick="location.href='/login'">로그인창으로 돌아가기</button>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>
<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.2/dist/umd/popper.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
<c:if test="${not empty success}">
    <script>
        alert("${success}");
    </script>
</c:if>

<c:if test="${not empty error}">
    <script>
        alert("${error}");
    </script>
</c:if>

<c:if test="${not empty exception}">
    <script>
        alert("${exception}");
    </script>
</c:if>
</body>
</html>
