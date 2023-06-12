<!DOCTYPE html>
<html>
<head>
  <title>음악추가</title>
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <link rel="stylesheet" href="../../css/default.css">
  <style>
    input[type='text'] {width:90%;}
  </style>
</head>
<body>
<%@include file="/WEB-INF/jsp/header.jsp" %>
<main>
  <h3>음악추가</h3>
  <form action="./addSong" method="post">
    <p><input type="text" name="songTitle" placeholder="음악제목" required autofocus/></p>
    <p><input type="text" name="singer" placeholder="가수" required/> </p>
    <p><button type="submit">등록</button></p>
  </form>
</main>
</body>
</html>