<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: caomao
  Date: 2021/10/18
  Time: 16:51
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>结果</title>
</head>
<body>
<%
    List<String> table = (List<String>)request.getAttribute("result");
    for (int i=0;i<(table.size());i=i+5) {
%>
<center>
    <table class="table">
        <div><h3>id&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;姓名&nbsp;&nbsp;&nbsp;电话&nbsp;&nbsp;&nbsp; qq&nbsp;&nbsp;&nbsp;邮箱</h3></div>
<tr>
    <td><%=table.get(i)%></td>
    <td><%=table.get(i+1)%></td>
    <td><%=table.get(i+2)%></td>
    <td><%=table.get(i+3)%></td>
    <td><%=table.get(i+4)%></td>
    <td> <form action="${pageContext.request.contextPath}/getPhoto.jsp?uname=<%=table.get(i)%>" method = "post">

        <input type = "submit" value = "查看图片">

    </form> </td>
</tr>
<%
    }%>
</table>
    <a href="${pageContext.request.contextPath}/search.jsp" class="card-link">继续查询</a>
</center>
</body>
</html>
