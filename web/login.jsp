<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8"%>

<!DOCTYPE html>
<html>
<head>
  <meta charset="utf-8">
  <title>login</title>
</head>
<body>

<%! String uname; %>
<%
  Cookie[] cookies = request.getCookies();
  if(cookies != null){
    for(Cookie cookie : cookies){
      if("name".equals(cookie.getName())){
        uname = cookie.getValue();
      }
    }
  }

%>
<center>
<form action="${pageContext.request.contextPath}/LoginServlet" method = "post">
  <h1>用户名：<input type = "text" name = "uname" value = <%=(uname==null ? "":uname)%>><br/></h1>
  <h1>密码：<input type = "password" name = "upwd"><br/></h1>
  <input type = "submit" value = "登录"><br/>
</form></center>
</body>
</html>