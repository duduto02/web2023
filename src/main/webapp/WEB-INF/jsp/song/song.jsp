<!DOCTYPE html>
<html>
<head>
  <title>음악추가</title>
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <link rel="stylesheet" href="../../css/default.css">
  <style>
    input[type='text'] {width:90%;}
    textarea {width:90%; height: 200px;}
    p.title {border-top:1px; solid gray; font-weight:bold;}
    p.info {border-bottom:1px solid grey;}
  </style>
  <script>
    window.onload = () => {
      document.querySelector('#btnDel').onClick = () => confirm('삭제하시겠습니까?');
    }
  </script>
</head>
<body>
<%@include file="/WEB-INF/jsp/header.jsp" %>
<main>
  <h3>음악보기</h3>

  <p><a href="${sessionScope.CURRENT_SONG_LIST}">음악목록</a>
    <a href="./songEdit?songId=${song.songId}">음악수정</a>
    <a id="btnDel" href="./deleteSong?songId=${song.songId}">음악삭제</a>
  </p>

  <p>
  <p class="info title">${song.songId}. ${song.songTitleEncoded} ${song.singerEncoded}</p>
  </p>
</main>
</body>
</html>