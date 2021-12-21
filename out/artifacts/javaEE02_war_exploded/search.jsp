<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="work.JDBC" %>
<!DOCTYPE html>

<html>
  <head>
    <title>$欢迎使用找人系统$</title>
  </head>
  <body>
  <% String uname=(String)application.getAttribute("uname");%>
  <h1>欢迎<%=uname%>登录</h1>
  <h1>请选择一个输入</h1>

  <form action="${pageContext.request.contextPath}/searchServlet" method="post">

    <div><h2>id&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="text" name="id" id="id"></h2></div>
    <div></div>

    <div><h2>姓名&nbsp;&nbsp;<input type="text" name="name" id="name"></h2></div>
	<div></div>
	
    <div><h2>电话&nbsp;&nbsp;<input type="text" name="phone" id="phone"></h2></div>
	<div></div>

    <div><h2>qq&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="text" name="qq" id="qq"></h2></div>
	<div></div>

    <div><h2>邮箱&nbsp;&nbsp;<input type="text" name="email" id="email"></h2></div>
	<div></div>
	
    <div><h2>每页显示条数<input type="text" name="number" id="number"></h2></div>
    <div></div>

    <h2><button type="submit">查询</button></h2>

  </form>

  <% int type =(int)application.getAttribute("type");
     if(type==0){
  %>
<div>
  <a href="${pageContext.request.contextPath}/operate.jsp" class="card-link" >对数据进行操作</a>
</div>
  <%}%>
  <div>
    <a href="${pageContext.request.contextPath}/photosearch.jsp" class="card-link">上传图片搜索</a>
  </div>
<%
  if(JDBC.getPhoto(uname)==0){
%>
  <div>
    <a href="${pageContext.request.contextPath}/upload.jsp" class="card-link" >上传照片</a>
  </div>
  <%
    }
  %>
  <div>
    <a href="${pageContext.request.contextPath}/chat.jsp?uname=<%=uname%>" class="card-link">聊天</a>
  </div>
  <div>
    <a href="${pageContext.request.contextPath}/login.jsp" class="card-link" >退出登录</a>
  </div>



  </body>
</html>