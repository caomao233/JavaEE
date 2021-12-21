<%@ page import="work.JDBC" %>
<%@ page import="javax.imageio.stream.FileImageOutputStream" %>
<%@ page import="java.io.File" %>
<%@ page import="java.io.FileOutputStream" %>
<%@ page import="java.sql.SQLException" %><%--
  Created by IntelliJ IDEA.
  User: 13422
  Date: 2021/10/13
  Time: 17:58
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>图片查看</title>
</head>
<body>
<%  String uname= request.getParameter("uname");
    String fileName=uname+".png";
    boolean photo= false;
    int type=JDBC.getPhoto(uname);
        if (type!=0) {
            photo = true;
            if(type==2)
                fileName=uname+".jpg";
        } else {
            photo = false;
        }
        System.out.println(photo);
%>
<center><h2><%=uname%></h2></center>
<%
    if (!photo) {
%>
<center><h3>尚未上传图片</h3></center>
<%
    } else {
%>
<center><img src="http://localhost:8080/javaEE02_war_exploded/upload<%=File.separator+fileName%>" ></center>
<%
}
%>
</body>
</html>
