<%--
  Created by IntelliJ IDEA.
  User: 13422
  Date: 2021/10/11
  Time: 18:19
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>请输入</title>
</head>
<body>

<form action="${pageContext.request.contextPath}/operateServlet" method="post">

    <div><h2>输入 (1)增加，(2)修改，(3)删除<input type="text" name="operate" id="operate"></h2></div>
    <h3>(1)格式：学号,姓名,电话,QQ,邮箱</h3>
    <h3>(2)格式：修改的值+where id=学号</h3>
    <h3>(3)格式：输入学号</h3>
    <div><h2>请输入&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="text" name="sql" id="sql"></h2></div>

    <div></div>
    <h2><button type="submit">确认</button></h2>

</form>
<a href="${pageContext.request.contextPath}/search.jsp" class="card-link">返回</a>
</body>
</html>
