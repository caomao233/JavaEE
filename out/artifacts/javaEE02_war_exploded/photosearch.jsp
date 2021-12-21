<%--
  Created by IntelliJ IDEA.
  User: caomao
  Date: 2021/10/18
  Time: 15:03
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>图片搜索</title>
</head>
<body>
<center>
<h1>请上传照片</h1>
<form action="${pageContext.request.contextPath}/photosearch" method="post" enctype="multipart/form-data">
    照片：<input type="file" name="spicture">
    <button type="submit">确认</button>
</form>
<div>
    <a href="${pageContext.request.contextPath}/search.jsp" class="card-link" >返回</a>
</div>
</center>
</body>
</html>
