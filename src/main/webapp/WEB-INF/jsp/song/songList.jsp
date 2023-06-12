<!DOCTYPE html>
<html>
<head>
  <title>음악목록</title>
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <link rel="stylesheet" href="../../css/default.css">
  <style>
    input[type='number'] {width:50px;text-align:center;}
  </style>
</head>
<body>
<%@include file="/WEB-INF/jsp/header.jsp" %>
<main>
  <h3>음악 목록</h3>
  <form action="./songList" method="get">
    <p>
      페이지 : <input type="number" name="page" min="1" value="${limit.page}"
                   required autofocus/> 행 : <input type="number" name="count" min="5"
                                                   step="5" value="${limit.count}" required/>
      <button type="submit">검색</button>
      <a href="./songForm">음악추가</a>
    </p>
  </form>
  <table class="list">
    <tr>
      <th>번호</th>
      <th>제목</th>
      <th>가수</th>
    </tr>
    <c:forEach var="song" items="${requestScope.songList}">
     <tr>
       <td>${pageScope.song.songId}</td>
       <td>${pageScope.song.songTitle}</td>
       <td>${pageScope.song.singer}</td>
     </tr>
    </c:forEach>
  </table>
</main>
</body>
</html>