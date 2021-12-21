<%--
  Created by IntelliJ IDEA.
  User: 13422
  Date: 2021/10/12
  Time: 12:29
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>上传照片</title>
</head>
<body>
    <h1>请上传照片</h1>
    <form action="uploadServlet" method="post" enctype="multipart/form-data">
        照片：<input type="file" name="spicture">
        <button type="submit">确认</button>
    </form>
    <div>
        <a href="${pageContext.request.contextPath}/search.jsp" class="card-link" >返回</a>
    </div>
</body>
</html>
