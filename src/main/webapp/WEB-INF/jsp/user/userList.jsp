<!DOCTYPE html>
<html>
<head>
    <title>회원목록</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="../../css/default.css">
    <style>
        input[type='number'] {width:50px; text-align:center;}
    </style>
</head>
<body>
<%@include file="/WEB-INF/jsp/header.jsp" %>
<main>
    <h3>회원 목록</h3>
    <form action="./userList" method="get">
        <p>
            페이지 : <input type="number" name="page" min="1" value="${limit.page}"
                         required autofocus/>행 : <input type="number" name="count" min="5"
                                                        step="5" value="${limit.count}" required/>
            <button type="submit">검색</button>
        </p>
    </form>
    <table class="list">
        <tr>
            <th>번호</th>
            <th>이메일</th>
            <th>이름</th>
        </tr>
        <c:forEach var="user" items="${requestScope.userList}">
            <tr>
                <td>${pageScope.user.userId}</td>
                <td>${pageScope.user.email}</td>
                <td>${pageScope.user.name}</td>
            </tr>
        </c:forEach>
    </table>
</main>
</body>
</html>