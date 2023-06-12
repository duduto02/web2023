<!DOCTYPE html>
<html>
<head>
  <title>음악 수정</title>
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <link rel="stylesheet" href="../../css/default.css">
  <style>
    input[type='text'] {width:90%;}
    textarea {width:90%; height:200px;}
  </style>
</head>
<body>
<%@include file="/WEB-INF/jsp/header.jsp" %>
<main>
  <h3>음악 수정</h3>
  <form action="./updateSong" method="post">
    <p><input type="text" name="songTitle" value="${song.songTitleEncoded}"
              placeholder="음악제목" required autofocus/></p>
    <p><input type="text" name="singer" value="${song.singerEncoded}"
              placeholder="가수" required/></p>
    <p><button type="submit">저장</button></p>
    <input type="hidden" name="songId" value="${song.songId}"/>
  </form>
</main>
</body>
</html>